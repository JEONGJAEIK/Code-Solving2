package org.practice.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class b11725 {

    static List<List<Integer>> graph = new ArrayList<>();
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(br.readLine());

        parent = new int[nodeCount + 1];
        visited = new boolean[nodeCount + 1];

        for (int i = 0; i <= nodeCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < nodeCount - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1);

        for (int i = 2; i <= nodeCount; i++) {
            System.out.println(parent[i]);
        }
    }

    static void dfs(int current) {
        visited[current] = true;

        for (int next : graph.get(current)) {
            if (!visited[next]) {
                parent[next] = current;
                dfs(next);
            }
        }
    }
}