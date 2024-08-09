package com.cathaybk.practice.nt50346.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HRMain {
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));

		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File("d:/output.csv")), StandardCharsets.UTF_8))) {
			bw.write('\ufeff');

			Collections.sort(employeeList, new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {
					if (o1 instanceof Supervisor && !(o2 instanceof Supervisor)) {
						return -1;
					} else if (!(o1 instanceof Supervisor) && o2 instanceof Supervisor) {
						return 1;
					} else {
						return o1.getPayment() - o2.getPayment();
					}
				}
			});

			for (Employee employee : employeeList) {
				String getNameAndPayment = employee.getName() + ", " + employee.getPayment();
				bw.write(getNameAndPayment);
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}