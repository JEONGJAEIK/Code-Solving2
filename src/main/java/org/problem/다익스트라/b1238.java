package org.problem.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 3 파티
// 문제
// N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
// 어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
// 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
// 이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

// 입력
// 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
// 모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.

// 출력
// 첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
public class b1238 {

    static int n; // 그래프 크기

    // 간선 클래스 그래프에 삽입
    static class Edge {
        int next; // 다음 도시
        int time; // 다음 도시 까지 걸리는 시간

        public Edge(int next, int time) {
            this.next = next;
            this.time = time;
        }
    }

    // 노드 클래스 우선순위 큐에 삽입
    static class Node implements Comparable<Node> {
        int index; // 도시 번호
        int time; // 시작 도시 부터 해당 도시 까지 오는 최소 시간

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }

        // Comparable 오버라이딩 우선순위 큐가 이걸 이용 작은게 우선
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int studentTotal = Integer.parseInt(st.nextToken());
        int roadTotal = Integer.parseInt(st.nextToken());
        int targetCity = Integer.parseInt(st.nextToken());

        // 파티 갈 때 그래프
        List<List<Edge>> graph = new ArrayList<>();
        // 집에 올 때 그래프
        List<List<Edge>> reverseGraph = new ArrayList<>();


        // 미리 그래프 초기롸
        for (int i = 0; i < 1001; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // 간선 삽입 (단방향)
        for (int i = 0; i < roadTotal; i++) {
            st = new StringTokenizer(br.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(startCity).add(new Edge(endCity, time));
            reverseGraph.get(endCity).add(new Edge(startCity, time));
        }

        n = graph.size();

        // 갈 때 다익스트라
        int[] go = dijkstra(graph, targetCity);
        // 올 때 다익스트라
        int[] back = dijkstra(reverseGraph, targetCity);

        // 최댓값 0부터 그래프 크기까지 거리배열이 INF가 아닐때만 고름
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (go[i] == Integer.MAX_VALUE || back[i] == Integer.MAX_VALUE) {
                continue;
            }
            max = Math.max(max, go[i] + back[i]);
        }
        System.out.println(max);
    }

    static int[] dijkstra(List<List<Edge>> graph, int start) {
        // 거리 배열 다익스트라 두번으로 지역변수
        int[] distance = new int[n];
        // 방문 배열 다익스트라 두번으로 지역변수
        boolean[] visited = new boolean[n];
        // 거리 배열 최댓값 초기화
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 자기자신 거리 0
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.index;
            int currentTime = current.time;

            visited[currentIndex] = true;

            for (Edge edge : graph.get(currentIndex)) {
                int neighbor = edge.next;
                int time = edge.time;

                if (!visited[neighbor]) {
                    int newTime = currentTime + time;

                    // 기존 출발지에서 특정 도시까지 가는 시간이 새로운 루트보다 긴 경우 교체하고 우선순위 큐에 삽입
                    if (newTime < distance[neighbor]) {
                        distance[neighbor] = newTime;
                        pq.offer(new Node(neighbor, newTime));
                    }
                }
            }
        }
        return distance;

    }
}
