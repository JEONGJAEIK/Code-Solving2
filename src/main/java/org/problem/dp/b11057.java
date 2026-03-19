package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실버1 오르막수
public class b11057 {
    static int n;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[1002][10];

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // 현재 마지막 숫자가 무엇이냐에 따라 다름
        // 1이면 1 ~ 9까지 가능
        // dp[현재 라운드][숫자]
        // dp[1][0] = 1
        // dp[1][1] = 1
        // dp[2][0] = 1, 00가능
        // dp[2][1] = 2, 11 01 가능
        // dp[2][9] = 10, 09 19 29 ~~~ 99 가능
        // 자기보다 이전라운드에서 자기와 같거나 작은 dp를 더한다
        // dp[n][m] = dp[n - 1][p] p는 0 <= p <= m

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9 ; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += (dp[i - 1][k] % 10007);
                }
            }
        }

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            result += (dp[n][i] % 10007);
        }

        result = result % 10007;
        System.out.println(result);
    }
}
