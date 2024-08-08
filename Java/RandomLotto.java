package com.cathaybk.practice.nt50346.b;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class RandomLotto {
    public static void main(String[] args) {
        Set<Integer> randNumSet = new HashSet<>();
        Set<Integer> orderNumSet = new TreeSet<>();
        while (orderNumSet.size() < 6) {
            int randNum = getNum();            
            orderNumSet.add(randNum);
            if (!randNumSet.contains(randNum)) {
                randNumSet.add(randNum);
            }
        }
        System.out.println("排序前：" + setToString(randNumSet));
        System.out.println("排序後：" + setToString(orderNumSet));
    }

    private static int getNum() {
        Random rand = new Random();
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
