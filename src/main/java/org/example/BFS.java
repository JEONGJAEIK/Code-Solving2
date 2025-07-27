package org.example;

import java.util.*;

//BFS 알고리즘 기본 개념
//**BFS(Breadth-First Search, 너비 우선 탐색)**는 그래프나 트리를 탐색하는 알고리즘입니다. 시작점에서 가까운 노드부터 차례대로 탐색하며, 마치 물결이 퍼져나가듯이 동심원 형태로 탐색합니다.
//시간복잡도: O(V + E) - V는 정점 수, E는 간선 수
//공간복잡도: O(V) - 큐와 방문 배열에 필요한 공간
//먼저 알아야 할 기본 개념:
//
//큐(Queue) 자료구조: FIFO(First In First Out) 원리
//그래프 표현 방법: 인접 리스트, 인접 행렬
//방문 체크의 중요성
//
//코딩테스트에서 BFS가 사용되는 문제 유형:
//
//최단 거리 문제: 미로 탈출, 최소 이동 횟수
//레벨별 탐색: 각 깊이별로 처리해야 하는 문제
//연결된 구역 찾기: 섬의 개수, 연결 요소
//상태 공간 탐색: 게임 상태 변화, 퍼즐 해결
/**
 * BFS(너비 우선 탐색) 교육용 예제
 *
 * 이 예제는 2D 격자에서 시작점(S)에서 목표점(G)까지의 최단 경로를 찾는 문제입니다.
 * 0: 이동 가능한 길, 1: 벽(이동 불가), S: 시작점, G: 목표점
 */
public class BFS {

    // 상하좌우 이동을 위한 방향 벡터
    // 이것은 BFS에서 매우 중요한 개념입니다!
    // dx[0], dy[0] = 상(-1, 0), dx[1], dy[1] = 하(1, 0)
    // dx[2], dy[2] = 좌(0, -1), dx[3], dy[3] = 우(0, 1)
    static int[] dx = {-1, 1, 0, 0};  // 행 이동 (상, 하, 좌, 우)
    static int[] dy = {0, 0, -1, 1};  // 열 이동 (상, 하, 좌, 우)

    // 격자의 크기
    static int rows = 5;
    static int cols = 5;

    // 예제 격자 (직접 입력된 데이터)
    // S = 시작점, G = 목표점, 0 = 길, 1 = 벽
    static char[][] grid = {
            {'S', '0', '1', '0', '0'},
            {'0', '0', '1', '0', '1'},
            {'0', '1', '0', '0', '0'},
            {'0', '0', '0', '1', 'G'},
            {'1', '0', '0', '0', '0'}
    };

    // 각 위치까지의 최단 거리를 저장하는 배열
    // -1로 초기화하여 아직 방문하지 않은 곳을 표시
    static int[][] distance;

    // BFS에서 사용할 위치 정보를 담는 클래스
    // 왜 클래스를 만들까? 큐에 (행, 열) 정보를 함께 저장하기 위해서입니다!
    static class Position {
        int x, y;  // x는 행(row), y는 열(column)

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BFS 알고리즘 교육용 예제 ===\n");

        // 초기 격자 출력
        System.out.println("초기 격자 상태:");
        printGrid();

        // BFS 실행 버튼 역할 - 이 메소드만 호출하면 됩니다!
        int result = runBFS();

        // 결과 출력
        System.out.println("\n=== 결과 ===");
        if (result != -1) {
            System.out.println("최단 거리: " + result + "칸");
            System.out.println("\n각 위치까지의 거리 정보:");
            printDistance();
        } else {
            System.out.println("목표점에 도달할 수 없습니다!");
        }
    }

    /**
     * BFS 알고리즘의 핵심 구현부
     * 이 메소드가 BFS의 모든 것을 담고 있습니다!
     */
    public static int runBFS() {
        System.out.println("\n=== BFS 탐색 시작 ===");

        // 1단계: 초기화
        // distance 배열을 -1로 초기화 (아직 방문하지 않음을 의미)
        distance = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distance[i][j] = -1;  // -1은 아직 방문하지 않은 상태
            }
        }

        // 2단계: 시작점과 목표점 찾기
        int startX = -1, startY = -1;  // 시작점 좌표
        int goalX = -1, goalY = -1;    // 목표점 좌표

        // 격자를 전체 탐색하여 S와 G의 위치를 찾습니다
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 'G') {
                    goalX = i;
                    goalY = j;
                }
            }
        }

        System.out.println("시작점: (" + startX + ", " + startY + ")");
        System.out.println("목표점: (" + goalX + ", " + goalY + ")");

        // 3단계: BFS의 핵심! 큐 생성 및 시작점 추가
        // 큐를 사용하는 이유: FIFO 방식으로 가까운 거리부터 탐색하기 위해
        Queue<Position> queue = new LinkedList<>();

        // 시작점을 큐에 추가하고 거리를 0으로 설정
        queue.offer(new Position(startX, startY));
        distance[startX][startY] = 0;  // 시작점까지의 거리는 0

        System.out.println("\nBFS 탐색 과정:");
        int step = 0;  // 진행 단계를 표시하기 위한 변수

        // 4단계: BFS 메인 루프
        // 큐가 비어있지 않은 동안 계속 탐색합니다
        while (!queue.isEmpty()) {
            step++;
            System.out.println("\n--- 단계 " + step + " ---");

            // 큐에서 현재 위치를 꺼냅니다 (FIFO)
            // 이것이 BFS의 핵심! 먼저 들어간 것이 먼저 나옵니다
            Position current = queue.poll();
            int currentX = current.x;
            int currentY = current.y;

            System.out.println("현재 탐색 위치: (" + currentX + ", " + currentY +
                    ") - 거리: " + distance[currentX][currentY]);

            // 목표점에 도착했는지 확인
            if (currentX == goalX && currentY == goalY) {
                System.out.println("목표점 도달! 최단 거리: " + distance[currentX][currentY]);
                return distance[currentX][currentY];
            }

            // 5단계: 4방향 탐색 (상하좌우)
            // 현재 위치에서 상하좌우로 이동할 수 있는지 확인합니다
            for (int dir = 0; dir < 4; dir++) {
                // 새로운 위치 계산
                // 왜 dx, dy를 사용할까? 상하좌우 이동을 간단하게 표현하기 위해!
                int nextX = currentX + dx[dir];
                int nextY = currentY + dy[dir];

                System.out.println("  방향 " + getDirectionName(dir) + " 체크: (" +
                        nextX + ", " + nextY + ")");

                // 6단계: 유효성 검사 (이 부분이 매우 중요합니다!)
                // 다음 위치가 유효한지 확인하는 조건들:

                // 조건 1: 격자 범위 내에 있는가?
                if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
                    System.out.println("    -> 격자 범위를 벗어남");
                    continue;  // 다음 방향으로 넘어감
                }

                // 조건 2: 벽이 아닌가?
                if (grid[nextX][nextY] == '1') {
                    System.out.println("    -> 벽이므로 이동 불가");
                    continue;
                }

                // 조건 3: 이미 방문했는가?
                // distance[nextX][nextY] != -1 이면 이미 방문한 곳
                // BFS에서는 처음 방문할 때가 항상 최단 거리이므로 재방문 불필요!
                if (distance[nextX][nextY] != -1) {
                    System.out.println("    -> 이미 방문한 위치");
                    continue;
                }

                // 7단계: 새로운 위치를 큐에 추가하고 거리 업데이트
                // 모든 조건을 통과했다면 이 위치는 탐색 가능합니다!
                distance[nextX][nextY] = distance[currentX][currentY] + 1;
                queue.offer(new Position(nextX, nextY));

                System.out.println("    -> 큐에 추가! 거리: " + distance[nextX][nextY]);
            }

            // 현재 큐 상태 출력 (학습 목적)
            System.out.println("  현재 큐 크기: " + queue.size());
        }

        // 큐가 비었는데도 목표점에 도달하지 못한 경우
        System.out.println("\n큐가 비었습니다. 목표점에 도달할 수 없습니다.");
        return -1;  // 도달 불가능
    }

    /**
     * 방향을 문자열로 변환하는 헬퍼 메소드
     * 0: 상, 1: 하, 2: 좌, 3: 우
     */
    private static String getDirectionName(int dir) {
        switch (dir) {
            case 0: return "상";
            case 1: return "하";
            case 2: return "좌";
            case 3: return "우";
            default: return "알 수 없음";
        }
    }

    /**
     * 현재 격자 상태를 출력하는 메소드
     */
    private static void printGrid() {
        System.out.println("격자 범례: S=시작점, G=목표점, 0=길, 1=벽");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("[" + grid[i][j] + "] ");
            }
            System.out.println();
        }
    }

    /**
     * 각 위치까지의 거리 정보를 출력하는 메소드
     * -1: 도달 불가능, 0 이상: 시작점으로부터의 거리
     */
    private static void printDistance() {
        System.out.println("거리 정보 (-1: 도달불가, 0 이상: 거리):");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (distance[i][j] == -1) {
                    System.out.print("[-1] ");
                } else {
                    System.out.printf("[%2d] ", distance[i][j]);
                }
            }
            System.out.println();
        }
    }
}

/*
=== BFS 알고리즘의 핵심 원리 요약 ===

1. 큐(Queue) 사용: FIFO 방식으로 가까운 거리부터 탐색
   - 왜 큐를 사용할까? 동심원 형태로 퍼져나가는 탐색을 위해

2. 방문 체크: 같은 곳을 두 번 방문하지 않음
   - distance 배열이 방문 체크와 거리 저장을 동시에 담당

3. 4방향 탐색: dx, dy 배열을 사용한 상하좌우 이동
   - 이것은 2D 격자 문제에서 표준적인 기법

4. 최단 거리 보장: BFS의 특성상 처음 도달한 경로가 최단 경로
   - 이것이 DFS와 가장 큰 차이점!

=== 시간복잡도 분석 ===
- 각 칸을 최대 한 번씩만 방문: O(rows × cols)
- 각 칸에서 4방향 체크: O(4) = O(1)
- 전체 시간복잡도: O(rows × cols)

=== 공간복잡도 분석 ===
- 큐 최대 크기: O(rows × cols)
- distance 배열: O(rows × cols)
- 전체 공간복잡도: O(rows × cols)

=== 코딩테스트에서 BFS 문제 해결 팁 ===

1. 최단 거리/최소 횟수 키워드가 나오면 BFS 고려
2. 레벨별 처리가 필요하면 큐 크기를 이용한 레벨 구분
3. 시작점이 여러 개인 경우 모든 시작점을 큐에 먼저 추가
4. 상태를 저장해야 하는 경우 Position 클래스를 확장
5. 방문 체크는 큐에 추가할 때 즉시! (중복 방지)

이 예제를 완전히 이해했다면 미로 탈출, 토마토, 나이트의 이동 등
다양한 BFS 문제를 해결할 수 있습니다!
*/
