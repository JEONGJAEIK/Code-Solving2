package org.problem.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class b11729 {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = (int) Math.pow(2, n) - 1;
        sb.append(result).append('\n');
        hanoi(n, 1, 2, 3);
        System.out.println(sb);


    }

    public static void hanoi(int n, int start, int aux, int end) {
        if (n == 1) {
            sb.append(start).append(" ").append(end).append('\n');
            return;
        }

        hanoi(n - 1, start, end, aux);
        sb.append(start).append(" ").append(end).append('\n');
        hanoi(n - 1, aux, start, end);
    }
}
