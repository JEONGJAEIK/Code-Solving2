package org.problem.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 골드 4 가장 긴 바이토닉 부분 수열
public class b11054 {
    static int[] arr;
    static int[] increase;
    static int[] decrease;
    static int[] dp;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        increase = new int[n + 1];
        decrease = new int[n + 1];
        dp = new int[n + 1];
        result = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n + 1; i++) { // 1Based
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lcs();
        dcs();

        for (int i = 1; i < arr.length; i++) {
            // i를 봉우리로 가정
            dp[i] = increase[i] + decrease[i] - 1;
        }

        for (int i : dp) {
            if (i > result) {
                result = i;
            }
        }

        System.out.println(result);
    }

    // 최장 증가 수열
    static void lcs() {
        Arrays.fill(increase, 1);
        for (int i = 2; i < arr.length; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i]) {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }
            }
        }
    }

    // 최장 감소 수열
    static void dcs() {
        Arrays.fill(decrease, 1);
        for (int i = arr.length - 2; i >= 1; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    decrease[i] = Math.max(decrease[i], decrease[j] + 1);
                }
            }
        }
    }
}
