package org.problem.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버 2 부분수열의 합
public class b1182 {
    static int[] arr;
    static int n;
    static int s;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        recursion(0, 0);

        if (s == 0) {
            count--;
        }

        System.out.println(count);

    }

    // 값을 넣을 것이냐 안 넣을 것이냐
    static void recursion(int depth, int sum) {
        if (depth == n) {
            if (sum == s) {
                count++;
            }
            return;
        }

        recursion(depth + 1, sum + arr[depth]);
        recursion(depth + 1, sum);
    }
}
