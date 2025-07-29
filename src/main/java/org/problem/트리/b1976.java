package org.problem.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 4 여행 가자
public class b1976 {
    static class Node {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<List<Node>> tree;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();

        for (int i = 0; i < nodeCount + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < nodeCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            tree.get(from).add(new Node(to, dist));
            tree.get(to).add(new Node(from, dist));

        }

        visited = new boolean[nodeCount + 1];
        bfs(1);

        visited = new boolean[nodeCount + 1];
        maxDist = 0;
        bfs(farthestNode);

        System.out.println(maxDist);
    }

    static void bfs(int start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.weight > maxDist) {
                maxDist = now.weight;
                farthestNode = now.to;
            }

            for (Node next : tree.get(now.to)) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    queue.add(new Node(next.to, now.weight + next.weight));
                }
            }
        }
    }
}
