package org.problem.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드3 음악 프로그램
public class b2632 {
    static List<List<Integer>> graph = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static int[] indegree; // 진입차수
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 가수의 수 최대 1000
        int m = Integer.parseInt(st.nextToken()); // 보조 PD 수 최대 100

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        indegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken()); // 보조 PD 별 담당 가수 수
            int[] arr = new int[count];

            for (int j = 0; j < count; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < arr.length - 1; j++) {
                graph.get(arr[j]).add(arr[j + 1]);
                indegree[arr[j + 1]]++;
            }
        }

        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        bfs();
        if (count != n) {
            System.out.println(0);
        } else {
            System.out.print(sb);
        }
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            int cn = queue.poll();
            count++;
            for (int nn : graph.get(cn)) {
                indegree[nn]--;
                if (indegree[nn] == 0) {
                    queue.offer(nn);
                }
            }
            sb.append(cn).append("\n");
        }
    }
}
