package org.practice.트리;


import java.io.*;
import java.util.*;

public class b1167 {
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

        for (int i = 0; i <= nodeCount; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i <= nodeCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                tree.get(from).add(new Node(to, dist));
            }
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
