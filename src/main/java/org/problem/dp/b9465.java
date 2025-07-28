package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][2];
            int[][] dp = new int[n][2];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j][1] = Integer.parseInt(st.nextToken());
            }

            if (n == 1) {
                System.out.println(Math.max(arr[0][0], arr[0][1]));
                continue;
            }

            dp[0][0] = arr[0][0];
            dp[0][1] = arr[0][1];
            dp[1][0] = dp[0][1] + arr[1][0];
            dp[1][1] = dp[0][0] + arr[1][1];

            for (int j = 2; j < n; j++) {
                dp[j][0] = Math.max(dp[j - 1][1], dp[j - 2][1]) + arr[j][0];
                dp[j][1] = Math.max(dp[j - 1][0], dp[j - 2][0]) + arr[j][1];
            }


            System.out.println(Math.max(dp[dp.length - 1][0], dp[dp.length - 1][1]));
        }
    }
}
