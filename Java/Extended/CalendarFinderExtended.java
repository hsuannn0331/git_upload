package com.cathaybk.practice.nt50346.b;

import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

public class CalendarFinderExtended {

	public static void main(String[] args) {
		Scanner inputMonth = new Scanner(System.in);
		System.out.print("輸入介於1-12間的整數m：");
		int m = inputMonth.nextInt();
		inputMonth.close();

		int currentYear = Year.now().getValue();
		LocalDate getMonth = LocalDate.of(currentYear, m, 1);

		int firstDateOfWeek = getMonth.getDayOfWeek().getValue() % 7;
		int daysInMonth = getMonth.lengthOfMonth();

		System.out.printf("              %d年%d月\n", currentYear, m);
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
