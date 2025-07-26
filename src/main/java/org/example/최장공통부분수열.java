package org.example;

public class 최장공통부분수열 {
    public static void main(String[] args) {

        if (A[i-1] == B[j-1]) {
            dp[i][j] = dp[i-1][j-1] + 1;
        } else {
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }


    }
}
