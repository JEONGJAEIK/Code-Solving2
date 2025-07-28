package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc > 0) {
            int n = Integer.parseInt(br.readLine());
            long[] dp = new long[101];
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;
            dp[5] = 2;

            if (n >= 6) {
                for (int i = 6; i < 100; i++) {
                    dp[i] = dp[i - 1] + dp[i - 5];
                }
            }


            System.out.println(dp[n]);
            tc--;
        }
    }
}
