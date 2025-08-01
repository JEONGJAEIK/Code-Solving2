package org.example.zeroOneBFS;

import java.util.*;

/*
 * ===== 진짜 0-1 BFS 알고리즘 =====
 *
 * 기본 개념:
 * - 가중치가 정확히 0 또는 1로만 이루어진 그래프에서 최단경로를 구하는 특화된 BFS
 * - 핵심: Deque를 사용해 가중치 0인 간선은 앞쪽(addFirst), 가중치 1인 간선은 뒤쪽(addLast)에 추가
 * - 벽 부수기는 상태 저장 BFS이므로 제외! 진짜 0-1 BFS만 다룸
 *
 * 시간복잡도: O(V + E) - 각 정점과 간선을 최대 1번씩만 처리
 * 공간복잡도: O(V) - 거리 배열과 덱의 크기
 *
 * 선행 지식:
 * 1. BFS와 큐 자료구조의 기본 원리
 * 2. Deque(양방향 큐) - addFirst(), addLast(), pollFirst() 메서드
 * 3. 그래프 최단경로 알고리즘의 완화(relaxation) 개념
 *
 * ===== 진짜 0-1 BFS 문제 유형 =====
 *
 * 1. 방향 전환 비용 문제
 *    - 직진: 비용 0, 회전: 비용 1
 *    - 로봇/자동차가 방향을 바꿀 때마다 연료/시간 소모
 *
 * 2. 특수 타일 활용 문제
 *    - 특수 경로: 비용 0, 일반 경로: 비용 1
 *    - 텔레포트, 컨베이어 벨트, 순간이동 등
 *
 * 3. 조건부 무료 이동 문제
 *    - 특정 조건 만족시: 비용 0, 일반 이동: 비용 1
 *    - 시간대별 통행료, 특정 아이템 보유시 무료 등
 *
 * ===== 다른 알고리즘과의 구분 =====
 *
 * vs 상태 저장 BFS (벽 부수기):
 * - 상태 저장 BFS: 3차원 배열 [x][y][벽부순개수], 모든 이동이 비용 1
 * - 0-1 BFS: 2차원 배열, 가중치가 실제로 0과 1로 다름
 * - 착각 포인트: 벽 부수기를 0-1 BFS로 착각하는 경우 많음
 *
 * vs 다익스트라:
 * - 다익스트라: 모든 양의 가중치, 우선순위큐, O((V+E)logV)
 * - 0-1 BFS: 0,1 가중치만, 덱 사용, O(V+E)
 * - 선택 기준: 가중치가 0,1뿐이면 0-1 BFS가 더 효율적
 *
 * ===== 0-1 BFS 핵심 원리 =====
 *
 * 왜 덱을 사용하는가?
 * 1. 가중치 0인 간선: 현재 거리 그대로 → 더 빨리 처리해야 함 (addFirst)
 * 2. 가중치 1인 간선: 현재 거리 + 1 → 나중에 처리해도 됨 (addLast)
 * 3. 결과: 거리가 짧은 순서대로 자동 정렬되어 처리됨
 *
 * 핵심 메커니즘:
 * - 덱의 앞쪽에는 항상 현재 최소 거리의 노드들이 위치
 * - 뒤쪽에는 거리+1인 노드들이 대기
 * - 이 순서 보장으로 첫 번째 도달 = 최단 거리 보장
 */

public class ZeroOneBFS {

    // 4방향 이동 벡터 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        System.out.println("========== 진짜 0-1 BFS 실전 예시 ==========\n");

        // ===== 1. 방향 전환 로봇 (가장 대표적인 0-1 BFS) =====
        char[][] robotGrid = {
                {'S', '.', '.', '.', 'E'},
                {'.', '#', '#', '.', '.'},
                {'.', '.', '.', '#', '.'},
                {'#', '#', '.', '.', '.'},
                {'.', '.', '.', '#', '.'}
        };

        System.out.println("=== 방향 전환 로봇 (직진=0, 회전=1) ===");
        robotMovementDetailed(robotGrid);

        // ===== 2. 텔레포트 게임 =====
        int[][] gameMap = {
                {0, 1, 0, 2, 0},  // 0: 일반칸(비용1), 1: 벽, 2: 텔레포트(비용0)
                {0, 1, 0, 1, 0},
                {2, 0, 0, 1, 2},
                {1, 1, 1, 0, 0},
                {0, 2, 0, 0, 0}
        };

        System.out.println("\n=== 텔레포트 게임 (일반이동=1, 텔레포트=0) ===");
        teleportGameDetailed(gameMap);
    }

    /**
     * 실전 코드: 방향 전환 로봇
     * 직진: 비용 0, 회전: 비용 1
     * 진짜 0-1 BFS의 대표 문제
     */
    static int robotMovementDetailed(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        // 시작점과 끝점 찾기
        int[] start = null, end = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'S') start = new int[]{i, j};
                if (grid[i][j] == 'E') end = new int[]{i, j};
            }
        }

        // 3차원 거리 배열: [x][y][방향]
        int[][][] dist = new int[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        // ★ 핵심: 덱 사용 (0-1 BFS의 정체성)
        Deque<int[]> deque = new ArrayDeque<>();

        // 모든 초기 방향으로 시작 (방향 설정 비용 없음)
        for (int dir = 0; dir < 4; dir++) {
            deque.addFirst(new int[]{start[0], start[1], dir});
            dist[start[0]][start[1]][dir] = 0;
        }

        System.out.println("초기 맵 (S=시작, E=끝, #=벽, .=빈공간):");
        printGrid(grid);

        int stepCount = 0;
        String[] dirName = {"위", "아래", "왼쪽", "오른쪽"};

        while (!deque.isEmpty() && stepCount < 12) { // 처음 몇 단계만 상세 출력
            int[] cur = deque.pollFirst();
            int x = cur[0], y = cur[1], curDir = cur[2];

            System.out.println("STEP " + (++stepCount) + ": 위치(" + x + "," + y + ") " +
                    dirName[curDir] + "방향, 회전횟수=" + dist[x][y][curDir]);

            // ★ 직진 이동 (비용 0) - 가장 우선 처리
            int nx = x + dx[curDir], ny = y + dy[curDir];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] != '#') {
                if (dist[x][y][curDir] < dist[nx][ny][curDir]) {
                    dist[nx][ny][curDir] = dist[x][y][curDir]; // 비용 0!
                    deque.addFirst(new int[]{nx, ny, curDir}); // ★ 앞쪽 추가 (우선처리)
                    System.out.println("  → 직진: (" + nx + "," + ny + ") " + dirName[curDir] +
                            ", 비용0, 덱 앞쪽 추가");
                }
            }

            // ★ 방향 전환 (비용 1) - 나중에 처리
            for (int newDir = 0; newDir < 4; newDir++) {
                if (newDir == curDir) continue; // 같은 방향 제외

                int newCost = dist[x][y][curDir] + 1; // 비용 1!
                if (newCost < dist[x][y][newDir]) {
                    dist[x][y][newDir] = newCost;
                    deque.addLast(new int[]{x, y, newDir}); // ★ 뒤쪽 추가 (후순위)
                    System.out.println("  → 회전: 같은위치 " + dirName[newDir] +
                            ", 비용1, 덱 뒤쪽 추가");
                }
            }

            System.out.println("  현재 덱 크기: " + deque.size() + "\n");
        }

        // 나머지는 출력 없이 처리 (실전에서는 이 부분만 있으면 됨)
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int x = cur[0], y = cur[1], curDir = cur[2];

            // 직진 (비용 0)
            int nx = x + dx[curDir], ny = y + dy[curDir];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] != '#') {
                if (dist[x][y][curDir] < dist[nx][ny][curDir]) {
                    dist[nx][ny][curDir] = dist[x][y][curDir];
                    deque.addFirst(new int[]{nx, ny, curDir});
                }
            }

            // 회전 (비용 1)
            for (int newDir = 0; newDir < 4; newDir++) {
                if (newDir == curDir) continue;
                int newCost = dist[x][y][curDir] + 1;
                if (newCost < dist[x][y][newDir]) {
                    dist[x][y][newDir] = newCost;
                    deque.addLast(new int[]{x, y, newDir});
                }
            }
        }

        // 최종 결과
        int result = Integer.MAX_VALUE;
        for (int dir = 0; dir < 4; dir++) {
            result = Math.min(result, dist[end[0]][end[1]][dir]);
        }

        System.out.println("최종 결과: 최소 회전 횟수 = " + result);
        return result;
    }

    /**
     * 실전 코드: 텔레포트 게임
     * 일반 이동: 비용 1, 텔레포트: 비용 0
     * 진짜 0-1 BFS의 또 다른 대표 예시
     */
    static int teleportGameDetailed(int[][] gameMap) {
        int n = gameMap.length, m = gameMap[0].length;

        // 거리 배열 초기화
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // ★ 덱 생성 및 시작점 설정
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});
        dist[0][0] = 0;

        System.out.println("맵 정보 (0=일반칸, 1=벽, 2=텔레포트):");
        printIntGrid(gameMap);

        int stepCount = 0;

        while (!deque.isEmpty() && stepCount < 15) {
            int[] cur = deque.pollFirst();
            int x = cur[0], y = cur[1];

            System.out.println("STEP " + (++stepCount) + ": 현재 위치(" + x + "," + y +
                    "), 누적 비용=" + dist[x][y]);

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || gameMap[nx][ny] == 1) {
                    continue; // 범위 밖이거나 벽
                }

                // ★ 핵심: 이동 비용 계산
                int moveCost = (gameMap[nx][ny] == 2) ? 0 : 1; // 텔레포트=0, 일반=1
                int newCost = dist[x][y] + moveCost;

                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;

                    if (moveCost == 0) {
                        // ★ 비용 0 → 덱 앞쪽 (최우선 처리)
                        deque.addFirst(new int[]{nx, ny});
                        System.out.println("  → 텔레포트: (" + nx + "," + ny + ") 비용0, 덱 앞쪽");
                    } else {
                        // ★ 비용 1 → 덱 뒤쪽 (나중에 처리)
                        deque.addLast(new int[]{nx, ny});
                        System.out.println("  → 일반이동: (" + nx + "," + ny + ") 비용1, 덱 뒤쪽");
                    }
                }
            }
            System.out.println("  덱 크기: " + deque.size() + "\n");
        }

        // 나머지 처리 (실전 코드)
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || gameMap[nx][ny] == 1) continue;

                int moveCost = (gameMap[nx][ny] == 2) ? 0 : 1;
                int newCost = dist[x][y] + moveCost;

                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;

                    if (moveCost == 0) {
                        deque.addFirst(new int[]{nx, ny}); // 텔레포트
                    } else {
                        deque.addLast(new int[]{nx, ny});  // 일반 이동
                    }
                }
            }
        }

        System.out.println("최종 거리 배열:");
        printIntGrid(dist);

        int result = dist[n-1][m-1];
        System.out.println("최종 결과: 최소 이동 비용 = " + result);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // ========== 출력 도우미 메서드들 ==========

    static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printIntGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
 * ===== 실전 코딩테스트에서 사용법 =====
 *
 * 1. 문제에서 "비용이 0과 1로만 구성"되어 있는지 확인
 * 2. 덱 생성: Deque<int[]> deque = new ArrayDeque<>();
 * 3. 거리 배열 초기화 후 시작점 설정
 * 4. while (!deque.isEmpty()) 루프에서:
 *    - deque.pollFirst()로 현재 위치 가져오기
 *    - 인접한 위치들 탐색
 *    - 비용 0인 이동: deque.addFirst() 사용
 *    - 비용 1인 이동: deque.addLast() 사용
 * 5. 첫 번째 도달이 항상 최단 거리 보장됨
 *
 * ===== 핵심 패턴 =====
 * if (cost == 0) {
 *     deque.addFirst(next);  // 우선 처리
 * } else {
 *     deque.addLast(next);   // 나중 처리
 * }
 *
 * 이 패턴만 기억하면 모든 0-1 BFS 문제 해결 가능!
 */