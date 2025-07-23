package org.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 실버 1 RGB거리를 DP로 푼 문제
// 원래 DP문제인데 다익스트라로 착각하고 풀어서 다익스트라로 푼 것은 다익스트라 폴더에 넣어뒀다
public class b1149DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int houseCount = Integer.parseInt(br.readLine());

        int[][] arr = new int[houseCount][3];
        int[][] dp = new int[houseCount][3];

        for (int i = 0; i < houseCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번 집은 같다
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        //dp[1][0] = Math.min(dp[0][1], dp[0][2]);
        //dp[1][1] = Math.min(dp[0][0], dp[0][2]);
        //dp[1][2] = Math.min(dp[0][0], dp[0][1]);

        for (int i = 1; i < houseCount; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j + 1], dp[i - 1][j + 2]) + arr[i][j];
                } else if (j == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]) + arr[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j - 2]) + arr[i][j];
                }
            }
        }

        int min = Math.min(dp[houseCount - 1][0], Math.min(dp[houseCount - 1][1], dp[houseCount - 1][2]));
        System.out.println(min);
    }
}
