package org.pratice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 조합 {
    static int[] list = new int[]{1, 2, 3, 4, 5};
    static List<Integer> current = new ArrayList<>();
    static List<List<Integer>> combination = new ArrayList<>();
    static int targetCount = 2;


    public static void main(String[] args) {
        // 5개의 숫자를 2개씩해서 조합을 만들어보자
        combination(0);
        for (List<Integer> integers : combination) {
            System.out.println(integers);
        }
    }

    static void combination(int start) {
        if (current.size() >= targetCount) {
            combination.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < list.length; i++) {

            if (current.size() + (list.length - i) < targetCount) {
                break;
            }

            current.add(i);
            combination(i + 1);
            current.remove(current.size() - 1);
        }
    }
}
