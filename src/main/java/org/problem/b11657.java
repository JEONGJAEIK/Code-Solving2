package org.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 골드 4 벨만포드
public class b11657 {
    static List<List<Edge>> graph = new ArrayList<>();
    static long[] dist;
    static int cityTotal;
    static int busTotal;

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cityTotal = Integer.parseInt(st.nextToken());
        busTotal = Integer.parseInt(st.nextToken());

        dist = new long[cityTotal + 1];
        for (int i = 1; i <= cityTotal; i++) {
            dist[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i <= cityTotal; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < busTotal; i++) {
            st = new StringTokenizer(br.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(startCity).add(new Edge(startCity, endCity, cost));
        }

        dist[1] = 0;

        for (int i = 1; i < cityTotal; i++) {
            for (int j = 1; j <= cityTotal; j++) {
                for (Edge edge : graph.get(j)) {
                    if (dist[edge.from] != Long.MAX_VALUE &&
                            dist[edge.to] > dist[edge.from] + edge.cost) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                    }
                }
            }
        }

        for (int j = 1; j <= cityTotal; j++) {
            for (Edge edge : graph.get(j)) {
                if (dist[edge.from] != Long.MAX_VALUE &&
                        dist[edge.to] > dist[edge.from] + edge.cost) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        for (int i = 2; i <= cityTotal; i++) {
            if (dist[i] == Long.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(dist[i]);
            }
        }
    }
}
