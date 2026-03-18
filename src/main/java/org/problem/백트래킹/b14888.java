package org.problem.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버1 연산자 끼워넣기
public class b14888 {
    static int n;
    static int[] arr;
    static int[] ops;
    static int maxResult = Integer.MIN_VALUE;
    static int minResult = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        ops = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, arr[0]);
        System.out.println(maxResult);
        System.out.println(minResult);
    }

    static void dfs(int depth, int current) {
        if (depth == n) {
            maxResult = Math.max(maxResult, current);
            minResult = Math.min(minResult, current);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (ops[i] > 0) {
                ops[i]--;
                int next = calculate(current, arr[depth], i);
                dfs(depth + 1, next);
                ops[i]++;
            }
        }
    }

    static int calculate(int a, int b, int op) {
        if (op == 0) {
            return a + b;
        } else if (op == 1) {
            return a - b;
        } else if (op == 2) {
            return a * b;
        } else {
            if (a < 0) {
                int result = Math.abs(a) / b;
                return -result;
            } else {
                return a / b;
            }
        }
    }
}
