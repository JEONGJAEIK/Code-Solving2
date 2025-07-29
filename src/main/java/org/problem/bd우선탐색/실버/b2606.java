package org.problem.bd우선탐색.실버;

import java.io.*;
import java.util.*;

// 실버 3 바이러스
public class b2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Integer, List<Integer>> map = new HashMap<>();

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());

        for (int i = 1; i <= a; i++) {
            map.put(i, new ArrayList<>());
        }


        for (int i = 1; i <= b; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map.get(c).add(d);
            map.get(d).add(c);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[a + 1];
        queue.add(1);
        visited[1] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : map.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    count++;
                }
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}
