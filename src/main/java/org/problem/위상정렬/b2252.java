package org.problem.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드3 줄세우기
public class b2252 {
    static List<List<Integer>> graph = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static int[] degree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 총 학생 수 최대 32000
        int m = Integer.parseInt(st.nextToken()); // 키 비교 횟수 최대 10만

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        degree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 학생 번호
            int b = Integer.parseInt(st.nextToken()); // 학생 번호 a가 b보다 작다는 것을 뜻 함
            graph.get(a).add(b);
            degree[b]++; // 진입차수 증가
        }

        // 학생을 노드라고 보고 제일 작은 학생을 루트로 보고 BFS하면 되겠네
        // 진입차수 : 특정 노드로 오는 간선의 개수 한마디로 자기보다 작은 학생이 정의 되어있지 않은 학생
        // 전부 큐에 넣고 해당 학생보다 큰 학생과의 연결을 끊고 큐 밖으로 반환

        // 진입차수 0인 학생 추출
        List<Integer> degreeZero = new ArrayList<>();
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] == 0) {
                degreeZero.add(i);
            }
        }

        // 진입차수 0인 학생 큐에 삽입
        for (Integer i : degreeZero) {
            queue.offer(i);
        }

        bfs();
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Integer cs = queue.poll();
            for (Integer i : graph.get(cs)) {
                degree[i]--;
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }
            sb.append(cs).append(" ");
        }
    }
}

