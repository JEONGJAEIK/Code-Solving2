package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실버1 쉬운 계단 수
public class b10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 좌우의 인접한 수의 차이가 1일때 성립
        // 계단수가 1이면 길이가 1인 계단수 1,2,3,4,5,6,7,8,9
        // 시작할때 0은 제외
        // 계단수 2
        // 1, 0
        // 1, 2
        // 2, 3
        // 2, 1
        // 3, 2
        // 3, 4
        // 4, 3
        // 4, 5
        // 5, 4
        // 5, 6
        // 6, 5
        // 6, 7
        // 7, 6
        // 7, 8
        // 8, 7
        // 8, 9
        // 9, 8
        // 17개

        // 1, 0, 1
        // 1, 2, 1
        // 1, 2, 3
        // 2, 1, 0
        // 2, 3, 1

        // 계단수가 1일때의 결과로부터 계단수가 2일때의 결과가 생김
        // 처음 계단으로부터 올라가거나 내려가거나 2가지의 경우의수 다만 현재 끝 계단이 0이나 9라면 경우의 수는 하나
        long[][] dp = new long[10][101];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[3][1] = 1;
        dp[4][1] = 1;
        dp[5][1] = 1;
        dp[6][1] = 1;
        dp[7][1] = 1;
        dp[8][1] = 1;
        dp[9][1] = 1;

//        dp[2][2] = dp[1][1] + dp[3][1]; // 2
//        dp[1][3] = dp[0][2] + dp[2][2];
        // dp[n][m] n = 현재 숫자, m은 현재 나열된 곳
        // dp[n][m] = dp[n - 1][m - 1] + dp[n + 1][m - 1] 중 더 큰 값 그런데 m이 0이면 dp[n + 1][m - 1] 9이면 dp[n - 1][m - 1]

        for (int j = 2; j <= n; j++) {
            for (int i = 0; i <= 9; i++) {
                if (i == 0) {
                    dp[i][j] = dp[i + 1][j - 1] % 1_000_000_000;
                    continue;
                }

                if (i == 9) {
                    dp[i][j] = dp[i - 1][j - 1] % 1_000_000_000;
                    continue;
                }

                dp[i][j] = (dp[i - 1][j - 1] + dp[i + 1][j - 1]) % 1_000_000_000;
            }
        }


        long sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += dp[i][n];
        }

        System.out.println(sum);

    }
}
