package org.problem.다익스트라;

import java.io.*;
import java.util.*;

// 골드 4 특정한 최단 경로
public class b1504 {
    static int N, E;
    static List<List<Edge>> graph = new ArrayList<>();
    static int[] dist;

    static class Node implements Comparable<Node> {
        int index, distance;
        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    static class Edge {
        int next, cost;
        Edge(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 경우 1: 1 → v1 → v2 → N
        long case1 = (long) dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        // 경우 2: 1 → v2 → v1 → N
        long case2 = (long) dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        long answer = Math.min(case1, case2);

        // 하나라도 불가능하면 -1 출력
        if (answer >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static int dijkstra(int start, int end) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.distance > dist[cur.index]) continue; // 이미 더 짧은 경로가 있으면 skip

            for (Edge edge : graph.get(cur.index)) {
                int newDist = cur.distance + edge.cost;
                if (dist[edge.next] > newDist) { // 🔹 여기 조건이 핵심
                    dist[edge.next] = newDist;
                    pq.offer(new Node(edge.next, newDist));
                }
            }
        }
        return dist[end];
    }
}
