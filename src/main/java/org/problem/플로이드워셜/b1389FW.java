package org.problem.플로이드워셜;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버 1 케빈 베이컨의 6단계 법칙 플로이드 워셜 풀이
public class b1389FW {
    static final int INF = 1000000;
    static int[][] dist;
    static int userCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        userCount = Integer.parseInt(st.nextToken());
        int lineCount = Integer.parseInt(st.nextToken());

        dist = new int[userCount][userCount];

        // 거리 배열 초기화
        for (int i = 0; i < userCount; i++) {
            for (int j = 0; j < userCount; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        // 간선 입력
        for (int i = 0; i < lineCount; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        // Floyd-Warshall
        for (int k = 0; k < userCount; k++) {
            for (int i = 0; i < userCount; i++) {
                for (int j = 0; j < userCount; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 각 사용자별 케빈 베이컨 수 계산
        int minSum = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 0; i < userCount; i++) {
            int sum = 0;
            for (int j = 0; j < userCount; j++) {
                sum += dist[i][j];
            }
            if (sum < minSum) {
                minSum = sum;
                answer = i;
            }
        }

        System.out.println(answer + 1); // 출력은 1번부터
    }
}
