package org.problem.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 2 공항
public class b10775 {
    static int[] parent; // parent[i]는 i 게이트 집합에서 도킹 가능한 가장 큰 게이트 번호를 저장
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine()); // 게이트의 수 최대 10만
        int p = Integer.parseInt(br.readLine()); // 비행기의 수 최대 10만
        parent = new int[g + 1];
        // 초기화: 모든 게이트 i에 대해, 도킹 가능한 가장 큰 번호는 자기 자신 i
        for (int i = 1; i <= g; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < p; i++) {
            int maxGate = Integer.parseInt(br.readLine()); // 비행기의 한계 게이트
            // maxGate가 속한 집합의 루트를 찾는다.
            // 이 루트가 실제로 도킹할 수 있는 가장 큰 번호의 게이트가 된다.
            int dockingGate = find(maxGate);
            if (dockingGate > 0) {
                result++;
                // 도킹 완료! 이제 이 게이트 집합 (dockingGate)은
                // 다음 번호인 dockingGate - 1에 연결된다. (Union)
                // 즉, 다음에 이 집합으로 들어오는 비행기는 dockingGate - 1을 찾게 된다.
                union(dockingGate, dockingGate - 1);
            } else {
                // dockingGate가 0이면 더 이상 도킹할 수 없다.
                // 문제의 조건에 따라 이후 도착하는 비행기는 모두 도킹할 수 없다.
                // 이미 find(maxGate)가 O(1)에 가까우므로, break를 사용하면 더 빠르다.
                break;
            }
        }
        System.out.println(result);
    }

    // Union 연산: 합치기
    // 이 문제에서는 집합의 루트 x를 x-1 집합에 합치는 방식으로 사용
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // 이 문제의 특성상 x 게이트를 사용했으면, 다음 빈 게이트는 x-1이 되므로
        // rootX를 rootY (즉, rootX - 1)에 연결한다.
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

    // Find 연산: 압축 경로 기법 (Path Compression) 적용
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        // 경로 압축: 루트를 찾으면 바로 연결
        return parent[x] = find(parent[x]);
    }
}
