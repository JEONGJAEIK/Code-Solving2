package org.problem.순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합
public class b15650 {
    static int[] result;
    static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        result = new int[b];

        dfs(1, 0);
    }


    static void dfs(int start, int depth) {
        if (depth == b) {
            for (int i = 0; i < b; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= a; i++) {
            result[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }
}
