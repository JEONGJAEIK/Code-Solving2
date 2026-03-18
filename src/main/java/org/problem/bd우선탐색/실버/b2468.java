package org.problem.bd우선탐색.실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버1
public class b2468 {
    static int n;
    static int[][] map;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static List<Integer> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        result = new ArrayList<>();


        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= 100; i++) {
            int count = 0;
            boolean[][] visited = new boolean[n][n];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (!visited[y][x] && map[y][x] > i) {
                        bfs(y, x, i, visited);
                        count++;
                    }
                }
            }
            result.add(count);
        }

        int realResult = 0;
        for (Integer i : result) {
            if (i > realResult) {
                realResult = i;
            }
        }

        System.out.println(realResult);
    }

    static void bfs(int y, int x, int rain, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny >= 0 && nx >= 0 && ny < n && nx < n) {
                    if (!visited[ny][nx] && map[ny][nx] > rain) {
                        visited[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}
