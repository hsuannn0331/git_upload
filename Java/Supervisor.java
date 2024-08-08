package com.cathaybk.practice.nt50346.b;

public class Supervisor extends Employee {

	private int payment;

	public Supervisor(String name, String department, int salary) {
		super(name, department, salary);
		this.payment = salary;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public void printInfo() {
		System.out.printf("姓名：%s 工作部門：%s\n月薪：%d\n總計：%d\n", getName(), getDepartment(), getSalary(), payment);
	}
}
