package org.problem.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 골드 3 소수의 연속합
public class b1644 {
    static int[] arr;
    static boolean[] isPrime;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 최대 400만
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        // n 까지의 에라스토테네스 체
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        arr = new int[count];
        int count2 = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                arr[count2] = i;
                count2++;
            }
        }

        // 소수 배열 정렬
        Arrays.sort(arr);

        // 투포인터 시작
        // 연속되어야해서 같이 움직인다.
        int start = 0;
        int end = 1;

        while (start < arr.length && end != arr.length + 1) {
            int tempSum = 0;
            for (int i = start; i < end; i++) {
                tempSum += arr[i];
            }

            if (tempSum < n) {
                end++;
            } else if (tempSum > n) {
                start++;
            } else {
                result++;
                end++;
            }
        }

        System.out.println(result);
    }
}
