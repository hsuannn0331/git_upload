package com.cathaybk.practice.nt50346.b;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class RandomLotto {
	public static void main(String[] args) {
		Random rand = new Random();
		Set<Integer> randNumSet = new HashSet<>();
		while (randNumSet.size() < 6) {
			int randNum = getNum(rand);
			randNumSet.add(randNum);
		}
		System.out.println("排序前：" + setToString(randNumSet));
		
		Set<Integer> orderNumSet = new TreeSet<>(randNumSet);
		System.out.println("排序後：" + setToString(orderNumSet));
	}

	private static int getNum(Random rand) {
		return rand.nextInt(49) + 1;
	}

	private static String setToString(Set<Integer> set) {
		StringBuilder sb = new StringBuilder();
		for (Integer randNum : set) {
			sb.append(randNum + " ");
		}
		return sb.toString();
	}
}
