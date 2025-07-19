package org.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실버 3
public class b9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[n + 1];
            dp[0] = 1; // 0을 만드는 방법은 1가지 (아무것도 선택하지 않는 경우)
            if (n >= 1) dp[1] = 1; // 1을 만드는 방법은 1가지 (1)
            if (n >= 2) dp[2] = 2; // 2를 만드는 방법은 2가지 (1+1, 2)
            if (n >= 3) dp[3] = 4; // 3을 만드는 방법은 4가지 (1+1+1, 1+2, 2+1, 3)
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
            System.out.println(dp[n]);
            tc --;
        }
    }
}
