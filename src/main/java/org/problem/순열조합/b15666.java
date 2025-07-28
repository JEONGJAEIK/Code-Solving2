package org.problem.순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// 중복 조합 (배열에 중복 숫자는 포함하지 않는)
public class b15666 {
    static int[] arr, result;
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[n];
        result = new int[m];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs(1, 0);
        System.out.print(sb);

    }

    static void dfs(int start, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }
        HashSet<Integer> used = new HashSet<>();
        for (int i = start - 1; i < n; i++) {
            if (used.contains(arr[i])) continue;

            used.add(arr[i]);
            result[depth] = arr[i];
            dfs(i + 1, depth + 1);
        }
    }
}
