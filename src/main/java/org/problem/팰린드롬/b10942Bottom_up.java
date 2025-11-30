package org.problem.팰린드롬;

import java.io.*;
import java.util.StringTokenizer;

// 골드4 팰린드롬?
public class b10942Bottom_up {
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 수열의 크기 최대 2000
        arr = new int[n + 1];
        dp = new int[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 숫자의 크기 각 10만 이하의 자연수
        }

        int m = Integer.parseInt(br.readLine()); // 질문의 개수 최대 100만

        // 시작과 끝이 팰린드롬인가?
        // dp[n][m]이 팰린드롬이려면 dp[n+1][m-1]이 팰린드롬이고 arr[n] == arr[m] 이여야한다.
        for (int i = 1; i < n + 1; i++) { // i는 수열의 길이 // 12345 3 345 2 3 4 2 1 1 1 3 3 3 5
            for (int j = 1; j < n - i + 2; j++) { // j는 시작점
                int end = j + i - 1; // 끝점  // 12345 1 5 5 4 4

                if (i == 1) {
                    dp[j][end] = 1;
                } else if (i == 2) {
                    if (arr[j] == arr[end]) {
                        dp[j][end] = 1;
                    }
                } else {
                    if (arr[j] == arr[end] && dp[j + 1][end - 1] == 1) {
                        dp[j][end] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 시작 인덱스
            int end = Integer.parseInt(st.nextToken()); // 끝 인덱스

            bw.write(dp[start][end] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
