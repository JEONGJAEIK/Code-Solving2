package org.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stairs = Integer.parseInt(br.readLine());

        int[] score = new int[stairs + 1]; // 계단 점수
        for (int i = 1; i <= stairs; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        if (stairs == 1) {
            System.out.println(score[1]);
            return;
        }

        int[] dp = new int[stairs + 1];
        dp[1] = score[1];
        dp[2] = score[1] + score[2];
        if (stairs >= 3) {
            dp[3] = Math.max(score[1] + score[3], score[2] + score[3]);

            for (int i = 4; i <= stairs; i++) {
                dp[i] = Math.max(dp[i - 2], dp[i - 3] + score[i - 1]) + score[i];
            }
        }

        System.out.println(dp[stairs]);
    }
}

