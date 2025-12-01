package org.problem.LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 3 LCS 2
public class b9252 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        dp = new int[a.length() + 1][b.length() + 1];

        // dp[n][m] = dp[n-1][m-1] + 1
        // dp[n][m] = Math.max(dp[n-1][m], dp[n][m-1])

        for (int i = 0; i < a.length() + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < b.length() + 1; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 역추적
        StringBuilder sb = new StringBuilder();
        int i = a.length();
        int j = b.length();
        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                sb.append(a.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        System.out.println(dp[a.length()][b.length()]);
        System.out.println(sb.reverse());
    }
}
