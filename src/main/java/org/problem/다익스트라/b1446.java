package org.problem.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버 1 지름길
public class b1446 {
    static List<List<Edge>> graph = new ArrayList<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] distance;

    static class Edge implements Comparable<Edge> {
        int nextNode;
        int length;

        public Edge (int nextNode, int length) {
            this.nextNode = nextNode;
            this.length = length;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.length, other.length);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 12 이하 지름길
        int d = Integer.parseInt(st.nextToken()); // 10000이하

        for (int i = 0; i < d + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < d; i++) {
            graph.get(i).add(new Edge(i + 1, 1));
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 시작 위치
            int end = Integer.parseInt(st.nextToken()); // 도착 위치
            int length = Integer.parseInt(st.nextToken()); // 길이

            if (end <= d) {
                graph.get(start).add(new Edge(end, length));
            }
        }

        distance = new int[d + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        dijkstra(new Edge(0, distance[0]));
        System.out.println(distance[d]);
    }

    static void dijkstra(Edge edge) {
        pq.offer(edge);

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int nextNode = current.nextNode;
            int length = current.length;

            if (distance[nextNode] < length) {
                continue;
            }

            List<Edge> edges = graph.get(nextNode);
            for (Edge next : edges) {
                int cost = distance[nextNode] + next.length;

                if (cost < distance[next.nextNode]) {
                    distance[next.nextNode] = cost;
                    pq.offer(new Edge(next.nextNode, distance[next.nextNode]));
                }
            }
        }
    }
}
