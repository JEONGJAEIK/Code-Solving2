package org.problem.벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 골드 3 웜홀
public class b1835 {
    static final int INF = Integer.MAX_VALUE / 2;

    static class Edge {
        int from;
        int to;
        int time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeCount = Integer.parseInt(st.nextToken());
            int roadCount = Integer.parseInt(st.nextToken());
            int wormholeCount = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();

            for (int j = 1; j < roadCount + 1; j++) {
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                edges.add(new Edge(startNode, endNode, time));
                edges.add(new Edge(endNode, startNode, time));
            }
            for (int j = 1; j < wormholeCount + 1; j++) {
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                edges.add(new Edge(startNode, endNode, -time));
            }

            int[] dist = new int[nodeCount + 1];
            for (int j = 1; j <= nodeCount; j++) {
                edges.add(new Edge(0, j, 0));
            }

            boolean result = bellmanFord(edges, nodeCount + 1, dist);


            if (result) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    static boolean bellmanFord(List<Edge> edges, int nodeCount, int[] dist) {
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int i = 0; i < nodeCount - 1; i++) {
            boolean updated = false;

            for (Edge edge : edges) {
                int startNode = edge.from;
                int endNode = edge.to;
                int time = edge.time;

                if (dist[startNode] != INF && dist[startNode] + time < dist[endNode]) {
                    int oldDist = dist[endNode];
                    dist[endNode] = dist[startNode] + time;
                    updated = true;
                }
            }
        }

        for (Edge edge : edges) {
            int startNode = edge.from;
            int endNode = edge.to;
            int time = edge.time;

            if (dist[startNode] != INF && dist[startNode] + time < dist[endNode]) {
                return true;
            }
        }
        return false;
    }
}
