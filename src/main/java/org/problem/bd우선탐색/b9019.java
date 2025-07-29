package org.problem.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 골드 4 DSLR
public class b9019 {
    static boolean[] visited = new boolean[10000];
    static int before, after;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            before = Integer.parseInt(st.nextToken());
            after = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            bfs();
        }
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(before, ""));

        visited[before] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.value == after) {
                System.out.println(cur.path);
                return;
            }

            // D
            int d = (cur.value * 2) % 10000;
            if (!visited[d]) {
                visited[d] = true;
                queue.offer(new Node(d, cur.path + "D"));
            }

            // S
            int s = (cur.value == 0) ? 9999 : cur.value - 1;
            if (!visited[s]) {
                visited[s] = true;
                queue.offer(new Node(s, cur.path + "S"));
            }

            // L
            int l = (cur.value % 1000) * 10 + (cur.value / 1000);
            if (!visited[l]) {
                visited[l] = true;
                queue.offer(new Node(l, cur.path + "L"));
            }

            // R
            int r = (cur.value % 10) * 1000 + (cur.value / 10);
            if (!visited[r]) {
                visited[r] = true;
                queue.offer(new Node(r, cur.path + "R"));
            }
        }
    }

    static class Node {
        int value;
        String path;

        public Node(int value, String path) {
            this.value = value;
            this.path = path;
        }
    }
}
