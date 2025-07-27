package org.example;

import java.util.*;

// 백준 11724 연결 요소의 개수 문제를 유니온 파인드로 푼 예시
// 유니온 파이드는 연결 요소의 개수, 같은 연결 요소에 속하는지, 최소 신장 트리등에 쓰임 find는 시간복잡도가 O(1)에 근접
public class 유니온파인드 {
    // 부모 노드를 저장할 배열
    static int[] parent;

    public static void main(String[] args) {
        // 예시 그래프: 노드 개수 n = 6, 간선 m = 5
        // 연결된 간선들
        int n = 6;
        int[][] edges = {
                {1, 2},
                {2, 5},
                {5, 1},
                {3, 4},
                {4, 6}
        };

        // 1. 부모 배열 초기화 (자기 자신을 부모로 설정)
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 2. 간선 정보로 유니온 수행
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            union(a, b);
        }

        // 3. 대표 노드를 Set에 담아 중복 제거 → 연결 요소 개수
        Set<Integer> roots = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            roots.add(find(i));
        }

        // 4. 결과 출력
        System.out.println("연결 요소의 개수: " + roots.size()); // 예상 출력: 2
    }

    // find: x의 대표(최상위) 부모를 찾는다 (경로 압축 포함)
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    // union: a와 b가 속한 집합을 합친다
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA; // 한쪽을 다른 쪽에 붙이기
        }
    }
}