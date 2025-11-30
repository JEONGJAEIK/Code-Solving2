package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드 4 RGB거리 2
public class b17404 {
    static int[][] cost;
    static int houseCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        houseCount = Integer.parseInt(br.readLine()); // 집 최대 1000개

        cost = new int[houseCount][3];
        int[][] dp0 = new int[houseCount][3];
        int[][] dp1 = new int[houseCount][3];
        int[][] dp2 = new int[houseCount][3];
        for (int i = 0; i < houseCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); // 비용은 최대 1000원
            cost[i][0] = Integer.parseInt(st.nextToken()); // i집을 빨강으로 칠하는 비용
            cost[i][1] = Integer.parseInt(st.nextToken()); // i집을 초록으로 칠하는 비용
            cost[i][2] = Integer.parseInt(st.nextToken()); // i집을 파랑으로 칠하는 비용
        }

        // 1. 모든집은 이웃집과 색이 달라야한다.
        // 2. 제일 왼쪽집은 오른쪽집과 맨 끝집과 색이 달라야한다.
        // 3. 제일 오른쪽집은 왼쪽집과 맨 첫집과 색이 달라야한다.
        // 한마디로 모든 집은 원형을 이루고 있고 이웃집과 색이 달라야한다고 생각하면 된다.
        // 이것을 만족하는 비용의 최솟값을 출력한다.

        // dp[현재 집 번호][색깔] 0은 빨강 1은 초록 2는 파랑
        // dp[n][0] = Math.min(dp[n - 1][1] + dp[n - 1][2]) + cost[n][0]
        // dp1은 빨강으로 고정시킨다.
        dp0[0][0] = cost[0][0];
        dp0[0][1] = 1000001;
        dp0[0][2] = 1000001;

        dp1[0][0] = 1000001;
        dp1[0][1] = cost[0][1];
        dp1[0][2] = 1000001;

        dp2[0][0] = 1000001;
        dp2[0][1] = 1000001;
        dp2[0][2] = cost[0][2];

        dp(dp0);
        dp(dp1);
        dp(dp2);

        int result0 = Math.min(dp0[houseCount - 1][1], dp0[houseCount - 1][2]);
        int result1 = Math.min(dp1[houseCount - 1][0], dp1[houseCount - 1][2]);
        int result2= Math.min(dp2[houseCount - 1][0], dp2[houseCount - 1][1]);
        int result = Math.min(Math.min(result0, result1), result2);
        System.out.println(result);
    }

    static void dp(int[][] arr) {
        for (int i = 1; i < houseCount; i++) {
            arr[i][0] = Math.min(arr[i - 1][1], arr[i - 1][2]) + cost[i][0];
            arr[i][1] = Math.min(arr[i - 1][0], arr[i - 1][2]) + cost[i][1];
            arr[i][2] = Math.min(arr[i - 1][0], arr[i - 1][1]) + cost[i][2];
        }
    }
}
