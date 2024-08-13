package com.cathaybk.practice.nt50346.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RandomLottoExtended {
	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> randNumList = new ArrayList<>();

		while (randNumList.size() < 5) {
			int randNum = getNum(rand);
			if (!randNumList.contains(randNum)) {
				randNumList.add(randNum);
			}
		}

		System.out.print("排序前：");
		showResults(randNumList);

		Collections.sort(randNumList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.intValue() - o1.intValue();
			}
		});

		System.out.print("排序後：");
		showResults(randNumList);
	}

	private static int getNum(Random rand) {
		return rand.nextInt(49) + 1;
	}

	private static void showResults(List<Integer> randNumList) {
		for (Integer nums : randNumList) {
			System.out.print(nums + " ");
		}
		System.out.println();
	}
}
