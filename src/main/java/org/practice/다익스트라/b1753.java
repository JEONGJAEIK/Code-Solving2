package org.practice.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b1753 {
    static int[] dist;
    static boolean[] visited;
    static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList<>();

        int nodeCount = Integer.parseInt(st.nextToken());
        int lineCount = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(br.readLine());

        for (int i = 0; i < nodeCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < lineCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, value));
        }

        visited = new boolean[nodeCount + 1];
        dist = new int[nodeCount + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;
        dijkstra(startNode);

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    static void dijkstra(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.index;

            if (visited[now]) continue;
            visited[now] = true;

            for (Node next : graph.get(now)) {
                if (dist[next.index] > dist[now] + next.value) {
                    dist[next.index] = dist[now] + next.value;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
    }
}
