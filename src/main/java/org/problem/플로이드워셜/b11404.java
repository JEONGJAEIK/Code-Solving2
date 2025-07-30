package org.problem.플로이드워셜;

// 골드 4 플로이드
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b11404 {
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cityTotal = Integer.parseInt(br.readLine());
        int busTotal = Integer.parseInt(br.readLine());
        int[][] graph = new int[cityTotal + 1][cityTotal + 1];

        for (int i = 1; i < busTotal + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (graph[startCity][endCity] == 0 || graph[startCity][endCity] > cost) {
                graph[startCity][endCity] = cost;
            }
        }

        for (int i = 1; i < cityTotal + 1; i++) {
            for (int j = 1; j < cityTotal + 1; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else if(graph[i][j] == 0) {
                    graph[i][j] = INF;
                }
            }
        }

        for (int k = 1; k < cityTotal + 1; k++) {
            for (int i = 1; i < cityTotal + 1; i++) {
                for (int j = 1; j < cityTotal + 1; j++) {
                    if (graph[i][k] != INF && graph[k][j] != INF) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }

        for (int i = 1; i < cityTotal + 1; i++) {
            for (int j = 1; j < cityTotal + 1; j++) {
                if (graph[i][j] == INF) {
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < cityTotal + 1; i++) {
            for (int j = 1; j < cityTotal + 1; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
