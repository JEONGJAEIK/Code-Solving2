package org.practice.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버2 과일탕후루
public class b30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> countMap = new HashMap<>();

        int start = 0;
        int result = 0;

        for (int end = 0; end < n; end++) {
            int fruit = arr[end];
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) + 1);

            while (countMap.size() > 2) {
                int leftFruit = arr[start];
                countMap.put(leftFruit, countMap.get(leftFruit) - 1);
                if (countMap.get(leftFruit) == 0) {
                    countMap.remove(leftFruit);
                }
                start++;
            }
            result = Math.max(result, end - start + 1);

        }
        System.out.println(result);
    }
}
