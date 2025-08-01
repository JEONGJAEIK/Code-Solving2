package org.example;

import java.util.*;

/*
=== 그래프 표현 방법들 (Graph Representation) ===
【목표】
【기본 개념】
그래프를 메모리에 저장하는 방법에는 크게 3가지가 있음:
1. 인접 행렬 (Adjacency Matrix)
2. 인접 리스트 (Adjacency List)
3. 간선 리스트 (Edge List)

【각 방법의 특징】
1. 인접 행렬: 2차원 배열로 표현, 정점 간 연결 여부를 O(1)에 확인 가능
2. 인접 리스트: ArrayList 배열로 표현, 메모리 효율적이고 인접 정점 탐색 빠름
3. 간선 리스트: 간선 정보만 저장, 크루스칼 알고리즘 등에서 주로 사용

【시간복잡도 비교】
                  인접행렬    인접리스트    간선리스트
정점 추가           O(V²)      O(1)        O(1)
간선 추가           O(1)       O(1)        O(1)
간선 존재 확인      O(1)       O(degree)   O(E)
정점의 모든 인접점  O(V)       O(degree)   O(E)
공간복잡도         O(V²)      O(V+E)      O(E)

【적용 문제 유형】
1. 인접 행렬 사용 문제:
   - 플로이드-워셜 (모든 쌍 최단경로)
   - 정점 수가 적고 간선이 많은 경우
   - 간선 존재 여부를 자주 확인해야 하는 경우

2. 인접 리스트 사용 문제:
   - DFS/BFS 탐색
   - 다익스트라, 벨만-포드
   - 위상정렬
   - 대부분의 그래프 알고리즘 (가장 범용적)

3. 간선 리스트 사용 문제:
   - 크루스칼 알고리즘 (MST)
   - 간선을 정렬해야 하는 경우
   - Union-Find와 함께 사용하는 경우

【착각하기 쉬운 점들】
1. 메모리 사용량 착각:
   - 인접 행렬: 정점이 1000개면 1,000,000개 공간 필요 (메모리 초과 주의)
   - 인접 리스트: 간선 수만큼만 공간 사용 (희소 그래프에 유리)

2. 정점 번호 처리 착각:
   - 0번부터 시작 vs 1번부터 시작 혼동
   - 배열 크기를 잘못 설정하는 경우

3. 방향 그래프 vs 무방향 그래프:
   - 무방향: 양쪽 다 저장해야 함 (a->b, b->a)
   - 방향: 한쪽만 저장

4. 가중치 그래프 처리:
   - boolean 배열 vs int 배열 vs 객체 사용 구분
   - 가중치가 0인 경우와 간선이 없는 경우 구분

【선행 지식】
- 그래프 기본 용어 (정점, 간선, 차수, 방향성)
- ArrayList, 배열의 기본 사용법
- 클래스와 객체 (가중치 그래프용)
*/

public class 그래프변환 {

    // 간선 정보를 저장하는 클래스 (가중치 그래프용)
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        // 크루스칼 알고리즘 등에서 가중치 기준 정렬을 위해 필요
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    // 인접 리스트에서 사용할 노드 클래스 (가중치 포함)
    static class Node {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        // 예시 그래프 정보 (실제 코딩테스트에서는 입력으로 받음)
        int n = 5; // 정점의 개수 (1번부터 5번까지)

        // 간선 정보: {시작점, 끝점, 가중치}
        int[][] edges = {
                {1, 2, 3},
                {1, 3, 2},
                {2, 3, 1},
                {2, 4, 5},
                {3, 4, 2},
                {3, 5, 4},
                {4, 5, 1}
        };

        System.out.println("=== 1. 인접 행렬 (Adjacency Matrix) ===");
        createAdjacencyMatrix(n, edges);

        System.out.println("\n=== 2. 인접 리스트 (Adjacency List) ===");
        createAdjacencyList(n, edges);

        System.out.println("\n=== 3. 간선 리스트 (Edge List) ===");
        createEdgeList(n, edges);
    }

    // 1. 인접 행렬로 그래프 표현
    static void createAdjacencyMatrix(int n, int[][] edges) {
        // 가중치가 있는 경우: int 배열 사용
        // 가중치가 없는 경우: boolean 배열도 가능
        int[][] matrix = new int[n + 1][n + 1]; // 1번부터 시작하므로 n+1 크기

        // 초기화: 연결되지 않은 경우를 나타내는 값 설정
        // 주의: 가중치가 0일 수 있는 경우 -1이나 INF 사용
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    matrix[i][j] = 0; // 자기 자신으로의 거리는 0
                } else {
                    matrix[i][j] = -1; // 연결되지 않음을 나타냄 (또는 INF 사용)
                }
            }
        }

        // 간선 정보를 행렬에 저장
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            matrix[from][to] = weight;
            // 무방향 그래프인 경우 아래 줄도 추가
            // matrix[to][from] = weight;
        }

        // 실전에서는 출력하지 않지만, 이해를 위해 표시
        System.out.println("인접 행렬 생성 완료");

        // 사용 예시: 특정 간선 존재 여부 확인 (O(1))
        int checkFrom = 1, checkTo = 3;
        if (matrix[checkFrom][checkTo] != -1) {
            System.out.println(checkFrom + "에서 " + checkTo + "로 가는 간선 존재, 가중치: " + matrix[checkFrom][checkTo]);
        }

        // 메모리 사용량: O(V²) - 정점이 많고 간선이 적으면 메모리 낭비
        // 장점: 간선 존재 확인이 O(1)
        // 단점: 공간복잡도가 높음, 희소 그래프에는 비효율적
    }

    // 2. 인접 리스트로 그래프 표현
    static void createAdjacencyList(int n, int[][] edges) {
        // 방법 1: ArrayList 배열 사용 (가장 일반적)
        List<List<Node>> graph = new ArrayList<>();

        // 초기화: 각 정점마다 빈 리스트 생성
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보를 리스트에 저장
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            // 방향 그래프: from -> to만 저장
            graph.get(from).add(new Node(to, weight));

            // 무방향 그래프인 경우 아래 줄도 추가
            // graph.get(to).add(new Node(from, weight));
        }

        System.out.println("인접 리스트 생성 완료");

        // 사용 예시 1: 특정 정점의 모든 인접 정점 탐색 (DFS/BFS에서 자주 사용)
        int vertex = 1;
        System.out.print(vertex + "번 정점과 연결된 정점들: ");
        for (Node node : graph.get(vertex)) {
            System.out.print(node.vertex + "(가중치:" + node.weight + ") ");
        }
        System.out.println();

        // 메모리 사용량: O(V + E) - 실제 간선 수만큼만 사용
        // 장점: 메모리 효율적, 인접 정점 탐색 빠름
        // 단점: 특정 간선 존재 확인이 O(degree)

        // 방법 2: 가중치가 없는 경우 간단한 버전
        List<List<Integer>> simpleGraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            simpleGraph.add(new ArrayList<>());
        }

        // 가중치 없는 간선 추가 예시
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            simpleGraph.get(from).add(to);
            // 무방향 그래프면: simpleGraph.get(to).add(from);
        }
    }

    // 3. 간선 리스트로 그래프 표현
    static void createEdgeList(int n, int[][] edges) {
        // ArrayList를 사용한 간선 리스트
        List<Edge> edgeList = new ArrayList<>();

        // 간선 정보를 리스트에 저장
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            edgeList.add(new Edge(from, to, weight));

            // 무방향 그래프인 경우 역방향도 추가
            // edgeList.add(new Edge(to, from, weight));
        }

        System.out.println("간선 리스트 생성 완료");

        // 사용 예시: 크루스칼 알고리즘에서 가중치 기준 정렬
        Collections.sort(edgeList); // Comparable 인터페이스로 정렬

        System.out.println("가중치 기준 정렬된 간선들:");
        for (int i = 0; i < Math.min(3, edgeList.size()); i++) { // 처음 3개만 출력
            Edge e = edgeList.get(i);
            System.out.println(e.from + " -> " + e.to + " (가중치: " + e.weight + ")");
        }

        // 메모리 사용량: O(E) - 간선 수만큼만 사용
        // 장점: 간선 중심 알고리즘에 적합, 정렬 가능
        // 단점: 특정 정점의 인접 정점 찾기 어려움
    }

    /*
    【실전 팁과 주의사항】

    1. 정점 번호 처리:
       - 문제에서 1번부터 시작하면 배열 크기를 n+1로 설정
       - 0번부터 시작하면 배열 크기를 n으로 설정
       - 입력받은 정점 번호를 그대로 인덱스로 사용할지 결정

    2. 메모리 제한 고려:
       - 정점 1000개 이하: 인접 행렬 사용 가능
       - 정점 10만 개 이상: 인접 리스트 필수
       - 간선이 적은 희소 그래프: 인접 리스트가 유리

    3. 알고리즘별 선택 기준:
       - DFS/BFS: 인접 리스트
       - 플로이드-워셜: 인접 행렬
       - 다익스트라: 인접 리스트 + 우선순위 큐
       - 크루스칼: 간선 리스트
       - 프림: 인접 리스트

    4. 가중치 처리:
       - 가중치가 0일 수 있으면 boolean 배열 사용 불가
       - 간선이 없음을 나타낼 특별한 값 필요 (-1, INF 등)
       - 음수 가중치가 있으면 초기값 설정 주의

    5. 방향성 처리:
       - 방향 그래프: 한 방향만 저장
       - 무방향 그래프: 양방향 모두 저장 필요
       - 실수하기 쉬운 부분이므로 문제를 정확히 읽을 것

    6. 코딩테스트에서 자주 하는 실수:
       - 배열 인덱스 범위 오류 (0 vs 1 시작)
       - 무방향 그래프에서 한쪽 방향만 저장
       - 메모리 초과 (정점 수가 많은데 인접 행렬 사용)
       - 가중치와 간선 존재 여부 혼동
    */
}