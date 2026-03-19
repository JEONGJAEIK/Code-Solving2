package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 골드 5 동전 2
public class b2292 {
    static int n;
    static int k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        int[] dp = new int[10001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 1 5 12 가 있을때
        // 12 1 1 1
        // 5 5 5
        // 등등이 있음
        // 선택의 경우 1을 선택 5를 선택 12를 선택
        // 1
        // 5
        // 12
        // 다시 선택의 경우
        // 1 1
        // 1 5
        // 1 12
        // 5 1
        // 5 5
        // 5 12
        // ...
        // 3가지 자식 트리 느낌
        // dp[현재 합] = 최소 숫자
        // dp[i] = Math.min(dp[i], dp[i - coin] + 1)
        for (int i = 0; i < n; i++) {
            for (int j = arr[i]; j <= k; j++) {
                if (dp[j - arr[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
                }
            }
        }

        if (dp[k] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
