package org.practice.순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// N과 M 9
// 순열 (배열에 중복 숫자는 포함하지 않는)
public class b15663 {
    static int[] arr, result;
    static boolean[] visited;
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
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs(0);
        System.out.print(sb);

    }

    static void dfs(int depth) {
        if (depth == m) {
            for (int num : result) sb.append(num).append(" ");
            sb.append("\n");
            return;
        }

        HashSet<Integer> used = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (used.contains(arr[i])) continue;

            used.add(arr[i]);
            visited[i] = true;
            result[depth] = arr[i];
            dfs(depth + 1);
            visited[i] = false;
        }
    }
}

