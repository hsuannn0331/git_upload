package com.cathaybk.practice.nt50346.b;

import java.math.BigDecimal;

public class Supervisor extends Employee {

	private BigDecimal payment;

	public Supervisor(String name, String department, BigDecimal salary) {
		super(name, department, salary);
		this.payment = salary;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.printf("總計：%.0f\n", payment);
	}
}
