package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 5 숨바꼭질
public class b13549 {
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static int[] distance;
    static int[] dx = {-1, 1, 2};
    static int[] timeX = {1, 1, 0};

    static class Node implements Comparable<Node> {
        int index; // 좌표 번호
        int time; // 시작점 에서 이 정점까지의 소요 시간

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }

        // 우선순위 큐에서 시간이 짧은 순서대로 정렬하기 위함 시간이 짧을 수록 우선 순위가 높음
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<List<Node>> graph = new ArrayList<>();

        int subin = Integer.parseInt(st.nextToken());
        int dongsang = Integer.parseInt(st.nextToken());
        int nodeCount = 100001;

        // 인접리스트 초기화
        for (int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        // 시작점에서 각 정점까지의 최단거리를 저장하느 배열 무한대로 초기화
        distance = new int[nodeCount];
        visited = new boolean[nodeCount];
        Arrays.fill(distance, Integer.MAX_VALUE);


        // 시작점 초기화
        distance[subin] = 0;
        pq.offer(new Node(subin, 0));
        dijkstra();

        System.out.println();
    }

    static void dijkstra() {

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.index;
            int currentTime = current.time;

            visited[currentIndex] = true;

            for (int i = 0; i < 3; i++) {
                int nextIndex;
                if (i == 2) {
                    nextIndex = currentIndex * dx[i];
                } else {
                    nextIndex = currentIndex + dx[i];
                }
                int nextTime = currentTime + timeX[i];

                if (nextIndex >= 0 && nextIndex < 100001) {
                    if (nextTime < distance[nextIndex]) {
                        distance[nextIndex] = nextTime;
                        pq.offer(new Node(nextIndex, nextTime));
                    }
                }
            }
        }
    }
}

