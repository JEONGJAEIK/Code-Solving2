package org.problem.최소신장트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b1647 {

    static int[][] graph;
    static int[] parent;
    static int result;
    static int[] costArr;

    // 2개의 집합으로 나눠야함
    // 컷(A, A-B), 컷(A, A-B)가 모든집을 존중해야한다.
    // 컷(A, A-B)가 모든 집을 존중할 때 이 컷을 cross하는 간선 중 가장 가중치가 작은 간선은 MST의 부분집합에 대해서 안전하다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int houseCount = Integer.parseInt(st.nextToken()); // 집 개수 10만까지
        int roadCount = Integer.parseInt(st.nextToken()); // 길 개수 100만 까지
        graph = new int[roadCount][3];
        parent = new int[houseCount + 1];
        costArr = new int[roadCount];

        for (int i = 0; i < roadCount; i++) {
            st = new StringTokenizer(br.readLine());
            int houseA = Integer.parseInt(st.nextToken()); // 집 A
            int houseB = Integer.parseInt(st.nextToken()); // 집 B
            int cost = Integer.parseInt(st.nextToken()); // A와 B의 사이의 길 유지비
            graph[i][0] = houseA;
            graph[i][1] = houseB;
            graph[i][2] = cost;
        }

        // 오름차순 정렬
        Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        // 부모 배열 초기화
        for (int i = 1; i < houseCount + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < roadCount; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) {
                union(graph[i][0], graph[i][1]);
                costArr[i] = graph[i][2];
                result += graph[i][2];
            }
        }

        // 가장 비싼길 하나를 제거
        Arrays.sort(costArr);
        int expensiveRoad = costArr[costArr.length - 1];
        result = result - expensiveRoad;
        System.out.println(result);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}
