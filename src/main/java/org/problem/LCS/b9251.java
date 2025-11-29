package org.problem.LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 5 LCS
public class b9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char a1 = a.charAt(i - 1);
                char b1 = b.charAt(j - 1);

                if (a1 == b1) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[n][m]);

        StringBuilder sb = new StringBuilder();
        while (n > 0 && m > 0) {
            char a1 = a.charAt(n - 1);
            char b1 = b.charAt(m - 1);

            if (a1 == b1) {
                sb.append(a1);
                n--;
                m--;
            } else if (dp[n - 1][m] > dp[n][m - 1]) {
                n--;
            } else {
                m--;
            }
        }
        System.out.println(sb.reverse());
    }
}
