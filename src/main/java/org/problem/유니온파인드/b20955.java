package org.problem.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 골드 4 민서의 응급 수술
public class b20955 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeTotal = Integer.parseInt(st.nextToken());
        int unionTotal = Integer.parseInt(st.nextToken());
        parent = new int[nodeTotal + 1];

        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        int removeEdges = 0;

        for (int i = 0; i < unionTotal; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (find(x) == find(y)) {
                removeEdges++;
            } else {
                union(x, y);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < nodeTotal + 1; i++) {
            set.add(find(i));
        }

        int connectEdges = set.size() - 1;

        System.out.println(removeEdges + connectEdges);
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

}
