package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int itemCount = Integer.parseInt(st.nextToken());
        int weightLimit = Integer.parseInt(st.nextToken());

        int[] weight = new int[itemCount + 1];
        int[] value = new int[itemCount + 1];
        int[][] dp = new int[itemCount + 1][weightLimit + 1];

        for (int i = 1; i < itemCount + 1; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        // 정의 dp[n][w]은 w의 무게를 한도로하여 n번째 물건까지 골랐을 때의 최대의 효율이다.
        // 물건을 고르지 않는다 -> dp[n][w] = dp[n - 1][w]이다
        // 물건을 고른다 -> dp[n][w] = Math.max(dp[n - 1][w], dp[n - 1][w - weight[i]] + value[i])이다
        for (int i = 1; i < itemCount + 1; i++) {
            for (int j = 1; j < weightLimit + 1; j++) {
                    dp[i][j] = dp[i - 1][j];
                if (j >= weight[i])  {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        System.out.println(dp[itemCount][weightLimit]);
    }
}
