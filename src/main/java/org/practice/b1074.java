package org.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1074 {
    static int count = 0;
    static int targetRow, targetCol ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        targetRow  = Integer.parseInt(st.nextToken());
        targetCol  = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, n);
        divide(0,0, size);

    }

    static void divide(int row, int col, int size) {
        if (size == 1) {
            System.out.println(count);
            return;
        }

        int half = size / 2;

        if (targetRow < row + half && targetCol < col + half) {
            divide(row, col, half);
        } else if (targetRow < row + half && targetCol >= col + half) {
            count += half * half;
            divide(row, col + half, half);
        } else if (targetRow >= row + half && targetCol < col + half) {
            count += half * half * 2;
            divide(row + half, col, half);
        } else {
            count += half * half * 3;
            divide(row + half, col + half, half);
        }
    }
}
