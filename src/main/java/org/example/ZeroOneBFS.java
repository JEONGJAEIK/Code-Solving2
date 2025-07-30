package org.example;

import java.util.*;

/*
 * ===== 0-1 BFS 알고리즘 상세 개념 =====
 *
 * 0-1 BFS란?
 * - 가중치가 0 또는 1로만 이루어진 그래프에서 최단경로를 구하는 특수한 BFS
 * - 일반 BFS는 모든 간선의 가중치가 동일할 때만 최단경로를 보장
 * - 다익스트라는 모든 양의 가중치에서 작동하지만 우선순위 큐로 인해 느림
 * - 0-1 BFS는 0, 1 가중치 한정이지만 일반 큐보다 빠른 덱을 사용해 효율적
 *
 * 핵심 아이디어:
 * 1. 덱(Deque, 양방향 큐)을 사용
 * 2. 가중치 0인 간선 → 덱의 앞쪽(front)에 추가 → 다음에 우선 처리
 * 3. 가중치 1인 간선 → 덱의 뒤쪽(back)에 추가 → 나중에 처리
 * 4. 이렇게 하면 비용이 적은 경로부터 자동으로 탐색됨
 *
 * 왜 이게 최단경로를 보장하는가?
 * - 덱에서 꺼내는 순서 = 비용이 작은 순서
 * - 어떤 정점에 처음 도달했을 때의 거리가 이미 최단거리
 * - 나중에 더 큰 비용으로 도달해도 이미 최적해를 찾은 상태
 *
 * 시간복잡도: O(V + E) - V는 정점 수, E는 간선 수
 * 공간복잡도: O(V) - 방문 배열과 덱의 크기
 *
 * 선행 지식:
 * - BFS의 기본 원리와 큐 자료구조
 * - Deque(양방향 큐) 자료구조 개념
 * - 최단경로 알고리즘의 기본 개념
 * - 그래프 탐색의 기본 원리
 *
 * ===== 0-1 BFS 진행 과정 (단계별) =====
 *
 * 1단계: 시작점을 덱에 넣고 거리를 0으로 설정
 * 2단계: 덱에서 원소를 하나 꺼냄 (항상 앞쪽에서)
 * 3단계: 현재 정점에서 갈 수 있는 모든 인접 정점 확인
 * 4단계: 각 인접 정점에 대해:
 *    - 새로운 거리 = 현재 거리 + 간선 가중치(0 또는 1)
 *    - 기존 거리보다 짧으면 업데이트
 *    - 가중치가 0이면 덱의 앞쪽에 추가 (우선처리)
 *    - 가중치가 1이면 덱의 뒤쪽에 추가 (후순위)
 * 5단계: 덱이 빌 때까지 2~4단계 반복
 *
 * ===== 코딩테스트에서의 활용 =====
 *
 * 주요 문제 유형:
 * 1. 미로 탈출 (벽 부수기 가능/불가능) - 벽 부수기 = 비용 1, 일반 이동 = 비용 0
 * 2. 최소 비용으로 목적지 도달 - 특정 행동만 비용이 드는 경우
 * 3. 가중치가 0, 1로만 구성된 그래프 문제
 * 4. 방향 전환 비용이 있는 경로 문제 - 직진 = 비용 0, 회전 = 비용 1
 * 5. 특정 조건하에서만 이동 가능한 격자 문제
 * 6. 최소 동작 횟수로 상태 변경하는 문제
 *
 * ===== 비슷한 알고리즘과의 차이점 및 주의사항 =====
 *
 * vs 일반 BFS:
 * - 일반 BFS: 가중치가 모두 동일(보통 1)한 경우에만 최단경로 보장
 * - 0-1 BFS: 가중치가 0 또는 1인 경우 사용
 * - 착각하기 쉬운 점: 가중치가 있다고 무조건 다익스트라를 쓸 필요 없음
 * - 잘못된 접근: 벽 부수기 문제를 일반 BFS로 풀려다 틀리는 경우
 *
 * vs 다익스트라:
 * - 다익스트라: 모든 양의 가중치에서 사용 가능하지만 우선순위큐로 O((V+E)logV)
 * - 0-1 BFS: 0, 1 가중치 한정이지만 덱 사용으로 O(V+E)
 * - 착각하기 쉬운 점: 가중치가 0, 1만 있어도 습관적으로 다익스트라 사용
 * - 성능 차이: 0-1 BFS가 약 3-5배 빠름
 *
 * vs DFS + 백트래킹:
 * - DFS: 모든 경로를 탐색하므로 최단경로 보장 안됨, 시간초과 위험
 * - 0-1 BFS: 첫 번째 도달이 최단경로 보장
 * - 착각하기 쉬운 점: "최소 횟수" 문제를 보고 백트래킹으로 접근
 * - 올바른 판단: 상태공간이 크면 0-1 BFS, 작으면 백트래킹
 */

public class ZeroOneBFS {

    // 방향벡터: 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String[] dirName = {"상", "하", "좌", "우"};

    public static void main(String[] args) {
        System.out.println("===== 0-1 BFS 동작 과정 시각화 =====\n");

        // 예제: 미로에서 벽을 부술 수 있는 최소 횟수로 목적지 도달
        // 0: 빈 공간(이동 비용 0), 1: 벽(부수는 비용 1)
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };

        System.out.println("초기 미로 상태:");
        System.out.println("0 = 빈 공간(비용 0), 1 = 벽(비용 1)");
        printMaze(maze);
        System.out.println("목표: (0,0)에서 (4,4)까지 최소 벽 부수기 횟수로 도달\n");

        int result = solve01BFSWithVisualization(maze);
        System.out.println("\n===== 최종 결과 =====");
        System.out.println("최소 벽 부수기 횟수: " + result + "번");
    }

    static void printMaze(int[][] maze) {
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printDistanceArray(int[][] dist, String title) {
        System.out.println(title);
        for(int i = 0; i < dist.length; i++) {
            for(int j = 0; j < dist[0].length; j++) {
                if(dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static int solve01BFSWithVisualization(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        // 거리 배열 초기화 (INF로 설정)
        int[][] dist = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        System.out.println("1단계: 거리 배열 초기화 (∞로 설정)");
        printDistanceArray(dist, "초기 거리 배열:");

        // ★ 핵심: 덱(Deque) 생성 - 양방향 큐!
        Deque<int[]> deque = new ArrayDeque<>();

        // 시작점 설정 (0, 0에서 시작)
        deque.addFirst(new int[]{0, 0});
        dist[0][0] = 0;

        System.out.println("2단계: 시작점 (0,0) 설정, 거리 = 0");
        printDistanceArray(dist, "시작점 설정 후:");

        int step = 3;

        while(!deque.isEmpty()) {
            System.out.println(step + "단계: 덱에서 원소 꺼내기");

            // 현재 덱 상태 출력
            System.out.print("현재 덱 상태: [");
            Iterator<int[]> iter = deque.iterator();
            while(iter.hasNext()) {
                int[] pos = iter.next();
                System.out.print("(" + pos[0] + "," + pos[1] + ")");
                if(iter.hasNext()) System.out.print(", ");
            }
            System.out.println("]");

            // 현재 위치 꺼내기 (항상 앞쪽에서!)
            int[] current = deque.pollFirst();
            int x = current[0];
            int y = current[1];

            System.out.println("꺼낸 위치: (" + x + "," + y + "), 현재 거리: " + dist[x][y]);

            // 4방향 탐색
            System.out.println("인접한 4방향 탐색:");

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                System.out.print("  " + dirName[i] + "쪽 (" + nx + "," + ny + "): ");

                // 경계 체크
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    System.out.println("경계 밖 - 무시");
                    continue;
                }

                // 새로운 비용 계산
                // maze[nx][ny]가 0이면 비용 0, 1이면 비용 1
                int newCost = dist[x][y] + maze[nx][ny];
                System.out.print("새 비용 = " + dist[x][y] + " + " + maze[nx][ny] + " = " + newCost);

                // 더 짧은 경로를 찾았을 때만 업데이트
                if(newCost < dist[nx][ny]) {
                    System.out.print(" → 기존 거리(" +
                            (dist[nx][ny] == Integer.MAX_VALUE ? "∞" : dist[nx][ny]) +
                            ")보다 짧음! 업데이트");

                    dist[nx][ny] = newCost;

                    // ★★★ 0-1 BFS의 핵심 로직 ★★★
                    if(maze[nx][ny] == 0) {
                        // 빈 공간으로 이동: 비용 0 → 덱 앞쪽 추가 (높은 우선순위)
                        System.out.print(" → 비용 0이므로 덱 앞쪽에 추가");
                        deque.addFirst(new int[]{nx, ny});
                    } else {
                        // 벽을 부수고 이동: 비용 1 → 덱 뒤쪽 추가 (낮은 우선순위)
                        System.out.print(" → 비용 1이므로 덱 뒤쪽에 추가");
                        deque.addLast(new int[]{nx, ny});
                    }
                } else {
                    System.out.print(" → 기존 거리가 더 짧음, 무시");
                }
                System.out.println();
            }

            System.out.println();
            printDistanceArray(dist, "현재 거리 배열 상태:");

            step++;

            // 너무 많은 출력 방지
            if(step > 15) {
                System.out.println("... (중간 과정 생략) ...\n");
                break;
            }
        }

        // 나머지 과정은 시각화 없이 진행
        while(!deque.isEmpty()) {
            int[] current = deque.pollFirst();
            int x = current[0];
            int y = current[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                int newCost = dist[x][y] + maze[nx][ny];

                if(newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;

                    if(maze[nx][ny] == 0) {
                        deque.addFirst(new int[]{nx, ny});
                    } else {
                        deque.addLast(new int[]{nx, ny});
                    }
                }
            }
        }

        System.out.println("최종 거리 배열 (각 위치까지의 최소 벽 부수기 횟수):");
        printDistanceArray(dist, "");

        // 목적지(우하단)까지의 최소 비용 반환
        return dist[n-1][m-1] == Integer.MAX_VALUE ? -1 : dist[n-1][m-1];
    }

    /*
     * ===== 실전 코딩테스트용 간단 버전 =====
     * 위의 시각화 코드는 학습용이고, 실제 코테에서는 아래 코드 사용
     */
    static int solve01BFS(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        // 거리 배열 초기화
        int[][] dist = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 덱 생성 및 시작점 설정
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});
        dist[0][0] = 0;

        while(!deque.isEmpty()) {
            int[] current = deque.pollFirst();
            int x = current[0];
            int y = current[1];

            // 4방향 탐색
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                int newCost = dist[x][y] + maze[nx][ny];

                if(newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;

                    // 핵심: 비용에 따른 덱 추가 위치 결정
                    if(maze[nx][ny] == 0) {
                        deque.addFirst(new int[]{nx, ny}); // 비용 0 → 앞쪽
                    } else {
                        deque.addLast(new int[]{nx, ny});  // 비용 1 → 뒤쪽
                    }
                }
            }
        }

        return dist[n-1][m-1] == Integer.MAX_VALUE ? -1 : dist[n-1][m-1];
    }
}