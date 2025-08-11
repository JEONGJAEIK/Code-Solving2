package org.problem.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 5 숨바꼭질 3
public class b13549 {
    static int[] distance;
    static List<List<Edge>> graph = new ArrayList<>();
    static int subin;
    static int sister;

    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static class Edge {
        int next;
        int cost;

        public Edge(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        subin = Integer.parseInt(st.nextToken());
        sister = Integer.parseInt(st.nextToken());
        distance = new int[100001];

        Arrays.fill(distance, Integer.MAX_VALUE / 2);

        for (int i = 0; i <= 100001; i++) {
            graph.add(new ArrayList<>());
            graph.get(i).add(new Edge(-1, 1));
            graph.get(i).add(new Edge(1, 1));
            graph.get(i).add(new Edge(2, 0));
        }
        distance[subin] = 0;
        dijkstra(new Node(subin, distance[subin]));
        System.out.println(distance[sister]);
    }

    static void dijkstra(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(start);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.index;
            int currentCost = current.cost;

            List<Edge> edges = graph.get(currentIndex);
            for (Edge edge : edges) {
                int nextIndex;
                if (edge.next == 2) {
                    nextIndex = currentIndex * edge.next;
                } else {
                    nextIndex = currentIndex + edge.next;
                }
                int nextCost = currentCost + edge.cost;

                if (nextIndex >= 0 && nextIndex < distance.length) {
                    if (distance[nextIndex] > nextCost) {
                        distance[nextIndex] = nextCost;
                        pq.offer(new Node(nextIndex, distance[nextIndex]));
                    }
                }
            }
        }
    }
}

