package com.cathaybk.practice.nt50346.b;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateFormatExtended {

	public static void main(String[] args) {

		try {
			Scanner input = new Scanner(System.in);
			System.out.print("輸入格式為yyyy/MM/dd的日期：");
			String inputDate = input.nextLine();
			input.close();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate date = LocalDate.parse(inputDate, formatter);
			int dayOfWeek = date.getDayOfWeek().getValue();
			System.out.println(date + "為星期" + dayOfWeek);
		} catch (DateTimeParseException e) {
			System.out.println("輸入格式錯誤");
			e.printStackTrace();
		}

	}

}
