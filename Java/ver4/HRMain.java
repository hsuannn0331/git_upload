package com.cathaybk.practice.nt50346.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HRMain {
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", new BigDecimal("35000"), new BigDecimal("6000")));
		employeeList.add(new Sales("林大鈞", "保代部", new BigDecimal("38000"), new BigDecimal("4000")));
		employeeList.add(new Supervisor("李中白", "資訊部", new BigDecimal("65000")));
		employeeList.add(new Supervisor("林小中", "理財部", new BigDecimal("80000")));

		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File("C:/Users/Admin/Desktop/Java班/git_upload/Java/ver4/output.csv")),
				StandardCharsets.UTF_8))) {
			bw.write('\ufeff');
			Collections.sort(employeeList, new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {
					if (o1 instanceof Supervisor && !(o2 instanceof Supervisor)) {
						return -1;
					} else if (!(o1 instanceof Supervisor) && o2 instanceof Supervisor) {
						return 1;
					} else if (o1 instanceof Supervisor && o2 instanceof Supervisor) {
						return ((Supervisor) o1).getPayment().compareTo(((Supervisor) o2).getPayment());
					} else {
						return ((Sales) o1).getPayment().compareTo(((Sales) o2).getPayment());
					}
				}
			});

			for (Employee employee : employeeList) {
				BigDecimal employeePayment;
				if (employee instanceof Sales) {
					employeePayment = ((Sales) employee).getPayment();
				} else if (employee instanceof Supervisor) {
					employeePayment = ((Supervisor) employee).getPayment();
				} else {
					employeePayment = BigDecimal.ZERO;
				}

				String getNameAndPayment = String.format("%s, %.0f", employee.getName(), employeePayment);
				bw.write(getNameAndPayment);
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}