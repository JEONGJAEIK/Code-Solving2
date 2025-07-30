package org.example;

import java.util.*;

/*
 * 유니온 파인드 (Union-Find) / 분리 집합 (Disjoint Set) - 코딩테스트 필수 알고리즘
 *
 * 【기본 원리】
 * - 서로소 집합들을 효율적으로 관리하는 자료구조
 * - 두 개의 핵심 연산: Union(합치기), Find(찾기)
 * - 각 집합을 트리 구조로 표현하고, 루트 노드가 집합의 대표자 역할
 * - "같은 그룹에 속하는가?"를 빠르게 판단할 수 있음
 *
 * 【시간복잡도】
 * - Find: O(α(n)) - 아커만 함수의 역함수, 거의 상수시간
 * - Union: O(α(n)) - Path Compression + Union by Rank 적용시
 * - 전체적으로 거의 O(1)에 가까운 성능 (실질적으로 상수시간)
 * - 공간복잡도: O(n) - parent 배열과 rank 배열
 *
 * 【사전 지식】
 * - 트리 구조에 대한 기본 이해
 * - 재귀 함수의 개념
 * - 그래프의 연결성 개념
 * - 집합의 개념 (교집합이 없는 서로소 집합)
 *
 * 【코딩테스트 출제 유형】
 * 1. 직접 출제: "친구 관계", "네트워크 연결", "그룹 나누기"
 * 2. 그래프 문제: 연결 요소 개수, 사이클 판별
 * 3. MST (최소 신장 트리): 크루스칼 알고리즘의 핵심 부품
 * 4. 동적 연결성: 실시간으로 연결/분리되는 관계 관리
 * 5. 격자 문제: 2D 배열에서 연결된 영역 찾기
 * 6. 온라인 쿼리: 연결 상태를 실시간으로 확인하는 문제
 *
 * 【혼동하기 쉬운 유사 알고리즘들】
 *
 * 1. DFS/BFS (깊이우선탐색/너비우선탐색)
 *    - 착각 포인트: 연결성을 확인한다는 공통점
 *    - Union-Find: 동적으로 연결 관계가 변할 때, 빠른 집합 판별
 *    - DFS/BFS: 정적인 그래프에서 경로 찾기나 전체 탐색
 *    - 실수: 단순 연결성 확인에 DFS 대신 Union-Find 사용 (오버킬)
 *
 * 2. 크루스칼 vs 프림 알고리즘 (MST)
 *    - 착각 포인트: 둘 다 MST를 구하지만 Union-Find는 크루스칼에만 필요
 *    - 크루스칼: 간선을 정렬 후 Union-Find로 사이클 방지
 *    - 프림: 정점 기반으로 확장, 우선순위 큐 사용
 *    - 실수: 프림 알고리즘에 Union-Find 사용하려는 시도
 *
 * 3. Flood Fill (플러드 필)
 *    - 착각 포인트: 2D 격자에서 연결된 영역을 다룸
 *    - Union-Find: 동적으로 연결 관계가 바뀌는 경우
 *    - Flood Fill: 한 번 정해진 연결 관계에서 영역 색칠
 *    - 실수: 정적인 격자 문제에 Union-Find 사용 (비효율)
 *
 * 4. 강연결 요소 (SCC)
 *    - 착각 포인트: 그래프의 연결성을 다룬다는 점
 *    - Union-Find: 무방향 그래프의 연결 요소
 *    - SCC: 방향 그래프에서 서로 도달 가능한 정점들의 집합
 *    - 실수: 방향 그래프 문제에 Union-Find 적용
 */

public class 유니온파인드 {

    public static void main(String[] args) {
        // 실제 코딩테스트 예시: 친구 관계 네트워크
        int n = 6; // 사람 수 (0번부터 5번까지)

        // Union-Find 자료구조 초기화
        UnionFindDS uf = new UnionFindDS(n);

        // 친구 관계 형성 (실제 문제에서는 입력으로 받음)
        uf.union(0, 1); // 0번과 1번이 친구
        uf.union(1, 2); // 1번과 2번이 친구 (간접적으로 0-2도 연결)
        uf.union(3, 4); // 3번과 4번이 친구

        // 쿼리 처리 예시
        System.out.println("0과 2가 같은 그룹인가? " + uf.isConnected(0, 2)); // true
        System.out.println("0과 3이 같은 그룹인가? " + uf.isConnected(0, 3)); // false
        System.out.println("전체 그룹 개수: " + uf.getComponentCount()); // 3개 그룹

        // 추가 연결
        uf.union(2, 3); // 두 그룹을 하나로 합침
        System.out.println("연결 후 그룹 개수: " + uf.getComponentCount()); // 2개 그룹
    }

    /**
     * Union-Find 자료구조 구현 클래스
     * Path Compression + Union by Rank 최적화 적용
     */
    static class UnionFindDS {
        private int[] parent; // 각 노드의 부모를 저장 (루트는 자기 자신)
        private int[] rank;   // 각 트리의 높이 (Union by Rank 최적화용)
        private int componentCount; // 현재 연결 요소의 개수

        /**
         * 생성자: n개의 독립적인 집합으로 초기화
         */
        public UnionFindDS(int n) {
            parent = new int[n];
            rank = new int[n];
            componentCount = n; // 처음에는 모든 노드가 독립적인 집합

            // 초기화: 각 노드가 자기 자신을 부모로 가짐 (자기 자신이 루트)
            for (int i = 0; i < n; i++) {
                parent[i] = i; // 자기 자신이 집합의 대표자
                rank[i] = 0;   // 초기 높이는 0
            }
        }

        /**
         * Find 연산: x가 속한 집합의 루트(대표자)를 찾음
         * Path Compression 최적화 적용
         *
         * 핵심 아이디어: 루트를 찾아가는 경로상의 모든 노드들을
         * 직접 루트에 연결시켜서 다음 탐색을 빠르게 만듦
         */
        public int find(int x) {
            // 기저 조건: x가 루트라면 (자기 자신이 부모라면) 반환
            if (parent[x] == x) {
                return x;
            }

            // Path Compression: 재귀적으로 루트를 찾으면서
            // 경로상의 모든 노드의 부모를 루트로 직접 연결
            // 이렇게 하면 다음번 find 호출시 O(1)에 루트를 찾을 수 있음
            return parent[x] = find(parent[x]);

            /* Path Compression 없는 버전 (이해용)
            if (parent[x] == x) return x;
            return find(parent[x]);
            */
        }

        /**
         * Union 연산: x와 y가 속한 집합을 합침
         * Union by Rank 최적화 적용
         *
         * 핵심 아이디어: 항상 높이가 낮은 트리를 높은 트리의 루트 아래에 붙임
         * 이렇게 하면 트리의 높이 증가를 최소화할 수 있음
         */
        public void union(int x, int y) {
            // 1단계: 각각의 루트를 찾음
            int rootX = find(x);
            int rootY = find(y);

            // 2단계: 이미 같은 집합에 속해있다면 아무것도 하지 않음
            if (rootX == rootY) {
                return;
            }

            // 3단계: Union by Rank - 높이가 낮은 트리를 높은 트리 아래에 붙임
            if (rank[rootX] < rank[rootY]) {
                // rootX의 높이가 더 낮으므로 rootY 아래에 붙임
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                // rootY의 높이가 더 낮으므로 rootX 아래에 붙임
                parent[rootY] = rootX;
            } else {
                // 높이가 같다면 아무거나 선택하고 높이를 1 증가
                parent[rootY] = rootX;
                rank[rootX]++; // 같은 높이의 트리를 합치면 높이가 1 증가
            }

            // 4단계: 연결 요소 개수 감소 (두 집합이 하나가 되었으므로)
            componentCount--;
        }

        /**
         * 두 노드가 같은 집합에 속하는지 확인
         * 가장 자주 사용되는 쿼리 함수
         */
        public boolean isConnected(int x, int y) {
            return find(x) == find(y); // 루트가 같으면 같은 집합
        }

        /**
         * 현재 연결 요소(집합)의 개수 반환
         * 그래프 문제에서 자주 요구되는 정보
         */
        public int getComponentCount() {
            return componentCount;
        }

        /**
         * 특정 집합의 크기를 구하는 함수 (선택적 기능)
         * 일부 문제에서 "가장 큰 그룹의 크기" 등을 물어볼 때 유용
         */
        public int getComponentSize(int x) {
            int root = find(x);
            int size = 0;

            // 모든 노드를 확인해서 같은 루트를 가진 노드 개수 세기
            // 주의: 이 방법은 O(n)이므로 자주 호출하면 비효율적
            for (int i = 0; i < parent.length; i++) {
                if (find(i) == root) {
                    size++;
                }
            }
            return size;
        }

        /**
         * 모든 집합의 크기를 한 번에 구하는 최적화된 버전
         * 여러 집합의 크기가 모두 필요한 경우 사용
         */
        public Map<Integer, Integer> getAllComponentSizes() {
            Map<Integer, Integer> sizeMap = new HashMap<>();

            for (int i = 0; i < parent.length; i++) {
                int root = find(i);
                sizeMap.put(root, sizeMap.getOrDefault(root, 0) + 1);
            }

            return sizeMap;
        }
    }

    /**
     * 실전 활용 예시 1: 크루스칼 알고리즘에서의 사용
     * MST 문제에서 사이클 방지용으로 사용
     */
    public static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    /**
     * 크루스칼 알고리즘 예시 (MST 구하기)
     */
    public static long kruskalMST(int n, List<Edge> edges) {
        // 1. 간선을 가중치 순으로 정렬
        Collections.sort(edges);

        // 2. Union-Find 초기화
        UnionFindDS uf = new UnionFindDS(n);

        long totalWeight = 0;
        int edgeCount = 0;

        // 3. 간선을 하나씩 검사하며 MST 구성
        for (Edge edge : edges) {
            // 사이클을 형성하지 않는 간선만 선택
            if (!uf.isConnected(edge.from, edge.to)) {
                uf.union(edge.from, edge.to);
                totalWeight += edge.weight;
                edgeCount++;

                // MST는 정확히 n-1개의 간선을 가짐
                if (edgeCount == n - 1) {
                    break;
                }
            }
        }

        return totalWeight;
    }

    /**
     * 실전 활용 예시 2: 2D 격자에서의 Union-Find
     * 격자 좌표를 1차원 인덱스로 변환하여 사용
     */
    public static int numIslands2D(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // 2D 좌표를 1D 인덱스로 변환: (r, c) -> r * cols + c
        UnionFindDS uf = new UnionFindDS(rows * cols);

        // 방향 배열: 상하좌우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++; // 일단 섬으로 카운트

                    // 인접한 4방향 확인
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        // 경계 확인 및 섬인지 확인
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                                grid[nr][nc] == '1') {

                            int current = r * cols + c;
                            int neighbor = nr * cols + nc;

                            // 아직 연결되지 않았다면 연결하고 섬 개수 감소
                            if (!uf.isConnected(current, neighbor)) {
                                uf.union(current, neighbor);
                                islands--; // 두 섬이 하나로 합쳐짐
                            }
                        }
                    }
                }
            }
        }

        return islands;
    }
}