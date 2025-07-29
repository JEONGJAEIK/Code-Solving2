package org.example.다익스트라;

import java.util.*;
import java.io.*;

/*
=== 다익스트라 알고리즘 기본 개념 ===
1. 정의: 한 정점에서 다른 모든 정점까지의 최단 거리를 구하는 알고리즘
2. 핵심 원리:
   - 시작점에서 가장 가까운 정점부터 차례대로 방문
   - 방문한 정점을 거쳐서 다른 정점으로 가는 거리를 갱신
   - 우선순위 큐를 사용해 항상 최단 거리인 정점부터 처리
3. 시간복잡도: O((V+E)logV) - V는 정점 수, E는 간선 수
4. 공간복잡도: O(V)
5. 전제조건: 음의 가중치가 없어야 함

=== 코딩테스트에서의 활용 ===
1. 최단 거리 문제 (단일 출발점)
2. 가중치가 있는 그래프에서의 경로 찾기
3. 네트워크 라우팅, GPS 길찾기 등의 실생활 문제
4. 상태 공간 탐색 (이 문제처럼 위치를 상태로 보는 경우)

=== 유사 알고리즘과 구분점 ===
1. BFS vs 다익스트라:
   - BFS: 모든 간선의 가중치가 1일 때만 최단거리 보장
   - 다익스트라: 서로 다른 가중치를 가진 간선에서 최단거리 보장
   - 이 문제에서 순간이동(0초)과 걷기(1초)의 가중치가 다르므로 다익스트라 필요

2. 플로이드-워셜 vs 다익스트라:
   - 플로이드-워셜: 모든 정점 쌍 간의 최단거리 (O(V³))
   - 다익스트라: 한 정점에서 모든 정점까지의 최단거리 (O((V+E)logV))

3. 벨만-포드 vs 다익스트라:
   - 벨만-포드: 음의 가중치 허용, 느림 (O(VE))
   - 다익스트라: 음의 가중치 불허용, 빠름

주의사항: 가중치가 모두 같다고 해서 무조건 BFS를 쓰는 것이 아니라,
문제의 특성을 파악해야 함. 이 문제는 가중치가 0과 1로 다르므로 다익스트라가 필요!
*/

public class 다익스트라숨바꼭질 {
    // 정점(위치)을 나타내는 클래스
    // 다익스트라에서 우선순위 큐에 들어갈 객체
    static class Node implements Comparable<Node> {
        int position;  // 현재 위치
        int time;      // 시작점부터 이 위치까지 오는데 걸린 시간

        Node(int position, int time) {
            this.position = position;
            this.time = time;
        }

        // 우선순위 큐에서 시간이 적게 걸린 노드가 먼저 나오도록 설정
        // 이것이 다익스트라의 핵심: 항상 최단 거리인 정점부터 처리
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.time, other.time);
        }

        @Override
        public String toString() {
            return String.format("위치:%d, 시간:%d", position, time);
        }
    }

    static final int MAX_POSITION = 100000;  // 문제에서 주어진 최대 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("=== 백준 13549 숨바꼭질3 - 다익스트라 알고리즘 시각화 ===");
        System.out.println("수빈이의 위치 N과 동생의 위치 K를 입력하세요 (0 ≤ N, K ≤ 100,000):");
        System.out.print("N K: ");

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 수빈이의 시작 위치
        int K = Integer.parseInt(st.nextToken());  // 동생의 위치 (목표)

        // 다익스트라 알고리즘 실행 및 결과 출력
        int result = dijkstra(N, K);

        System.out.println("\n=== 최종 결과 ===");
        System.out.printf("수빈이가 동생을 찾는 최소 시간: %d초\n", result);
    }

    static int dijkstra(int start, int target) {
        System.out.println("\n=== 다익스트라 알고리즘 시작 ===");
        System.out.printf("시작 위치: %d, 목표 위치: %d\n\n", start, target);

        // 거리 배열: distance[i] = 시작점에서 위치 i까지의 최단 시간
        // 초기값을 무한대(Integer.MAX_VALUE)로 설정
        int[] distance = new int[MAX_POSITION + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 방문 여부를 체크하는 배열
        // 한번 방문된 정점은 다시 처리하지 않음 (다익스트라의 핵심 최적화)
        boolean[] visited = new boolean[MAX_POSITION + 1];

        // 우선순위 큐: 시간이 적게 걸린 노드가 먼저 나옴
        // 이것이 다익스트라가 BFS와 다른 핵심 부분!
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작점 초기화
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        System.out.println("초기 상태:");
        System.out.printf("distance[%d] = 0 (시작점)\n", start);
        System.out.println("우선순위 큐에 시작 노드 추가\n");

        int step = 1;  // 단계 번호 (출력용)

        // 다익스트라 메인 루프
        while (!pq.isEmpty()) {
            // 현재 가장 가까운 정점을 꺼냄
            // 우선순위 큐 덕분에 항상 최단 거리인 정점이 나옴
            Node current = pq.poll();
            int currentPos = current.position;
            int currentTime = current.time;

            System.out.printf("=== Step %d ===\n", step++);
            System.out.printf("우선순위 큐에서 꺼낸 노드: %s\n", current);

            // 이미 방문했다면 건너뛰기
            // 우선순위 큐 특성상 같은 정점이 여러 번 들어갈 수 있음
            // 하지만 먼저 나온 것이 항상 최단 거리이므로, 나중에 나온 것은 무시
            if (visited[currentPos]) {
                System.out.println("→ 이미 방문한 위치이므로 건너뜀\n");
                continue;
            }

            // 현재 정점을 방문 처리
            visited[currentPos] = true;
            System.out.printf("→ 위치 %d 방문 처리 (최단 시간: %d초)\n", currentPos, currentTime);

            // 목표에 도달했다면 종료
            if (currentPos == target) {
                System.out.printf("→ 목표 위치 %d에 도달! 최단 시간: %d초\n\n", target, currentTime);
                printFinalTable(distance, start, target);
                return currentTime;
            }

            // 현재 위치에서 갈 수 있는 모든 위치를 확인
            List<Node> neighbors = getNeighbors(currentPos, currentTime);
            System.out.println("→ 인접한 위치들을 확인:");

            for (Node next : neighbors) {
                int nextPos = next.position;
                int nextTime = next.time;

                // 유효한 범위 체크
                if (nextPos < 0 || nextPos > MAX_POSITION) {
                    continue;
                }

                // 이미 방문한 정점은 건너뛰기
                if (visited[nextPos]) {
                    System.out.printf("   위치 %d: 이미 방문함 (건너뜀)\n", nextPos);
                    continue;
                }

                // 거리 갱신 (릴랙세이션)
                // 새로 찾은 경로가 기존 경로보다 짧다면 갱신
                if (nextTime < distance[nextPos]) {
                    System.out.printf("   위치 %d: %d초 → %d초로 갱신 (우선순위 큐 추가)\n",
                            nextPos, distance[nextPos] == Integer.MAX_VALUE ? -1 : distance[nextPos], nextTime);
                    distance[nextPos] = nextTime;
                    pq.offer(new Node(nextPos, nextTime));
                } else {
                    System.out.printf("   위치 %d: 기존 시간(%d초)이 더 짧음 (갱신 안함)\n",
                            nextPos, distance[nextPos]);
                }
            }

            System.out.println();
            printCurrentTable(distance, visited, start, target, step - 1);
        }

        // 목표에 도달할 수 없는 경우 (이 문제에서는 발생하지 않음)
        return -1;
    }

    // 현재 위치에서 갈 수 있는 모든 인접 위치를 반환
    // 숨바꼭질 문제의 이동 규칙을 구현
    static List<Node> getNeighbors(int currentPos, int currentTime) {
        List<Node> neighbors = new ArrayList<>();

        // 1. 순간이동: 2 * X (시간 0초 추가)
        // 순간이동은 시간이 안 걸리므로 가중치 0
        if (currentPos > 0) {  // 0에서는 순간이동 불가 (0*2 = 0이므로 의미없음)
            neighbors.add(new Node(currentPos * 2, currentTime));
        }

        // 2. 걷기: X-1 (시간 1초 추가)
        neighbors.add(new Node(currentPos - 1, currentTime + 1));

        // 3. 걷기: X+1 (시간 1초 추가)
        neighbors.add(new Node(currentPos + 1, currentTime + 1));

        return neighbors;
    }

    // 현재 상태를 표로 출력하는 함수
    static void printCurrentTable(int[] distance, boolean[] visited, int start, int target, int step) {
        System.out.printf("Step %d 후의 상태:\n", step);
        System.out.println("┌────────┬────────┬─────────┬──────────┐");
        System.out.println("│  위치  │  거리  │ 방문여부 │   상태   │");
        System.out.println("├────────┼────────┼─────────┼──────────┤");

        // 시작점 주변과 목표점 주변의 중요한 위치들만 출력
        Set<Integer> importantPositions = new TreeSet<>();
        importantPositions.add(start);
        importantPositions.add(target);

        // 방문한 위치들과 거리가 갱신된 위치들 추가
        for (int i = 0; i <= MAX_POSITION; i++) {
            if (visited[i] || distance[i] != Integer.MAX_VALUE) {
                importantPositions.add(i);
                // 주변 위치도 추가 (더 나은 시각화를 위해)
                if (i > 0) importantPositions.add(i - 1);
                if (i < MAX_POSITION) importantPositions.add(i + 1);
            }
        }

        // 너무 많은 위치가 출력되지 않도록 제한
        int count = 0;
        for (int pos : importantPositions) {
            if (count >= 15) {  // 최대 15개까지만 출력
                System.out.println("│   ...  │   ...  │   ...   │   ...    │");
                break;
            }

            String distStr = distance[pos] == Integer.MAX_VALUE ? "∞" : String.valueOf(distance[pos]);
            String visitStr = visited[pos] ? "O" : "X";
            String status = "";

            if (pos == start) status = "시작점";
            else if (pos == target) status = "목표점";
            else if (visited[pos]) status = "방문완료";
            else if (distance[pos] != Integer.MAX_VALUE) status = "대기중";
            else status = "미방문";

            System.out.printf("│ %6d │ %6s │ %7s │ %8s │\n", pos, distStr, visitStr, status);
            count++;
        }

        System.out.println("└────────┴────────┴─────────┴──────────┘\n");
    }

    // 최종 결과 표 출력
    static void printFinalTable(int[] distance, int start, int target) {
        System.out.println("=== 최종 최단 거리 테이블 ===");
        System.out.println("┌────────┬────────┬──────────┐");
        System.out.println("│  위치  │ 최단시간 │   설명   │");
        System.out.println("├────────┼────────┼──────────┤");

        // 시작점부터 목표점까지의 경로상 중요한 위치들만 출력
        Set<Integer> importantPositions = new TreeSet<>();

        for (int i = 0; i <= MAX_POSITION; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                importantPositions.add(i);
            }
        }

        int count = 0;
        for (int pos : importantPositions) {
            if (count >= 20) {  // 최대 20개까지만 출력
                System.out.println("│   ...  │   ...  │   ...    │");
                break;
            }

            String explanation = "";
            if (pos == start) explanation = "시작점";
            else if (pos == target) explanation = "목표점";
            else explanation = "방문함";

            System.out.printf("│ %6d │ %6d │ %8s │\n", pos, distance[pos], explanation);
            count++;
        }

        System.out.println("└────────┴────────┴──────────┘");
    }
}