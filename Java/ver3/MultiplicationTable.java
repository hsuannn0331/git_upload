package com.cathaybk.practice.nt50346.b;

public class MultiplicationTable {

	public static void main(String[] args) {

		for (int sNum = 1; sNum <= 9; sNum++) {
			for (int fNum = 2; fNum <= 9; fNum++) {
				System.out.printf("%d*%d=%2d ", fNum, sNum, fNum * sNum);
			}
			System.out.println();
		}

	}

}
