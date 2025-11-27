package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b17070 {
    public static int[][][] dp;
    public static int[][] map;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작지점 1,2 지만 코드에서는 0베이스로 한다.
        // 0은 가로 1은 세로 2는 대각선

        // dp[y][x][0] = dp[y][x-1][0] + dp[y][x-1][2];
        // dp[y][x][1] = dp[y-1][x][1] + dp[y-1][x][2];
        // dp[y][x][2] = dp[y-1][x-1][0] + dp[y-1][x-1][1] + dp[y-1][x-1][2];
        // result = dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2];
        dp[0][1][0] = 1;

        for (int y = 0; y < n; y++) {
            for (int x = 2; x < n; x++) {
                if (map[y][x] == 1) {
                    continue;
                } else {
                    // 가로이동
                    dp[y][x][0] = dp[y][x-1][0] + dp[y][x-1][2];

                    // 세로이동
                    if (y > 0) {
                        dp[y][x][1] = dp[y-1][x][1] + dp[y-1][x][2];
                    }

                    // 대각선이동
                    if (y > 0 && map[y - 1][x] == 0 && map[y][x - 1] == 0) {
                        dp[y][x][2] = dp[y-1][x-1][0] + dp[y-1][x-1][1] + dp[y-1][x-1][2];
                    }
                }
            }
        }
        long result = dp[n - 1][n - 1][0] + dp[n- 1][n - 1][1] + dp[n - 1][n - 1][2];
        System.out.println(result);
    }
}
