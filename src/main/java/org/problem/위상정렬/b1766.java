package org.problem.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 2 문제집
public class b1766 {
    static List<List<Integer>> graph = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int[] degree; // 진입차수
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 문제의 수 최소 1 최대 32000
        int m = Integer.parseInt(st.nextToken()); // 정보의 수 최소 1 최대 10만
        degree = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 먼저 푸는 문제가 1번째
        // 그다음 선후관계가 없다면 문제 번호가 작은 순으로 나열
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a번 문제는 b보다 먼저 푸는게 좋다는 뜻
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            degree[b]++;
        }

        // 진입차수가 0인 문제들 큐에 삽입
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] == 0) {
                pq.offer(i);
            }
        }

        bfs();
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void bfs() {
        while (!pq.isEmpty()) {
            int a = pq.poll();
            List<Integer> list = graph.get(a);
            for (Integer i : list) {
                degree[i]--;
                if (degree[i] == 0) {
                    pq.offer(i);
                }
            }
            sb.append(a).append(" ");
        }
    }
}
