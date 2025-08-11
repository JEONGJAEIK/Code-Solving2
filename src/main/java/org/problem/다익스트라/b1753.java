package org.problem.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 4 최단경로
public class b1753 {

    static List<List<Edge>> graph = new ArrayList<>();
    static int[] distance;

    static class Edge {
        int next;
        int cost;

        public Edge(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeTotal = Integer.parseInt(st.nextToken());
        int edgeTotal = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(br.readLine());

        for (int i = 1; i <= nodeTotal + 1; i++) {
            graph.add(new ArrayList<>());
        }

        distance = new int[nodeTotal + 1];
        for (int i = 1; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE / 2;
        }

        for (int i = 1; i < edgeTotal + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, cost));
        }

        distance[startNode] = 0;
        dijkstra(new Node(startNode, distance[startNode]));

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE / 2) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    static void dijkstra(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(start);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.index;
            int currentWeight = current.weight;

            List<Edge> edges = graph.get(currentIndex);
            for (Edge edge : edges) {
                int nextIndex = edge.next;
                int nextCost = edge.cost;

                if (currentWeight + nextCost < distance[nextIndex]) {
                    int newCost = currentWeight + nextCost;
                    distance[nextIndex] = newCost;
                    pq.offer(new Node(nextIndex, distance[nextIndex]));
                }
            }
        }
    }
}
