package org.practice.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b9663 {
    // 열 중복
    static boolean[] colUsed;
    // 오른 대각선
    static boolean[] diag1Used;
    // 왼 대각선
    static boolean[] diag2Used;
    // 경우의 수
    static int count;
    // 체스판 길이
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        size = n;

        colUsed = new boolean[n];
        diag1Used = new boolean[2 * n];
        diag2Used = new boolean[2 * n];

        dfs(0);
        System.out.println(count);
    }

    static void dfs(int row) {
        if (row == size) {
            count += 1;
            return;
        }

        // n은 행으로 간주한다.
        for (int col = 0; col < size; col++) {
            if (!colUsed[col] && !diag1Used[row - col + size] && !diag2Used[row + col]) {
                colUsed[col] = true;
                diag1Used[row - col + size] = true;
                diag2Used[row + col] = true;
                dfs(row + 1);

                // 백 트래킹
                colUsed[col] = false;
                diag1Used[row - col + size] = false;
                diag2Used[row + col] = false;
            }
        }
    }
}
