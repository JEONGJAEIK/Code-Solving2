package org.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class d11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] dp = new long[1001];
        int n = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i < 1001; i++) {
            dp[i] = (dp[i - 1] + (2 * dp[i - 2])) % 10007;
        }
        System.out.println(dp[n]);
    }
}
