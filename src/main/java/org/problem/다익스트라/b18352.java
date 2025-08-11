package org.problem.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버 2 특정 거리의 도시 찾기
public class b18352 {
    static List<List<Edge>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] distance;

    static class Edge {
        int nextCity;
        int length;

        public Edge(int nextCity, int length) {
            this.nextCity = nextCity;
            this.length = length;
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node (int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cityCount = Integer.parseInt(st.nextToken());
        int roadCount = Integer.parseInt(st.nextToken());
        int targetLength = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());

        for (int i = 0; i < cityCount + 1; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[cityCount + 1];
        distance = new int[cityCount + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < roadCount; i++) {
            st = new StringTokenizer(br.readLine());
            int beforeCity = Integer.parseInt(st.nextToken());
            int afterCity = Integer.parseInt(st.nextToken());
            graph.get(beforeCity).add(new Edge(afterCity, 1));
        }

        dijkstra(new Node(startCity, 0));
        int count = 0;

        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == targetLength) {
                System.out.println(i);
                count++;
            }
        }

        if (count == 0) {
            System.out.println(-1);
        }
    }

    static void dijkstra(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(start);
        distance[start.index] = 0;

        while (!pq.isEmpty()) {
            Node current= pq.poll();
            int cityDistance = current.distance;
            int currentIndex = current.index;
            visited[currentIndex] = true;

            List<Edge> edges = graph.get(currentIndex);
            for (Edge edge : edges) {
                int nextIndex = edge.nextCity;
                int nextDistance = edge.length;

                if (!visited[nextIndex]) {
                    if (distance[nextIndex] > cityDistance + nextDistance) {
                        int newDistance = cityDistance + nextDistance;
                        distance[nextIndex] = newDistance;
                        pq.offer(new Node(nextIndex, newDistance));
                    }
                }
            }
        }
    }
}

