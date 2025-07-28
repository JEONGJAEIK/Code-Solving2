package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.*;

/*
=== 그래프 변환법 기본 개념 ===
- 그래프를 다양한 자료구조로 표현하고 상호 변환하는 기법
- 인접행렬 ↔ 인접리스트 ↔ 간선리스트 변환이 핵심
- 시간복잡도:
  * 인접행렬 → 인접리스트: O(V²)
  * 인접리스트 → 인접행렬: O(V + E)
  * 간선리스트 → 인접리스트: O(E)
- 공간복잡도: 인접행렬 O(V²), 인접리스트 O(V + E)

=== 선수 지식 ===
1. 그래프의 기본 개념 (정점, 간선, 방향성)
2. 배열과 리스트 자료구조
3. 2차원 배열 조작
4. ArrayList와 HashMap 사용법

=== 코딩테스트에서 그래프 변환이 사용되는 문제 유형 ===
1. 그래프 입력 형태가 다양한 문제
   - 간선 정보로 주어져서 인접리스트로 변환 필요
   - 인접행렬로 주어져서 인접리스트로 변환하여 메모리 절약
2. 그래프 알고리즘 적용 전 전처리
   - DFS/BFS 실행 전 적절한 형태로 변환
   - 최단경로 알고리즘 실행 전 형태 변환
3. 양방향 그래프 처리
   - 무방향 그래프를 방향 그래프로 변환
4. 가중치 그래프 변환
   - 가중치 정보 포함한 그래프 표현 변환
5. 그래프 압축 및 최적화 문제

=== 혼동하기 쉬운 유사 개념들 ===
1. 인접행렬 vs 인접리스트
   - 인접행렬: V²공간, 간선 존재 확인 O(1), 밀집 그래프에 유리
   - 인접리스트: V+E공간, 간선 존재 확인 O(degree), 희소 그래프에 유리

2. 방향 그래프 vs 무방향 그래프 변환
   - 무방향: 양쪽 모두에 간선 추가
   - 방향: 한 쪽만 간선 추가

3. 가중치 그래프 vs 단순 그래프
   - 가중치: 간선에 비용 정보 포함
   - 단순: 간선 존재 여부만 표현

=== 실수하기 쉬운 접근법 ===
- 메모리 제한 고려 없이 항상 인접행렬 사용
- 무방향 그래프에서 양방향 간선 추가 누락
- 1-based 인덱스와 0-based 인덱스 혼동
- 가중치 그래프에서 가중치 정보 손실
- 중복 간선 처리 미고려

=== 언제 어떤 표현을 사용할까? ===
- 인접행렬: 정점 수가 적고(≤1000), 간선이 많은 경우, 간선 존재 확인이 빈번한 경우
- 인접리스트: 정점 수가 많거나, 간선이 적은 경우, DFS/BFS 탐색이 주목적
- 간선리스트: 크루스칼 알고리즘, 간선 정렬이 필요한 경우
*/

public class 그래프변환 {

    // 간선 정보를 저장하는 클래스 (가중치 그래프용)
    static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        // 가중치 없는 간선용 생성자
        Edge(int from, int to) {
            this(from, to, 1);
        }

        @Override
        public String toString() {
            return "(" + from + "->" + to + ", w:" + weight + ")";
        }
    }

    public static void main(String[] args) {
        // 실전 시나리오: 다양한 형태의 그래프 입력을 처리

        // 예시 1: 간선 리스트로 주어진 무방향 그래프
        int vertices = 5;
        int[][] edges = {
                {0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}
        };

        System.out.println("=== 간선 리스트 → 인접 리스트 변환 ===");
        List<List<Integer>> adjList = edgeListToAdjList(edges, vertices, false);
        printAdjList(adjList);

        System.out.println("\n=== 인접 리스트 → 인접 행렬 변환 ===");
        int[][] adjMatrix = adjListToAdjMatrix(adjList);
        printAdjMatrix(adjMatrix);

        System.out.println("\n=== 인접 행렬 → 간선 리스트 변환 ===");
        List<Edge> edgeList = adjMatrixToEdgeList(adjMatrix);
        printEdgeList(edgeList);

        // 예시 2: 가중치 그래프 처리
        System.out.println("\n=== 가중치 그래프 변환 ===");
        int[][] weightedEdges = {
                {0, 1, 5}, {0, 2, 3}, {1, 3, 2}, {2, 3, 4}, {3, 4, 1}
        };

        List<List<Edge>> weightedAdjList = weightedEdgeListToAdjList(weightedEdges, vertices, false);
        printWeightedAdjList(weightedAdjList);
    }

    /**
     * 간선 리스트를 인접 리스트로 변환
     * @param edges 간선 배열 [from, to] 형태
     * @param vertices 정점의 개수
     * @param isDirected 방향 그래프 여부
     * @return 인접 리스트
     */
    public static List<List<Integer>> edgeListToAdjList(int[][] edges, int vertices, boolean isDirected) {
        // 각 정점마다 연결된 정점들을 저장할 리스트 초기화
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // 각 간선 정보를 인접 리스트에 추가
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            // 방향 그래프든 무방향 그래프든 일단 from → to 추가
            adjList.get(from).add(to);

            // 무방향 그래프인 경우 to → from도 추가
            // 이 부분이 핵심! 무방향 그래프는 양방향 연결
            if (!isDirected) {
                adjList.get(to).add(from);
            }
        }

        return adjList;
    }

    /**
     * 인접 리스트를 인접 행렬로 변환
     * @param adjList 인접 리스트
     * @return 인접 행렬
     */
    public static int[][] adjListToAdjMatrix(List<List<Integer>> adjList) {
        int vertices = adjList.size();
        int[][] adjMatrix = new int[vertices][vertices];

        // 인접 리스트의 각 정점에 대해
        for (int i = 0; i < vertices; i++) {
            // 해당 정점과 연결된 모든 정점들을 확인
            for (int neighbor : adjList.get(i)) {
                // 인접 행렬에 연결 표시 (1로 설정)
                adjMatrix[i][neighbor] = 1;
            }
        }

        return adjMatrix;
    }

    /**
     * 인접 행렬을 간선 리스트로 변환
     * @param adjMatrix 인접 행렬
     * @return 간선 리스트
     */
    public static List<Edge> adjMatrixToEdgeList(int[][] adjMatrix) {
        List<Edge> edges = new ArrayList<>();
        int vertices = adjMatrix.length;

        // 인접 행렬의 모든 셀을 확인
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                // 간선이 존재하는 경우 (0이 아닌 값)
                if (adjMatrix[i][j] != 0) {
                    // 간선 정보를 리스트에 추가
                    // 가중치가 있는 경우 해당 값을, 없는 경우 1을 가중치로 사용
                    edges.add(new Edge(i, j, adjMatrix[i][j]));
                }
            }
        }

        return edges;
    }

    /**
     * 가중치가 있는 간선 리스트를 가중치 인접 리스트로 변환
     * @param weightedEdges 가중치 간선 배열 [from, to, weight] 형태
     * @param vertices 정점의 개수
     * @param isDirected 방향 그래프 여부
     * @return 가중치 인접 리스트
     */
    public static List<List<Edge>> weightedEdgeListToAdjList(int[][] weightedEdges, int vertices, boolean isDirected) {
        // 각 정점마다 연결된 간선들을 저장할 리스트 초기화
        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // 각 간선 정보를 인접 리스트에 추가
        for (int[] edge : weightedEdges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            // from에서 to로 가는 간선 추가
            adjList.get(from).add(new Edge(from, to, weight));

            // 무방향 그래프인 경우 반대 방향 간선도 추가
            if (!isDirected) {
                adjList.get(to).add(new Edge(to, from, weight));
            }
        }

        return adjList;
    }

    /**
     * 가중치 인접 리스트를 가중치 인접 행렬로 변환
     * @param weightedAdjList 가중치 인접 리스트
     * @return 가중치 인접 행렬
     */
    public static int[][] weightedAdjListToMatrix(List<List<Edge>> weightedAdjList) {
        int vertices = weightedAdjList.size();
        int[][] adjMatrix = new int[vertices][vertices];

        // 초기값을 무한대로 설정 (연결되지 않은 경우)
        // 실전에서는 문제에 따라 -1, 0, Integer.MAX_VALUE 등 사용
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i != j) {
                    adjMatrix[i][j] = Integer.MAX_VALUE; // 무한대 표시
                }
            }
        }

        // 각 정점의 연결된 간선들을 행렬에 반영
        for (int i = 0; i < vertices; i++) {
            for (Edge edge : weightedAdjList.get(i)) {
                adjMatrix[edge.from][edge.to] = edge.weight;
            }
        }

        return adjMatrix;
    }

    /**
     * 1-based 인덱스를 0-based 인덱스로 변환
     * 실전에서 매우 자주 사용되는 유틸리티 함수
     */
    public static List<List<Integer>> convertToZeroBased(int[][] edges, int vertices) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            // 1-based에서 0-based로 변환 (1을 빼기)
            int from = edge[0] - 1;
            int to = edge[1] - 1;

            adjList.get(from).add(to);
            adjList.get(to).add(from); // 무방향 그래프 가정
        }

        return adjList;
    }

    /**
     * 중복 간선과 자기 자신으로의 간선을 제거하는 함수
     * 실전에서 그래프 정제가 필요한 경우 사용
     */
    public static List<List<Integer>> removeDuplicateEdges(List<List<Integer>> adjList) {
        List<List<Integer>> cleanedList = new ArrayList<>();

        for (int i = 0; i < adjList.size(); i++) {
            // Set을 사용해서 중복 제거
            Set<Integer> uniqueNeighbors = new HashSet<>(adjList.get(i));

            // 자기 자신으로의 간선 제거 (self-loop 제거)
            uniqueNeighbors.remove(i);

            // 다시 List로 변환해서 추가
            cleanedList.add(new ArrayList<>(uniqueNeighbors));
        }

        return cleanedList;
    }

    /**
     * 그래프의 차수(degree) 계산 함수
     * 각 정점의 연결된 간선 수를 반환
     */
    public static int[] calculateDegrees(List<List<Integer>> adjList) {
        int[] degrees = new int[adjList.size()];

        for (int i = 0; i < adjList.size(); i++) {
            degrees[i] = adjList.get(i).size();
        }

        return degrees;
    }

    // === 출력 유틸리티 함수들 (실전에서는 보통 불필요) ===

    private static void printAdjList(List<List<Integer>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(i + ": " + adjList.get(i));
        }
    }

    private static void printAdjMatrix(int[][] adjMatrix) {
        for (int[] row : adjMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void printEdgeList(List<Edge> edges) {
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }

    private static void printWeightedAdjList(List<List<Edge>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + ": ");
            for (Edge edge : adjList.get(i)) {
                System.out.print("(" + edge.to + ",w:" + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}
