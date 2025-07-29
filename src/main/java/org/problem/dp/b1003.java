package org.problem.dp;

import java.io.*;

// 실버 3 피보나치 함수
public class b1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[41][2];
            dp[0][0] = 1; // 0 호출 수
            dp[0][1] = 0; // 1 호출 수
            dp[1][0] = 0;
            dp[1][1] = 1;

            for (int i = 2; i <= 40; i++) {
                dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
                dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
            }
            System.out.println(dp[n][0] + " " + dp[n][1]);
            tc--;
        }
    }
}
