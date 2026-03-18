package org.problem.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버1 스타트와 링크
public class b14889 {
    static int n;
    static int[][] arr;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n]; // 10
        visited = new boolean[n];

        for (int i = 0; i < n; i++) { // 0 ~ 9
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) { // 0 ~ 9
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(min);
    }

    static void combination(int start, int depth) {
        if (depth == n / 2) {
            int startTeam = 0;
            int linkTeam = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        startTeam += arr[i][j] + arr[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        linkTeam += arr[i][j] + arr[j][i];
                    }
                }
            }
            min = Math.min(min, Math.abs(startTeam - linkTeam));
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(i + 1, depth + 1);
            visited[i] = false;
        }
    }
}
