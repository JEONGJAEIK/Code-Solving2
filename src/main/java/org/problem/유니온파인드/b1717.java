package org.problem.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1717 {
    static int n;
    static int m;
    static int[] parent;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int ops = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (ops == 0) {
                union(x, y);
            } else {
                int i1 = find(x);
                int i2 = find(y);
                if (i1 == i2) {
                    if (i == m - 1) {
                        sb.append("YES");
                    } else {
                        sb.append("YES\n");
                    }
                } else {
                    if (i == m - 1) {
                        sb.append("NO");
                    } else {
                        sb.append("NO\n");
                    }
                }
            }
        }

        System.out.println(sb);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        parent[x] = find(parent[x]);
        return parent[x];
    }
}
