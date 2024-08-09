package com.cathaybk.practice.nt50346.b;

import java.util.Comparator;

public abstract class Employee implements IWork, Comparator<Employee> {

	private String name;
	private String department;
	private int salary;

	public Employee(String name, String department, int salary) {
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public abstract int getPayment();

	@Override
	public abstract void printInfo();

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getPayment() - o2.getPayment();
	}

}
