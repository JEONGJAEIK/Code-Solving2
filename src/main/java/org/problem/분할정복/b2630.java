package org.problem.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2630 {
    static int[][] arr;
    static int zeroResult = 0;
    static int oneResult = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        arr = new int[length][length];

        for (int i = 0; i < length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(0, 0, length);
        System.out.println(zeroResult);
        System.out.println(oneResult);
    }

    static void recursion(int row, int col, int size) {
        if (isSameColor(row, col, size)) {
            if (arr[row][col] == 0) zeroResult++;
            else oneResult++;
            return;
        }

            int newSize = size / 2;
            recursion(row, col, newSize);                     // 1사분면
            recursion(row, col + newSize, newSize);           // 2사분면
            recursion(row + newSize, col, newSize);           // 3사분면
            recursion(row + newSize, col + newSize, newSize); // 4사

    }

    static boolean isSameColor(int row, int col, int size) {
        int color = arr[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] != color) return false;
            }
        }
        return true;
    }
}
