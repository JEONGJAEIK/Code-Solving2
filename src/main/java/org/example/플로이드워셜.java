package org.example;

public class 플로이드워셜 {
    
    // 무한대를 나타내는 상수 (연결되지 않은 정점 간의 거리)
    // Integer.MAX_VALUE를 사용하면 덧셈 시 오버플로우 발생 가능하므로 
    // 충분히 큰 값으로 설정 (문제에서 주어진 최대값의 2배 정도)
    static final int INF = 987654321;
    
    public static void main(String[] args) {
        // 예시 그래프: 4개의 정점 (0, 1, 2, 3)
        int n = 4; // 정점의 개수
        
        // 인접행렬로 그래프 표현
        // dist[i][j] = i에서 j로 가는 직접 경로의 가중치
        int[][] dist = {
            //   0   1   2   3
            {   0,  5, INF,  10},  // 0번 정점에서
            { INF,  0,   3, INF},  // 1번 정점에서  
            { INF, INF,  0,   1},  // 2번 정점에서
            { INF, INF, INF,  0}   // 3번 정점에서
        };
        
        System.out.println("=== 플로이드-워셜 알고리즘 실행 ===");
        System.out.println("초기 인접행렬:");
        printMatrix(dist, n);
        
        // 플로이드-워셜 알고리즘 실행
        floydWarshall(dist, n);
        
        System.out.println("\n최종 결과 (모든 정점 간 최단거리):");
        printMatrix(dist, n);
        
        // 특정 경로들의 최단거리 출력
        System.out.println("\n=== 주요 경로들의 최단거리 ===");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (dist[i][j] == INF) {
                        System.out.println(i + " -> " + j + ": 경로 없음");
                    } else {
                        System.out.println(i + " -> " + j + ": " + dist[i][j]);
                    }
                }
            }
        }
    }
    
    /**
     * 플로이드-워셜 알고리즘의 핵심 구현
     * 
     * 핵심 아이디어: 
     * - k번째 정점을 경유지로 사용했을 때 더 짧은 경로가 있는지 확인
     * - dist[i][j] vs dist[i][k] + dist[k][j] 중 더 작은 값 선택
     * 
     * 왜 k를 가장 바깥 루프로 해야 할까?
     * - k번째 정점을 경유지로 '완전히' 고려한 후 다음 경유지로 넘어가야 함
     * - 만약 i나 j를 바깥 루프로 하면, 아직 고려되지 않은 경유지들 때문에
     *   최적해를 놓칠 수 있음
     */
    static void floydWarshall(int[][] dist, int n) {
        // k: 경유지로 사용할 정점 (0번부터 n-1번까지 차례로)
        for (int k = 0; k < n; k++) {
            System.out.println("\n--- " + k + "번 정점을 경유지로 고려 ---");
            
            // i: 출발 정점
            for (int i = 0; i < n; i++) {
                // j: 도착 정점  
                for (int j = 0; j < n; j++) {
                    
                    // 자기 자신으로 가는 경로는 0이므로 스킵
                    if (i == j) continue;
                    
                    // 현재 i에서 j로 가는 최단거리
                    int currentDist = dist[i][j];
                    
                    // k를 경유해서 가는 경로의 거리
                    // 주의: i->k 또는 k->j 경로가 없으면 (INF) 계산하지 않음
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        int viaDist = dist[i][k] + dist[k][j];
                        
                        // 경유해서 가는 경로가 더 짧으면 갱신
                        if (viaDist < currentDist) {
                            dist[i][j] = viaDist;
                            System.out.println("  갱신: " + i + "->" + j + 
                                             " (기존: " + (currentDist == INF ? "INF" : currentDist) + 
                                             ", 새로운: " + viaDist + " via " + k + ")");
                        }
                    }
                }
            }
            
            // 각 단계별 결과 출력 (디버깅용)
            System.out.println("현재 상태:");
            printMatrix(dist, n);
        }
    }
    
    /**
     * 행렬을 보기 좋게 출력하는 헬퍼 함수
     */
    static void printMatrix(int[][] matrix, int n) {
        System.out.print("     ");
        for (int j = 0; j < n; j++) {
            System.out.printf("%4d ", j);
        }
        System.out.println();
        
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d : ", i);
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == INF) {
                    System.out.print(" INF ");
                } else {
                    System.out.printf("%4d ", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    /*
     * ========== 추가 활용 예시 ==========
     * 
     * 1. 경로 복원 (실제 경로 찾기)
     * - next 배열을 사용하여 실제 최단 경로상의 정점들을 추적
     * 
     * 2. 음수 사이클 탐지
     * - 대각선 원소가 음수가 되면 음수 사이클 존재
     * 
     * 3. 도달 가능성 판단
     * - dist[i][j] < INF 이면 i에서 j로 도달 가능
     * 
     * 4. 최단 경로 개수 세기
     * - count 배열로 최단 경로의 개수 추적
     */
    
    /**
     * 경로 복원을 위한 플로이드-워셜 (참고용)
     * 실제 코딩테스트에서 경로를 출력해야 할 때 사용
     */
    static void floydWarshallWithPath(int[][] dist, int[][] next, int n) {
        // next[i][j] = i에서 j로 가는 최단경로에서 i 다음에 방문할 정점
        
        // 초기화: 직접 연결된 경우 next[i][j] = j
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] != INF) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1; // 경로 없음
                }
            }
        }
        
        // 플로이드-워셜 + 경로 복원
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            next[i][j] = next[i][k]; // 경로 정보 갱신
                        }
                    }
                }
            }
        }
    }
    
    /*
     * ========== 코딩테스트 팁 ==========
     * 
     * 1. 메모리 최적화가 필요한 경우:
     *    - 정점 번호를 좌표압축으로 줄이기
     *    - 실제로는 모든 쌍이 필요하지 않을 수 있음
     * 
     * 2. 시간복잡도 주의:
     *    - N ≤ 400 정도까지가 플로이드-워셜 적용 가능한 범위
     *    - 그 이상이면 다익스트라를 여러 번 실행 고려
     * 
     * 3. 실수하기 쉬운 점들:
     *    - INF 값 설정 (오버플로우 주의)
     *    - 루프 순서 (k-i-j 순서 반드시 지키기)
     *    - 자기 자신으로의 거리는 0으로 초기화
     *    - 입력에서 양방향 그래프인지 단방향 그래프인지 확인
     */
}