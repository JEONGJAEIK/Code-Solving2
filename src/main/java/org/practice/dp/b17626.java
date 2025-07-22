package org.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실버 3 Four Squares
public class b17626 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];

        dp[0] = 0;
        //dp[1] = 1; // 1 -> 1제곱
        //dp[2] = 2; // 1 + 1 -> dp[1] + dp[1]
        //dp[3] = 3; // 1 + 1 + 1 -> dp[2] + dp[1]
        //dp[4] = 1; // 4 -> dp[3] - 2
        //dp[5] = 2; // 4 + 1 -> dp[4] + dp[1]
        //dp[6] = 3; // 4 + 1 + 1 -> dp[4] + dp[2]
        //dp[7] = 4; // 4 + 1 + 1 + 1 -> dp[4] + dp[3]
        //dp[8] = 2; // 4 + 4 -> dp[4] + dp[4]
        //dp[9] = 1; // 9 -> dp[8] - 1
        //dp[10] = 2; // 9 + 1 -> dp[9] + dp[1]

        // 우선 누군가의 제곱수는 무조건 1이다 예를들어 1, 4, 9 의 경우
        // 최소 개수의 제곱수의 합을 위해서 해당 dp 이전의 최대 제곱수를 빼면된다고 하기엔 그것이 전체 최적 해를 보장 못한다.
        // 그래서 자기자신보다 작은 제곱수들을 탐색하고 자기자신을 해당 제곱수들로 뺀 값 중 최소를 찾는다.

        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
