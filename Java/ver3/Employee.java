package com.cathaybk.practice.nt50346.b;

import java.math.BigDecimal;

public class Employee implements IWork {

	private String name;
	private String department;
	private BigDecimal salary;

	public Employee(String name, String department, BigDecimal salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public void printInfo() {
		System.out.printf("薪資單\n姓名：%s 工作部門：%s\n月薪：%.0f\n", name, department, salary);
	}

}