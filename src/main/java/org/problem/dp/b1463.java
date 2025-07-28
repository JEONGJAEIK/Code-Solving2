package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// dp[1] = 0
// dp[2] = 1
// dp[3] = 1
// dp[4] = 2
// dp[5] = 3
// dp[6] = 2
// dp[7] = 3
// dp[8] = 3
// dp[9] = 2
// dp[10] = 3
// 귀납으로 패턴 분석을 해보자
// 기본적으로 dp[N]은 dp[N + 1] + 1 이다 왜냐하면 + 1 더하는 것이 횟수의 + 1 이기 때문에
// 다만 N이 2로 나누어 지거나 3으로 나누어 질 때 dp[N] = dp[N / 2] + 1 이거나 dp[N / 3] + 1 이다
// 그렇기 때문에 가능한 경우의 수 중 가장 작은 숫자를 구하면 된다 예를 들어 6으로 나누어 떨어지는 경우 2, 3으로 나누기 + 1 하기 전부 가능하기 때문에 그 중에서의 최소값을 찾는다.
// 3으로 나누어 떨어지면 + 1과 3으로 나눈 것의 최소값을 찾는다.
public class b1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];

        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 3;
        dp[6] = 2;

        for (int i = 7; i < dp.length; i++) {
            if (i % 6 == 0) {
                dp[i] = Math.min(Math.min(dp[i / 3] + 1, dp[i / 2] + 1), dp[i - 1] + 1);
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i / 3] + 1, dp[i - 1] + 1);
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2] + 1, dp[i - 1] + 1);
            } else {
                dp[i] = dp[i - 1] + 1;
            }

        }
        System.out.println(dp[n]);
    }
}

