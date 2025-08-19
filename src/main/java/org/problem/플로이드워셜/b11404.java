package org.problem.플로이드워셜;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드 4 플로이드
public class b11404 {
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cityTotal = Integer.parseInt(br.readLine());
        int busTotal = Integer.parseInt(br.readLine());

        graph = new int[cityTotal + 1][cityTotal + 1];

        for (int i = 0; i < busTotal; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (graph[startCity][endCity] != 0) {
                if (graph[startCity][endCity] > cost) {
                    graph[startCity][endCity] = cost;
                }
            } else {
                graph[startCity][endCity] = cost;
            }
        }

        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }


        for (int k = 1; k < graph.length; k++) {
            for (int i = 1; i < graph.length; i++) {
                for (int j = 1; j < graph.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++) {
                if (graph[i][j] == Integer.MAX_VALUE / 2) {
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++) {
                if (j == graph.length - 1) {
                    System.out.print(graph[i][j]);
                    System.out.println();
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
        }
    }
}
