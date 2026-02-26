package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b2159 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] grapes = new int[count];
        int[] dp = new int[count];

        for (int i = 0; i < count; i++) {
            grapes[i] = Integer.parseInt(br.readLine());
        }

        // 6 10 13 9 8 1
        // 각 포도주 마다 먹지 않은 경우의 수 먹은 경우의 수
        // dp[1][0] = grapes[0]
        // dp[1][1] = 0
        // 이전의 최대 값을 따라
        // dp[2][0] = Math.max(dp[1][0], dp[1][1]) + grapes[1]
        // dp[2][1] = Math.max(dp[1][0], dp[1][1]) + 0
        // dp[3][0] = 이전에 두번 먹은 걸 어떻게 알지? 이전이 1이면 안된다고 하기엔 두 번으로 체크해야 함
        // 지금 이 잔을 마시기 위해 과거의 어느 시점을 희생해야하는가? 이전잔이나 두번째 전의 잔
        // 5번째잔의 최선은? 4번째를 안마시고 5번째를 마셨을때 최선은? 3번째를 안마시고 4,5번째를 마셨을때
        // 123 1안마시고 2,3 1,3 , 1 2 둘중 하나
        // 12 1안마시고 2 12
        // 1234 23 134 124
        // 12345 235 134 1245
        // dp[3] = Math.max(i[1] + i[3], i[2] + i[3], i[1] + i[2])
        // dp[4] = Math.max(i[2] + i[3], dp[3] + i[4])
        // dp[5] = Math.max(dp[3] + i[4], dp[4] + i[5])

        // 3이상일때 dp[n] = dp[n - 2] 와 dp[n - 1] + grape[n] 와 dp[n - 2] + grape[n - 1] + graph[n] 중에 가장 큰 것

        dp[0] = grapes[0];
        if (count == 1) {
            System.out.println(dp[dp.length - 1]);
            return;
        }

        dp[1] = grapes[0] + grapes[1];
        if (count == 2) {
            System.out.println(dp[dp.length - 1]);
            return;
        }

        dp[2] = Math.max(grapes[1] + grapes[2], Math.max(dp[1], grapes[0] + grapes[2]));
        if (count == 3) {
            System.out.println(dp[dp.length - 1]);
            return;
        }

        for (int i = 3; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + grapes[i] + grapes[i - 1], dp[i - 2] + grapes[i]));
        }

        System.out.println(dp[dp.length - 1]);

    }
}
