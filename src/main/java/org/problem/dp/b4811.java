package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 5 알약
public class b4811 {
    static long[][] dp = new long[31][31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 미리 dp 채워두기
        for (int w = 0; w <= 30; w++) {
            for (int h = 0; h <= 30; h++) {
                dp[w][h] = -1;
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            System.out.println(solve(n, 0));
        }
    }

    static long solve(int w, int h) {
        // 남은 통알약 w, 반쪽알약 h
        if (w == 0 && h == 0) return 1;
        if (dp[w][h] != -1) return dp[w][h];

        long res = 0;
        if (w > 0) res += solve(w - 1, h + 1); // 통알약 하나 꺼냄
        if (h > 0) res += solve(w, h - 1);     // 반쪽알약 하나 꺼냄

        return dp[w][h] = res;
    }
}
