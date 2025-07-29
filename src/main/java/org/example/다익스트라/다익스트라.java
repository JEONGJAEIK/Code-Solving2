package org.example.다익스트라;

import java.util.*;

/*
=== 다익스트라(Dijkstra) 알고리즘 ===

【알고리즘 개념】
다익스트라 알고리즘은 그래프에서 한 정점에서 다른 모든 정점까지의 최단 경로를 찾는 알고리즘입니다.
"가중치가 있는 그래프"에서 사용되며, 모든 간선의 가중치는 0 이상이어야 합니다.

【동작 원리】
1. 시작점에서 각 정점까지의 거리를 무한대로 초기화 (시작점은 0)
2. 아직 방문하지 않은 정점 중 거리가 가장 짧은 정점을 선택
3. 선택된 정점을 통해 인접한 정점들로 가는 거리를 계산하여 더 짧으면 업데이트
4. 모든 정점을 방문할 때까지 2-3 과정을 반복

【시간복잡도】
- 우선순위 큐 사용 시: O((V + E) log V)
  * V: 정점의 개수, E: 간선의 개수
- 배열 사용 시: O(V²)

【사전 지식】
- 그래프의 기본 개념 (정점, 간선, 가중치)
- 우선순위 큐(Priority Queue)의 개념
- 인접 리스트를 이용한 그래프 표현법
*/

public class 다익스트라 {
    
    // 그래프의 간선을 나타내는 클래스
    // 목적지 정점과 그 정점까지의 가중치(비용)를 저장
    static class Edge {
        int destination;  // 도착 정점 번호
        int weight;       // 간선의 가중치 (거리, 비용 등)
        
        // 생성자: 목적지와 가중치를 받아서 간선 객체 생성
        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    // 우선순위 큐에서 사용할 노드 클래스
    // 현재까지 계산된 최단거리와 정점 번호를 저장
    static class Node implements Comparable<Node> {
        int vertex;    // 정점 번호
        int distance;  // 시작점에서 이 정점까지의 현재 최단거리
        
        // 생성자: 정점 번호와 거리를 받아서 노드 객체 생성
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        // 우선순위 큐에서 거리가 짧은 순서대로 정렬하기 위한 비교 메서드
        // 거리가 짧을수록 우선순위가 높음 (먼저 처리됨)
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    public static void main(String[] args) {
        // 예시 그래프 생성 및 다익스트라 알고리즘 실행
        다익스트라 dijkstra = new 다익스트라();
        
        System.out.println("=== 다익스트라 알고리즘 실행 ===\n");
        
        // 예시 그래프 실행 (정점 6개, 시작점 0번)
        dijkstra.runExample();
    }
    // 예시 그래프를 만들고 다익스트라 알고리즘을 실행하는 메서드
    public void runExample() {
        // 6개의 정점을 가진 그래프 생성 (정점 번호: 0, 1, 2, 3, 4, 5)
        int vertices = 6;
        List<List<Edge>> graph = new ArrayList<>();

        // 각 정점마다 인접 리스트 초기화
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프의 간선들을 추가 (방향 그래프)
        // addEdge(그래프, 시작정점, 도착정점, 가중치)
        addEdge(graph, 0, 1, 4);   // 0 → 1, 가중치 4
        addEdge(graph, 0, 2, 2);   // 0 → 2, 가중치 2
        addEdge(graph, 1, 2, 1);   // 1 → 2, 가중치 1
        addEdge(graph, 1, 3, 5);   // 1 → 3, 가중치 5
        addEdge(graph, 2, 3, 8);   // 2 → 3, 가중치 8
        addEdge(graph, 2, 4, 10);  // 2 → 4, 가중치 10
        addEdge(graph, 3, 4, 2);   // 3 → 4, 가중치 2
        addEdge(graph, 3, 5, 6);   // 3 → 5, 가중치 6
        addEdge(graph, 4, 5, 3);   // 4 → 5, 가중치 3

        // 생성된 그래프 정보 출력
        System.out.println("생성된 그래프 정보:");
        printGraph(graph);
        System.out.println();

        // 시작점 0에서 다익스트라 알고리즘 실행
        int startVertex = 0;
        int[] shortestDistances = dijkstra(graph, startVertex);

        // 최종 결과 출력
        System.out.println("=== 최종 결과 ===");
        System.out.println("정점 " + startVertex + "에서 각 정점까지의 최단거리:");
        for (int i = 0; i < shortestDistances.length; i++) {
            String distance = (shortestDistances[i] == Integer.MAX_VALUE) ? "도달 불가능" : String.valueOf(shortestDistances[i]);
            System.out.println("정점 " + startVertex + " → 정점 " + i + ": " + distance);
        }

        // 경로 추적 예시 (정점 0에서 정점 5까지의 경로)
        System.out.println();
        System.out.println("예시: 정점 0에서 정점 5까지의 최단 경로 분석");
        System.out.println("최단거리: " + shortestDistances[5]);
        System.out.println("가능한 경로: 0 → 2 → 4 → 5 (거리: 2 + 10 + 3 = 15)");
        System.out.println("더 짧은 경로: 0 → 1 → 3 → 5 (거리: 4 + 5 + 6 = 15)");
        System.out.println("실제 최단경로: 0 → 1 → 3 → 4 → 5 (거리: 4 + 5 + 2 + 3 = 14)");
    }
    // 다익스트라 알고리즘의 핵심 구현 메서드
    public int[] dijkstra(List<List<Edge>> graph, int start) {
        int n = graph.size();  // 정점의 개수
        
        // 시작점에서 각 정점까지의 최단거리를 저장하는 배열
        // 처음에는 모든 거리를 무한대(Integer.MAX_VALUE)로 초기화
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        
        // 각 정점의 방문 여부를 체크하는 배열
        // true: 이미 최단거리가 확정된 정점, false: 아직 미확정인 정점
        boolean[] visited = new boolean[n];
        
        // 우선순위 큐: 거리가 짧은 정점부터 처리하기 위해 사용
        // 가장 짧은 거리를 가진 정점이 큐의 맨 앞에 위치
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 시작점 초기화
        distances[start] = 0;           // 시작점에서 시작점까지의 거리는 0
        pq.offer(new Node(start, 0));   // 시작점을 우선순위 큐에 추가
        
        System.out.println("시작점: " + start);
        System.out.println("초기 거리 배열: " + Arrays.toString(distances));
        System.out.println();
        
        int step = 1;  // 단계별 진행상황을 보여주기 위한 변수
        
        // 우선순위 큐가 빌 때까지 반복 (모든 정점을 처리할 때까지)
        while (!pq.isEmpty()) {
            // 현재 가장 거리가 짧은 정점을 큐에서 꺼냄
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentDistance = current.distance;
            
            System.out.println("--- 단계 " + step + " ---");
            System.out.println("현재 처리 중인 정점: " + currentVertex + " (거리: " + currentDistance + ")");
            
            // 이미 방문한 정점이면 건너뜀
            // (우선순위 큐 특성상 같은 정점이 여러 번 들어갈 수 있음)
            if (visited[currentVertex]) {
                System.out.println("이미 방문한 정점이므로 건너뜀");
                System.out.println();
                continue;
            }
            
            // 현재 정점을 방문 처리 (최단거리 확정)
            visited[currentVertex] = true;
            System.out.println("정점 " + currentVertex + " 방문 처리 완료");
            
            // 현재 정점과 인접한 모든 정점들을 확인
            for (Edge edge : graph.get(currentVertex)) {
                int neighbor = edge.destination;  // 인접한 정점
                int weight = edge.weight;         // 현재 정점에서 인접 정점까지의 가중치
                
                // 아직 방문하지 않은 인접 정점만 처리
                if (!visited[neighbor]) {
                    // 현재 정점을 거쳐서 인접 정점으로 가는 새로운 거리 계산
                    int newDistance = currentDistance + weight;
                    
                    System.out.println("  인접 정점 " + neighbor + " 확인:");
                    System.out.println("    현재 저장된 거리: " + 
                        (distances[neighbor] == Integer.MAX_VALUE ? "∞" : distances[neighbor]));
                    System.out.println("    새로 계산된 거리: " + currentDistance + " + " + weight + " = " + newDistance);
                    
                    // 새로 계산된 거리가 기존에 저장된 거리보다 짧으면 업데이트
                    if (newDistance < distances[neighbor]) {
                        distances[neighbor] = newDistance;  // 최단거리 업데이트
                        pq.offer(new Node(neighbor, newDistance));  // 우선순위 큐에 추가
                        System.out.println("    → 더 짧은 경로 발견! 거리 업데이트: " + newDistance);
                    } else {
                        System.out.println("    → 기존 경로가 더 짧음. 업데이트하지 않음");
                    }
                }
            }
            
            System.out.println("현재 거리 배열: " + Arrays.toString(distances));
            System.out.println();
            step++;
        }
        
        return distances;  // 시작점에서 모든 정점까지의 최단거리 배열 반환
    }
    

    
    // 그래프에 간선을 추가하는 헬퍼 메서드
    // 방향 그래프이므로 한 방향으로만 간선을 추가
    private void addEdge(List<List<Edge>> graph, int source, int destination, int weight) {
        graph.get(source).add(new Edge(destination, weight));
    }
    
    // 생성된 그래프의 구조를 출력하는 메서드
    private void printGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("정점 " + i + ": ");
            for (Edge edge : graph.get(i)) {
                System.out.print("→ " + edge.destination + "(가중치:" + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}