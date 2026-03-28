package org.problem.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실버 1 쿼드트리
public class b1992 {
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        recursion(0, 0, n);
        System.out.println(sb);
    }


    static void recursion(int y, int x, int length) {
        int check = check(y, x, length);
        if (check == 0) {
            sb.append("0");
        } else if (check == 1) {
            sb.append("1");
        } else {
            int mid = length / 2;
            sb.append("(");
            recursion(y, x, mid);
            recursion(y, x + mid, mid);
            recursion(y + mid, x, mid);
            recursion(y + mid, x + mid, mid);
            sb.append(")");
        }
    }

    static int check(int y, int x, int length) {
        int baseline = map[y][x];
        for (int i = y; i < y + length; i++) {
            for (int j = x; j < x + length; j++) {
                if (map[i][j] != baseline) {
                    return 2;
                }
            }
        }

        if (baseline == 1) {
            return 1;
        }

        return 0;
    }
}
