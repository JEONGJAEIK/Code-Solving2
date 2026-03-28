package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 5 1, 2, 3 더하기 4
public class b15989 {
    static int[] arr = new int[]{1, 2, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        // dp[현재금액] = 방법 수 = dp[현재금액 - 특정금액] = 방법 수, + dp[현재금액 - 특정금액 - 특정금액]... 반복

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[10001];
            dp[0] = 1;

            for (int coin : arr) {
                for (int j = coin; j <= 10000; j++) {
                    dp[j] += dp[j - coin];
                }
            }

            System.out.println(dp[n]);
        }
    }
}






//  재귀버전 시간초과
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int t = Integer.parseInt(br.readLine());
//
//        for (int i = 0; i < t; i++) {
//            N = Integer.parseInt(br.readLine());
//            count = 0;
//            recursion(0, 0);
//            System.out.println(count);
//        }
//    }
//
//    static void recursion(int sum, int idx) {
//        if (sum == N) {
//            count++;
//            return;
//        }
//
//        if (idx == arr.length || sum > N) {
//            return;
//        }
//
//        for (int i = idx; i < arr.length; i++) {
//            recursion(sum + arr[i], i);
//        }
//    }
// }
