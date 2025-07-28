package org.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b2448 {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new char[n][2 * n - 1];

        for (char[] row : map) {
            Arrays.fill(row, ' ');
        }
        recursion(n, 0, n - 1);

        for (char[] row : map) {
            System.out.println(row);
        }
    }

    static void recursion(int n, int x, int y) {
        if (n == 3) {
            map[x][y] = '*';
            map[x + 1][y - 1] = '*';
            map[x + 1][y + 1] = '*';
            for (int i = -2; i <= 2; i++) {
                map[x + 2][y + i] = '*';
            }
            return;
        }

        int half = n / 2;
        recursion(half, x, y);
        recursion(half, x + half, y - half);
        recursion(half, x + half, y + half);
    }
}
