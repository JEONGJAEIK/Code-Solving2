package org.problem.팰린드롬;

import java.io.*;
import java.util.StringTokenizer;

// 골드4 팰린드롬?
public class b10942Top_down_시간초과 {
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

        for (int i = 1; i < n + 1; i++) {
            dp[i][i] = 1; // 자기 자신은 팰린드롬이다 0은 아직 안 구한 것 1은 팰린드롬 2는 팰린드롬이 아닌 것
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 시작 인덱스
            int end = Integer.parseInt(st.nextToken()); // 끝 인덱스
            // 시작과 끝이 팰린드롬인가?
            // dp[n][m]이 팰린드롬이려면 dp[n+1][m-1]이 팰린드롬이고 arr[n] == arr[m] 이여야한다.
            if (recursion(start, end)) {
                bw.write(1 + "\n");
            } else {
                bw.write(0 + "\n");
            }
        }
        bw.flush();
        bw.close();

    }

    // Top-down방식으로 한다.
    static boolean recursion(int start, int end) {
        if (dp[start][end] == 2) {
            return false;
        } else if (dp[start][end] == 1) {
            return true;
        } else {
            if (arr[start] == arr[end]) {
                // 단일 숫자면 무조건 팰린드롬
                if (start == end) {
                    return true;
                }

                // 붙어 있는 숫자도 팰린드롬 + DP 저장
                if (end - start == 1) {
                    dp[start][end] = 1;
                    return true;
                }
                // 3개 숫자 비교부터
                if (dp[start + 1][end - 1] == 1) {
                    dp[start][end] = 1;
                    return true;
                } else if (dp[start + 1][end - 1] == 2) {
                    dp[start][end] = 2;
                    return false;
                } else {
                    return recursion(start + 1, end - 1);
                }
            } else {
                // 숫자 다르면 무조건 팰린드롬 아님
                dp[start][end] = 2;
                return false;
            }
        }
    }
}
