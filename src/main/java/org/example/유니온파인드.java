package org.example;

import java.io.*;
import java.util.*;

// 백준 11724 연결 요소의 개수 문제를 유니온 파인드로 푼 예시
// 유니온 파이드는 연결 요소의 개수, 같은 연결 요소에 속하는지, 최소 신장 트리등에 쓰임 find는 시간복잡도가 O(1)에 근접
public class 유니온파인드 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 수
        int m = Integer.parseInt(st.nextToken()); // 간선 수

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        Set<Integer> roots = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            roots.add(find(i)); // 대표 노드들만 모으기
        }

        System.out.println(roots.size()); // 연결 요소 개수
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // 경로 압축
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) parent[rootB] = rootA;
    }
}
