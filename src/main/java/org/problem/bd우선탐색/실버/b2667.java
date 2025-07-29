package org.problem.bd우선탐색.실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class b2667 {
    static boolean[][] visited;
    static int[][] arr;
    static int[] dy = {1, -1,0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        visited = new boolean[n][n];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n ; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    count = 0;
                    dfs(i, j);
                    result.add(count);
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;
        count++;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < arr.length && nx >= 0 && nx < arr.length) {
                if (!visited[ny][nx] && arr[ny][nx] == 1) {
                    dfs(ny, nx);
                }
            }
        }
    }
}
