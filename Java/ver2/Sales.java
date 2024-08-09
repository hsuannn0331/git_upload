package com.cathaybk.practice.nt50346.b;

import java.math.BigDecimal;

public class Sales extends Employee {

	private BigDecimal bonus;
	private BigDecimal payment;

	public Sales(String name, String department, BigDecimal salary, BigDecimal bonus) {
		super(name, department, salary);
		BigDecimal multiplier = new BigDecimal("0.05");
		this.bonus = bonus.multiply(multiplier);
		this.payment = salary.add(this.bonus);
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
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
		System.out.printf("業績獎金：%.0f\n總計：%.0f\n", bonus, payment);
	}
}
