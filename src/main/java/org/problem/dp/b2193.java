package org.problem.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

// 실버3 이친수
public class b2193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int i = sc.nextInt();
        // 0로 시작 X, 1연속 ㄴㄴ
        // dp[1] = 1 1
        // dp[2] = 1 10 // 끝이 1이면 1
        // dp[3] = 2 100 101 // 끝이 0이면 dp[1] + dp[2]
        // dp[4] = 3 1000 1010 1001 // dp[2] + dp[3]
        // dp[4] = 10000 10001 10100 10101 10010

        long[] dp = new long[91];

        dp[1] = 1;
        dp[2] = 2;

        for (int j = 2; j <= i; j++) {
            dp[j] = dp[j - 1] + dp[j - 2];
        }

        System.out.println(dp[i]);


    }
}
