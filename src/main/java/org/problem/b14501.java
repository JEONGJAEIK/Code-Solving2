package org.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버3 퇴사
public class b14501 {
    public static void main(String[] args) throws IOException {
        // 만약 특정날 상담을 했다면?
        // 아니다 시간관점으로
        // 뒤에서부터
        // dp[7] = 0
        // dp[6] = 0
        // dp[5] = 15
        // dp[4] = 35
        // dp[3] = 45
        // dp[2] = Math.max(dp[2 + 5 - 1) + arr[2]), dp[3))
        // dp[1] =
        // dp[4] = 4 즉 n은 arr[1] + arr[1]의 시간 -1 = 10
        // dp[6] = 20
        // dp[3] = 10
        // dp[4] = 20
        // dp[6] = 15
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        int[] costArr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[i] = time;
            costArr[i] = cost;
        }

        int[] dp = new int[n + 2];

        for (int i = n; i >= 1; i--) {

            if (i + arr[i] <= n + 1) {
                dp[i] = Math.max(dp[i + 1], costArr[i] + dp[i + arr[i]]);
            } else {
                dp[i] = dp[i + 1];
            }

        }

        System.out.println(dp[1]);
    }
}
