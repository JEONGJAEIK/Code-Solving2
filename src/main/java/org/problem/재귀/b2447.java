package org.problem.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b2447 {
    static char[][] star;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        star = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                star[i][j] = '*';
            }
        }
        dfs(0, 0, n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(star[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    static void dfs(int startY, int startX, int size) {
        if (size == 1) {
            return;
        }

        int newSize = size / 3;

        for (int i = startY + newSize; i < startY + 2 * newSize; i++) {
            for (int j = startX + newSize; j < startX + 2 * newSize; j++) {
                star[i][j] = ' ';
            }
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (y == 1 && x == 1) {
                    continue;
                }
                dfs(startY + y * newSize, startX + x * newSize, newSize);
            }

        }

    }
}
