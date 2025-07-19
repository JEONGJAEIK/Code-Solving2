package org.practice;

import java.io.*;
import java.util.*;

public class dds {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Map<Integer, List<Integer>> map = new java.util.HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int node = Integer.parseInt(st.nextToken());
        int line = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= node; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= line; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map.get(x).add(y);
            map.get(y).add(x);
        }

        for (int key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        boolean[] dfsVisited = new boolean[node + 1];

        dfs(start, dfsVisited);
        bw.write("\n");
        bfs(start, node);
        bw.close();
    }

    static void bfs(int current, int node) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[node + 1];
        queue.add(current);
        visited[current] = true;

        while (!queue.isEmpty()) {
            current = queue.poll();
            bw.write(current + " ");
            for (int next : map.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        bw.flush();
    }


    static void dfs(int current, boolean[] dfsVisited) throws IOException {
        dfsVisited[current] = true;
        bw.write(current + " ");

        for (int next : map.get(current)) {
            if (!dfsVisited[next]) {
                dfs(next, dfsVisited);
            }
        }
        bw.flush();
    }
}
