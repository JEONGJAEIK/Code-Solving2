package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1904 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        long[] dp = new long[1000001]; // 00이 붙어있음
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2; // 00 11
        dp[3] = 3; // 001 111 100
        dp[4] = 5; // 0011 1111 0000 1100 1001

        for (int i = 5; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        long result = dp[n] % 15746;
        System.out.println(result);
    }
}
