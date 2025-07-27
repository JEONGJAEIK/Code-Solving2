package org.example;

import java.util.*;

/*
 * ========== 벨만-포드(Bellman-Ford) 알고리즘 ==========
 * 
 * 【기본 개념】
 * - 한 정점에서 다른 모든 정점까지의 최단 거리를 구하는 알고리즘
 * - 음수 가중치 간선이 있어도 처리 가능 (다익스트라와의 가장 큰 차이점)
 * - 음수 사이클(negative cycle) 탐지 가능
 * 
 * 【핵심 아이디어】
 * - "최단 경로는 최대 V-1개의 간선을 사용한다"는 원리 활용
 * - V-1번 반복하여 모든 간선에 대해 완화(relaxation) 수행
 * - V번째에도 완화가 일어나면 음수 사이클 존재
 * 
 * 【시간복잡도】
 * - O(V × E) (V: 정점 수, E: 간선 수)
 * - 최악의 경우 O(V³) (완전 그래프일 때)
 * 
 * 【선행 지식】
 * - 그래프의 간선 리스트 표현법
 * - 최단 경로 문제의 기본 개념
 * - 완화(relaxation) 개념: dist[v] = min(dist[v], dist[u] + weight(u,v))
 * 
 * 【코딩테스트 활용 유형】
 * 1. 음수 가중치가 있는 최단 경로 문제
 * 2. 음수 사이클 탐지 문제
 * 3. "돈을 벌면서 이동" 류의 문제 (음수 간선으로 모델링)
 * 4. 시간 여행, 워프홀 등의 특수한 상황 모델링
 * 
 * 【유사 알고리즘과의 차이점】
 * 
 * ★ 다익스트라 vs 벨만-포드
 * - 다익스트라: 음수 간선 처리 불가, O(V² 또는 ElogV), 그리디 알고리즘
 * - 벨만-포드: 음수 간선 처리 가능, O(VE), 동적 계획법
 * - 착각하기 쉬운 점: 음수 간선이 없어도 벨만-포드를 쓰면 비효율적
 * 
 * ★ 플로이드-워셜 vs 벨만-포드  
 * - 플로이드-워셜: 모든 쌍 최단거리, O(V³), 음수 사이클 있으면 부정확
 * - 벨만-포드: 단일 출발점, O(VE), 음수 사이클 탐지 가능
 * - 착각하기 쉬운 점: 모든 쌍이 필요하다고 벨만-포드를 V번 돌리는 것보다 
 *   플로이드-워셜이 더 효율적일 수 있음
 * 
 * ★ SPFA vs 벨만-포드
 * - SPFA: 벨만-포드의 최적화 버전, 평균적으로 더 빠름
 * - 벨만-포드: 최악의 경우에도 O(VE) 보장
 * - 착각하기 쉬운 점: SPFA는 최악의 경우 지수시간이 걸릴 수 있음
 * 
 * 【잘못 접근하기 쉬운 경우】
 * - 음수 간선이 없는데 벨만-포드 사용 → 다익스트라가 더 효율적
 * - 정점 수가 많고 간선 수가 적은데 벨만-포드 사용 → 다익스트라 고려
 * - 음수 사이클만 탐지하면 되는데 최단거리까지 구함 → 불필요한 계산
 */

public class 벨만포드 {
    
    // 무한대를 나타내는 상수 (도달할 수 없는 정점까지의 거리)
    // 음수 간선이 있으므로 Long 타입 사용 (오버플로우 방지)
    static final long INF = Long.MAX_VALUE;
    
    // 간선을 표현하는 클래스
    // 벨만-포드는 간선 리스트를 사용하는 것이 일반적
    static class Edge {
        int from, to;  // 출발 정점, 도착 정점
        long weight;   // 간선의 가중치 (음수 가능)
        
        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public String toString() {
            return from + " -> " + to + " (가중치: " + weight + ")";
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 벨만-포드 알고리즘 실행 ===");
        
        // 예시 그래프 설정
        // 5개의 정점 (0, 1, 2, 3, 4)과 음수 간선을 포함한 그래프
        int n = 5;  // 정점 개수
        List<Edge> edges = new ArrayList<>();
        
        // 간선 추가 (from, to, weight)
        edges.add(new Edge(0, 1, 6));   // 0 -> 1, 가중치 6
        edges.add(new Edge(0, 2, 7));   // 0 -> 2, 가중치 7  
        edges.add(new Edge(1, 2, 8));   // 1 -> 2, 가중치 8
        edges.add(new Edge(1, 3, -4));  // 1 -> 3, 가중치 -4 (음수!)
        edges.add(new Edge(1, 4, 5));   // 1 -> 4, 가중치 5
        edges.add(new Edge(2, 3, 9));   // 2 -> 3, 가중치 9
        edges.add(new Edge(2, 4, -3));  // 2 -> 4, 가중치 -3 (음수!)
        edges.add(new Edge(3, 4, 7));   // 3 -> 4, 가중치 7
        edges.add(new Edge(4, 3, 2));   // 4 -> 3, 가중치 2
        
        int start = 0;  // 시작 정점
        
        System.out.println("그래프 정보:");
        System.out.println("정점 개수: " + n);
        System.out.println("시작 정점: " + start);
        System.out.println("간선 목록:");
        for (Edge edge : edges) {
            System.out.println("  " + edge);
        }
        
        // 벨만-포드 알고리즘 실행
        long[] dist = new long[n];
        boolean hasNegativeCycle = bellmanFord(edges, n, start, dist);
        
        // 결과 출력
        System.out.println("\n=== 실행 결과 ===");
        if (hasNegativeCycle) {
            System.out.println("❌ 음수 사이클이 존재합니다!");
            System.out.println("   일부 정점까지의 최단 거리가 무한히 작아질 수 있습니다.");
        } else {
            System.out.println("✅ 음수 사이클이 없습니다. 최단 거리:");
            for (int i = 0; i < n; i++) {
                if (dist[i] == INF) {
                    System.out.println("  " + start + " -> " + i + ": 도달 불가");
                } else {
                    System.out.println("  " + start + " -> " + i + ": " + dist[i]);
                }
            }
        }
        
        System.out.println("\n=== 음수 사이클이 있는 경우 테스트 ===");
        testNegativeCycle();
    }
    
    /**
     * 벨만-포드 알고리즘의 핵심 구현
     * 
     * @param edges 간선 리스트
     * @param n 정점 개수  
     * @param start 시작 정점
     * @param dist 최단 거리 배열 (결과가 저장됨)
     * @return true면 음수 사이클 존재, false면 정상
     */
    static boolean bellmanFord(List<Edge> edges, int n, int start, long[] dist) {
        
        // 1단계: 거리 배열 초기화
        // 시작 정점은 0, 나머지는 무한대로 설정
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        System.out.println("\n--- 벨만-포드 알고리즘 단계별 실행 ---");
        System.out.println("초기 상태: " + Arrays.toString(dist));
        
        // 2단계: V-1번 반복하여 모든 간선에 대해 완화 수행
        // 왜 V-1번일까? 
        // - 최단 경로는 사이클을 포함하지 않으므로 최대 V-1개의 간선을 사용
        // - 각 반복에서 최소 하나의 정점의 최단 거리가 확정됨
        for (int i = 0; i < n - 1; i++) {
            System.out.println("\n" + (i + 1) + "번째 반복:");
            
            boolean updated = false;  // 이번 반복에서 갱신이 있었는지 확인
            
            // 모든 간선에 대해 완화(relaxation) 시도
            for (Edge edge : edges) {
                int u = edge.from;   // 출발 정점
                int v = edge.to;     // 도착 정점  
                long w = edge.weight; // 간선 가중치
                
                // 완화 조건 검사
                // 1) u까지의 경로가 존재해야 함 (dist[u] != INF)
                // 2) u를 거쳐 v로 가는 경로가 현재 알고 있는 경로보다 짧아야 함
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    long oldDist = dist[v];
                    dist[v] = dist[u] + w;  // 거리 갱신
                    updated = true;
                    
                    // 갱신 상황을 자세히 출력
                    System.out.println("  간선 " + edge + " 완화:");
                    System.out.println("    " + v + "번 정점 거리 갱신: " + 
                                     (oldDist == INF ? "INF" : oldDist) + 
                                     " -> " + dist[v]);
                }
            }
            
            System.out.println("  현재 상태: " + Arrays.toString(dist));
            
            // 조기 종료 최적화: 이번 반복에서 갱신이 없었다면 더 이상 갱신될 것이 없음
            if (!updated) {
                System.out.println("  ✅ 더 이상 갱신이 없으므로 조기 종료");
                break;
            }
        }
        
        // 3단계: 음수 사이클 탐지
        // V번째 반복에서도 거리가 갱신되면 음수 사이클이 존재
        // 왜 이렇게 탐지할 수 있을까?
        // - 음수 사이클이 없다면 V-1번 반복으로 모든 최단 거리가 확정됨
        // - 음수 사이클이 있다면 계속해서 거리가 줄어들 수 있음
        System.out.println("\n--- 음수 사이클 탐지 단계 ---");
        
        for (Edge edge : edges) {
            int u = edge.from;
            int v = edge.to;
            long w = edge.weight;
            
            // V번째에도 완화가 가능하다면 음수 사이클 존재
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                System.out.println("음수 사이클 발견!");
                System.out.println("  문제가 된 간선: " + edge);
                System.out.println("  현재 " + v + "까지 거리: " + dist[v]);
                System.out.println("  " + u + "을 거쳐 가는 거리: " + (dist[u] + w));
                return true;  // 음수 사이클 존재
            }
        }
        
        System.out.println("음수 사이클이 없습니다.");
        return false;  // 음수 사이클 없음
    }
    
    /**
     * 음수 사이클이 있는 경우를 테스트하는 메서드
     * 이해를 돕기 위한 추가 예시
     */
    static void testNegativeCycle() {
        // 음수 사이클이 있는 그래프 생성
        // 0 -> 1 -> 2 -> 1 형태의 음수 사이클
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));   // 0 -> 1, 가중치 1
        edges.add(new Edge(1, 2, -3));  // 1 -> 2, 가중치 -3  
        edges.add(new Edge(2, 1, 1));   // 2 -> 1, 가중치 1 (사이클 완성)
        
        // 1 -> 2 -> 1의 사이클 가중치: -3 + 1 = -2 (음수!)
        
        int n = 3;
        int start = 0;
        long[] dist = new long[n];
        
        System.out.println("음수 사이클 테스트용 그래프:");
        for (Edge edge : edges) {
            System.out.println("  " + edge);
        }
        System.out.println("예상 결과: 1->2->1 사이클의 가중치가 -2이므로 음수 사이클 존재");
        
        boolean hasNegativeCycle = bellmanFord(edges, n, start, dist);
        
        if (hasNegativeCycle) {
            System.out.println("✅ 정상적으로 음수 사이클을 탐지했습니다!");
        } else {
            System.out.println("❌ 음수 사이클 탐지에 실패했습니다.");
        }
    }
    
    /*
     * ========== 추가 활용 및 최적화 기법 ==========
     * 
     * 1. SPFA (Shortest Path Faster Algorithm)
     * - 벨만-포드의 최적화 버전
     * - 큐를 사용하여 실제로 갱신된 정점만 처리
     * - 평균적으로 O(E)에 가까운 성능
     * 
     * 2. 음수 사이클에 영향받는 정점 찾기
     * - 음수 사이클을 탐지한 후, 추가로 V번 더 실행
     * - 거리가 계속 줄어드는 정점들을 마킹
     * 
     * 3. 경로 복원
     * - parent 배열을 사용하여 실제 최단 경로 추적
     * 
     * 4. 메모리 최적화
     * - 인접 리스트 사용 시 메모리 사용량 최적화
     */
    
    /**
     * SPFA 알고리즘 구현 예시 (참고용)
     * 실제 코딩테스트에서 벨만-포드보다 빠른 성능을 원할 때 사용
     */
    static boolean spfa(List<List<Edge>> graph, int n, int start, long[] dist) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        boolean[] inQueue = new boolean[n];  // 큐에 있는지 확인
        int[] count = new int[n];  // 각 정점이 큐에 들어간 횟수
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        inQueue[start] = true;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;
            
            // u에서 출발하는 모든 간선에 대해 완화 시도
            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                long w = edge.weight;
                
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    
                    if (!inQueue[v]) {
                        queue.offer(v);
                        inQueue[v] = true;
                        count[v]++;
                        
                        // 한 정점이 n번 이상 큐에 들어가면 음수 사이클
                        if (count[v] >= n) {
                            return true;  // 음수 사이클 존재
                        }
                    }
                }
            }
        }
        
        return false;  // 음수 사이클 없음
    }
    
    /*
     * ========== 코딩테스트 실전 팁 ==========
     * 
     * 1. 언제 벨만-포드를 사용할까?
     *    - 문제에서 "음수 가중치" 언급
     *    - "돈을 벌면서 이동", "시간을 되돌리는" 등의 표현
     *    - 명시적으로 음수 사이클 탐지를 요구
     * 
     * 2. 구현 시 주의사항:
     *    - 오버플로우 방지를 위해 long 타입 사용
     *    - INF 값은 충분히 크게 설정 (Long.MAX_VALUE 권장)
     *    - 간선 리스트 vs 인접 리스트 선택 (보통 간선 리스트가 구현이 쉬움)
     * 
     * 3. 최적화 기법:
     *    - 조기 종료: 한 라운드에서 갱신이 없으면 종료
     *    - SPFA 사용: 평균적으로 더 빠른 성능
     *    - 음수 사이클이 없다는 보장이 있으면 다익스트라 고려
     * 
     * 4. 디버깅 팁:
     *    - 각 라운드별 거리 배열 상태 출력
     *    - 어떤 간선에서 완화가 일어나는지 추적
     *    - 음수 사이클 탐지 시 구체적인 사이클 경로 출력
     */
}