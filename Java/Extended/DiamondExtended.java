package com.cathaybk.practice.nt50346.b;

public class DiamondExtended {

	public static void main(String[] args) {
		int length = Math.ceilDiv(9, 2);
		for (int i = 1; i <= length; i++) {

			for (int s = 1; s <= length - i; s++) {
				System.out.print(" ");
			}

			for (int n = 1; n <= i * 2 - 1; n++) {
				System.out.print("*");
			}

			System.out.println();
		}

		for (int i = length - 1; i > 0; i--) {

			for (int s = 1; s <= length - i; s++) {
				System.out.print(" ");
			}

			for (int n = 1; n <= i * 2 - 1; n++) {
				System.out.print("*");
			}

			System.out.println();
		}
	}
}
