package org.problem.배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b1106 {
    static int result = Integer.MAX_VALUE;
    static int[] memberArr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int target = Integer.parseInt(st.nextToken());
        int cityCount = Integer.parseInt(st.nextToken());

        // 고객 수 1~100 중 최소 비용만 저장
        memberArr = new int[101];
        Arrays.fill(memberArr, Integer.MAX_VALUE);

        // dp는 target + 100까지 필요 (고객수 최대 100)
        dp = new int[target + 101];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 입력
        for (int i = 0; i < cityCount; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int member = Integer.parseInt(st.nextToken());
            memberArr[member] = Math.min(memberArr[member], cost);
        }

        // dp 계산
        for (int i = 1; i < dp.length; i++) {
            computeDP(i);
        }

        // target 이상 중 최소 비용
        for (int i = target; i < dp.length; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }

    static void computeDP(int n) {
        int limit = Math.min(n, 100);
        int best = Integer.MAX_VALUE;

        for (int i = 1; i <= limit; i++) {
            if (memberArr[i] == Integer.MAX_VALUE) continue;  // 없는 경우 skip
            if (dp[n - i] == Integer.MAX_VALUE) continue;     // 이전 상태 불가능 skip

            best = Math.min(best, dp[n - i] + memberArr[i]);
        }

        dp[n] = best;
    }
}
