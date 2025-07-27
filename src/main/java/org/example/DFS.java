package org.example;

import java.util.*;
//DFS 알고리즘 기본 개념
//**DFS(Depth-First Search, 깊이 우선 탐색)**는 그래프나 트리를 탐색하는 알고리즘입니다. 한 방향으로 최대한 깊이 들어가다가 더 이상 갈 곳이 없으면 되돌아와서 다른 방향을 탐색합니다. 마치 미로에서 길을 따라 끝까지 가보고, 막다른 길이면 되돌아오는 것과 같습니다.
//    시간복잡도: O(V + E) - V는 정점 수, E는 간선 수
//    공간복잡도: O(V) - 재귀 호출 스택과 방문 배열에 필요한 공간
//    먼저 알아야 할 기본 개념:
//
//    재귀(Recursion): DFS의 핵심 구현 방법
//    스택(Stack) 개념: 재귀 호출이 스택과 같은 원리
//    백트래킹(Backtracking): 되돌아가는 개념
//    방문 체크의 중요성
//
//    코딩테스트에서 DFS가 사용되는 문제 유형:
//
//    경로 탐색: 모든 경로 찾기, 경로의 개수 세기
//    연결 요소: 섬의 개수, 연결된 구역 찾기
//    순열/조합: 모든 가능한 조합 생성
//    백트래킹: N-Queen, 스도쿠 등 제약 조건 문제
//    사이클 검출: 그래프에서 순환 구조 찾기
/**
 * DFS(깊이 우선 탐색) 교육용 예제
 *
 * 이 예제는 2D 격자에서 연결된 섬의 개수를 찾는 문제입니다.
 * 1: 땅(섬), 0: 바다, 상하좌우로 연결된 1들은 하나의 섬으로 간주
 */
public class DFS {
//
    // 상하좌우 이동을 위한 방향 벡터
    // DFS에서도 BFS와 동일하게 사용합니다
    // dx[0], dy[0] = 상(-1, 0), dx[1], dy[1] = 하(1, 0)
    // dx[2], dy[2] = 좌(0, -1), dx[3], dy[3] = 우(0, 1)
    static int[] dx = {-1, 1, 0, 0};  // 행 이동 (상, 하, 좌, 우)
    static int[] dy = {0, 0, -1, 1};  // 열 이동 (상, 하, 좌, 우)

    // 격자의 크기
    static int rows = 6;
    static int cols = 6;

    // 예제 격자 (직접 입력된 데이터)
    // 1 = 땅(섬), 0 = 바다
    static int[][] grid = {
            {1, 1, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 1},
            {0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0}
    };

    // 방문했는지 체크하는 배열
    // DFS에서 방문 체크는 무한 루프를 방지하기 위해 필수입니다!
    static boolean[][] visited;

    // DFS 탐색 과정을 시각적으로 보여주기 위한 변수들
    static int dfsCallCount = 0;        // DFS 호출 횟수
    static int currentIslandNumber = 0; // 현재 탐색 중인 섬 번호
    static List<String> dfsLog = new ArrayList<>(); // DFS 과정 로그

    public static void main(String[] args) {
        System.out.println("=== DFS 알고리즘 교육용 예제 ===\n");

        // 초기 격자 출력
        System.out.println("초기 격자 상태:");
        printGrid();

        // DFS 실행 버튼 역할 - 이 메소드만 호출하면 됩니다!
        int islandCount = runDFS();

        // 결과 출력
        System.out.println("\n=== 최종 결과 ===");
        System.out.println("총 섬의 개수: " + islandCount + "개");
        System.out.println("총 DFS 호출 횟수: " + dfsCallCount + "회");

        // 방문 상태 출력
        System.out.println("\n최종 방문 상태:");
        printVisited();

        // DFS 탐색 로그 출력 (처음 10개만)
        System.out.println("\nDFS 탐색 과정 (처음 10단계):");
        for (int i = 0; i < Math.min(10, dfsLog.size()); i++) {
            System.out.println(dfsLog.get(i));
        }
        if (dfsLog.size() > 10) {
            System.out.println("... (총 " + dfsLog.size() + "단계)");
        }
    }

    /**
     * DFS 알고리즘의 메인 실행 메소드
     * 모든 격자를 순회하면서 새로운 섬을 발견할 때마다 DFS를 시작합니다
     */
    public static int runDFS() {
        System.out.println("\n=== DFS 탐색 시작 ===");

        // 1단계: 방문 배열 초기화
        visited = new boolean[rows][cols];
        // boolean 배열은 자동으로 false로 초기화됩니다

        int islandCount = 0;  // 발견한 섬의 개수

        // 2단계: 모든 격자를 순회하면서 새로운 섬 찾기
        // 이중 for문으로 모든 칸을 하나씩 확인합니다
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // 3단계: 새로운 섬 발견 조건 체크
                // 조건 1: 현재 위치가 땅(1)이어야 함
                // 조건 2: 아직 방문하지 않은 곳이어야 함
                if (grid[i][j] == 1 && !visited[i][j]) {

                    islandCount++;  // 새로운 섬 발견!
                    currentIslandNumber = islandCount;

                    System.out.println("\n새로운 섬 #" + islandCount + " 발견! 시작 위치: (" + i + ", " + j + ")");

                    // 4단계: DFS로 연결된 모든 땅 탐색
                    // 여기서 재귀적 DFS가 시작됩니다!
                    dfs(i, j);

                    System.out.println("섬 #" + islandCount + " 탐색 완료");
                }
            }
        }

        return islandCount;
    }

    /**
     * DFS의 핵심! 재귀적 깊이 우선 탐색 메소드
     *
     * 이 메소드가 DFS의 모든 것을 담고 있습니다!
     * 재귀 호출의 원리를 이해하는 것이 매우 중요합니다.
     *
     * @param x 현재 탐색 중인 행 위치
     * @param y 현재 탐색 중인 열 위치
     */
    private static void dfs(int x, int y) {
        dfsCallCount++;  // DFS 호출 횟수 증가

        // 탐색 과정을 로그로 기록 (학습 목적)
        String logMessage = "DFS #" + dfsCallCount + ": 섬 #" + currentIslandNumber +
                "의 (" + x + ", " + y + ") 탐색";
        dfsLog.add(logMessage);

        // 처음 몇 단계는 콘솔에도 출력
        if (dfsCallCount <= 15) {
            System.out.println("  " + logMessage);
        }

        // 1단계: 현재 위치를 방문했다고 표시
        // 이것이 매우 중요합니다! 무한 재귀를 방지합니다
        // 왜 맨 처음에 할까? 나중에 하면 중복 방문이 발생할 수 있기 때문!
        visited[x][y] = true;

        // 2단계: 4방향으로 재귀 탐색
        // 상하좌우 각 방향으로 DFS를 계속 진행합니다
        for (int dir = 0; dir < 4; dir++) {

            // 다음 탐색할 위치 계산
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            // 3단계: 다음 위치의 유효성 검사
            // 이 조건들을 모두 만족해야 재귀 호출을 계속합니다

            // 조건 1: 격자 범위 내에 있는가?
            if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
                continue;  // 범위를 벗어나면 이 방향은 탐색하지 않음
            }

            // 조건 2: 땅(1)인가?
            if (grid[nextX][nextY] != 1) {
                continue;  // 바다(0)이면 이 방향은 탐색하지 않음
            }

            // 조건 3: 아직 방문하지 않았는가?
            if (visited[nextX][nextY]) {
                continue;  // 이미 방문했으면 이 방향은 탐색하지 않음
            }

            // 4단계: 모든 조건을 만족하면 재귀 호출!
            // 이것이 DFS의 핵심입니다!
            // 다음 위치에서 또 다시 DFS를 시작합니다
            //
            // ★ 재귀 호출의 이해 ★
            // 현재 dfs(x, y)가 끝나기 전에 dfs(nextX, nextY)를 호출합니다
            // 이렇게 하면 한 방향으로 끝까지 파고들어가는 효과가 생깁니다
            // 마치 스택에 쌓이는 것처럼 깊이 들어갔다가 되돌아옵니다
            if (dfsCallCount <= 15) {
                System.out.println("    -> " + getDirectionName(dir) + " 방향 (" +
                        nextX + ", " + nextY + ")로 재귀 호출");
            }

            dfs(nextX, nextY);  // ★ 재귀 호출! ★

            // 재귀 호출이 끝나고 여기로 돌아올 때가 "백트래킹"입니다
            // 한 방향 탐색이 완전히 끝났으므로 다음 방향을 탐색합니다
        }

        // 5단계: 4방향 탐색 완료
        // 현재 위치에서 갈 수 있는 모든 방향을 탐색했습니다
        // 이제 이 함수가 종료되고 이전 재귀 호출로 돌아갑니다 (백트래킹)
        if (dfsCallCount <= 15) {
            System.out.println("    (" + x + ", " + y + ") 탐색 완료, 이전 위치로 백트래킹");
        }
    }

    /**
     * 방향을 문자열로 변환하는 헬퍼 메소드
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
        System.out.println("격자 범례: 1=땅(섬), 0=바다");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("[" + grid[i][j] + "] ");
            }
            System.out.println();
        }
    }

    /**
     * 방문 상태를 출력하는 메소드
     */
    private static void printVisited() {
        System.out.println("방문 상태 (T=방문함, F=방문안함):");
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char status = visited[i][j] ? 'T' : 'F';
                System.out.print("[" + status + "] ");
            }
            System.out.println();
        }
    }
}

/*
=== DFS 알고리즘의 핵심 원리 요약 ===

1. 재귀 호출: 자기 자신을 호출하여 깊이 우선으로 탐색
   - 한 방향으로 끝까지 들어간 후 되돌아옴 (백트래킹)
   - 스택의 LIFO 원리와 동일 (Last In First Out)

2. 방문 체크: 무한 재귀를 방지하고 중복 탐색 방지
   - visited 배열로 이미 방문한 곳을 표시
   - 재귀 호출 전에 방문 체크를 하는 것이 중요!

3. 베이스 케이스: 재귀를 멈추는 조건들
   - 격자 범위를 벗어남
   - 탐색 대상이 아님 (예: 바다)
   - 이미 방문함

4. 백트래킹: 한 방향 탐색이 끝나면 이전 위치로 되돌아감
   - 재귀 호출이 끝나면 자동으로 이전 함수로 돌아감

=== BFS vs DFS 비교 ===

BFS (너비 우선 탐색):
- 큐 사용, 가까운 곳부터 탐색
- 최단 거리 보장
- 메모리 사용량이 많을 수 있음

DFS (깊이 우선 탐색):
- 재귀/스택 사용, 깊이 우선 탐색
- 경로 존재 여부 확인에 적합
- 메모리 사용량이 적음
- 모든 경로 탐색 가능

=== 시간복잡도 분석 ===
- 각 칸을 최대 한 번씩만 방문: O(rows × cols)
- 각 칸에서 4방향 체크: O(4) = O(1)
- 전체 시간복잡도: O(rows × cols)

=== 공간복잡도 분석 ===
- 재귀 호출 스택 최대 깊이: O(rows × cols) (최악의 경우)
- visited 배열: O(rows × cols)
- 전체 공간복잡도: O(rows × cols)

=== 코딩테스트에서 DFS 문제 해결 팁 ===

1. 모든 경로/조합을 탐색해야 하는 문제 → DFS
2. 연결된 구역을 찾는 문제 → DFS (또는 BFS)
3. 순열/조합 생성 → DFS + 백트래킹
4. 사이클 검출 → DFS + 방문 상태 세분화
5. N-Queen, 스도쿠 등 제약 조건 문제 → DFS + 백트래킹

=== DFS 구현시 주의사항 ===

1. 방문 체크를 재귀 호출 직전에 할 것
2. 베이스 케이스를 명확히 정의할 것
3. 재귀 깊이가 너무 깊어지지 않도록 주의
4. 백트래킹이 필요한 경우 방문 체크를 해제하는 코드 추가

이 예제를 완전히 이해했다면 연결 요소 찾기, 순열/조합 생성,
백트래킹 문제 등 다양한 DFS 문제를 해결할 수 있습니다!

=== 재귀 호출 이해를 위한 시각적 설명 ===

예시: (0,0)에서 시작하는 DFS
dfs(0,0) 호출
├── visited[0][0] = true
├── 상 방향: 범위를 벗어남 → continue
├── 하 방향: dfs(1,0) 호출
│   ├── visited[1][0] = true
│   ├── 상 방향: 이미 방문함 → continue
│   ├── 하 방향: 바다 → continue
│   ├── 좌 방향: 범위를 벗어남 → continue
│   └── 우 방향: 바다 → continue
│   └── dfs(1,0) 종료, dfs(0,0)로 복귀
├── 좌 방향: 범위를 벗어남 → continue
└── 우 방향: dfs(0,1) 호출
    └── ... 계속

이런 식으로 깊이 들어갔다가 되돌아오는 것이 DFS의 핵심입니다!
*/
