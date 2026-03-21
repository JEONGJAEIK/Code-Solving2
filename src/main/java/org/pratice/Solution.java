package org.pratice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

class Solution {


    public static void main(String[] args) {
        int n = 3;
        int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int solution = solution(n, computers);
        System.out.println(solution);
    }
    static int[] parent;
    public static int solution(int n, int[][] computers) {
        parent = new int[computers.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers.length; j++) {
                if (computers[i][j] != 0 && computers[i][j] == computers[j][i]) {
                    union(i, j);
                }
            }
        }

        int count = 0;
        int m = 0;
        for (int i : parent) {
            if (m != i) {
                count++;
                m = i;
            }
        }




        return count;
    }

    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}