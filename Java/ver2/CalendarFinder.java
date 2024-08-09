package com.cathaybk.practice.nt50346.b;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalendarFinder {

	public static void main(String[] args) {
		Scanner inputMonth = new Scanner(System.in);
		int m = inputMonth.nextInt();
		inputMonth.close();
		System.out.println("輸入介於1-12間的整數m：" + m);

		Calendar calendar = new GregorianCalendar(2024, m - 1, 1);

		int firstDateOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.printf("              2024年%d月\n", m);
		System.out.println("----------------------------------");
		System.out.println("日    一    二    三    四    五    六");
		System.out.println("==================================");

		int countDayOfWeek = 0;

		for (int i = 0; i < firstDateOfWeek; i++) {
			System.out.print("     ");
			countDayOfWeek++;
		}

		for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
			System.out.printf("%3d  ", dayOfMonth);
			if (countDayOfWeek % 7 == 6) {
				System.out.println();
			}
			countDayOfWeek++;
		}

	}

}
