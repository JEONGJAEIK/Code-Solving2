package org.problem.최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 골드 4 최소 스패닝 트리
public class b1197 {
    static class Edge implements Comparable<Edge>{
        int startNode;
        int endNode;
        int cost;

        public Edge(int startNode, int endNode, int cost) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
    static List<Edge> graph = new ArrayList<>();

    static int[] parent;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeTotal = Integer.parseInt(st.nextToken());
        int edgeTotal = Integer.parseInt(st.nextToken());

        for (int i = 0; i < edgeTotal; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Edge(startNode, endNode, cost));
            graph.add(new Edge(endNode, startNode, cost));
        }

        parent = new int[nodeTotal + 1];

        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        int totalCost = 0;

        Collections.sort(graph);
        for (Edge edge : graph) {
            if (find(edge.startNode) != find(edge.endNode)) {
                union(edge.startNode, edge.endNode);
                totalCost += edge.cost;
            }
        }

        System.out.println(totalCost);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }
}
