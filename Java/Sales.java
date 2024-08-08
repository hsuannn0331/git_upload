package com.cathaybk.practice.nt50346.b;

public class Sales extends Employee {

	private int bonus;
	private int payment;

	public Sales(String name, String department, int salary, int bonus) {
		super(name, department, salary);
		this.bonus = (int) Math.round(bonus * 0.05);
		this.payment = salary + this.bonus;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public void printInfo() {
		System.out.printf("姓名：%s 工作部門：%s\n月薪：%d\n業績獎金：%d\n總計：%d\n", getName(), getDepartment(), getSalary(), bonus,
				payment);
	}
}
