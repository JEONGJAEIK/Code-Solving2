package org.practice.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b7576 {

    static int[][] maps;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        maps = new int[height][width];
        visited = new boolean[height][width];
        int tomatoCheck = 0;

        List<int[]> start = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                tomatoCheck++;
                if (maps[i][j] == 1) {
                    start.add(new int[]{i, j});
                }
            }
        }

        if (tomatoCheck == 0) {
            System.out.println(0);
            return;
        }

        bfs(start);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maps[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        int max = 0;
        for (int[] map : maps) {
            Arrays.sort(map);
            if (max < map[maps[0].length - 1]) {
                max = map[maps[0].length - 1];
            }
        }
        System.out.println(max - 1);
    }

    static void bfs(List<int[]> start) {
        Queue<int[]> queue = new LinkedList<>();

        for (int[] ints : start) {
            queue.offer(ints);
            visited[ints[0]][ints[1]] = true;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && nx >= 0 && ny < maps.length && nx < maps[0].length) {
                    if (!visited[ny][nx] && maps[ny][nx] != -1) {
                        maps[ny][nx] = maps[y][x] + 1;
                        queue.offer(new int[]{ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }
        }
    }
}
