package org.practice.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b7576 {
    static int[][] map;
    static boolean[][] visited;
    static int[][] distance;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        map = new int[height][width];
        visited = new boolean[height][width];
        distance = new int[height][width];
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                map[i][j] = tomato;
            }
        }

        int tomatoCount = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 1) {
                    tomatoCount++;
                }
            }
        }

        if (tomatoCount == height * width) {
            System.out.println(0);
            return;
        } else {
            tomatoCount = 0;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        bfs(queue);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (distance[i][j] > max) {
                    max = distance[i][j];
                }
            }
        }
        System.out.println(max);
    }

    static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny >= 0 && nx >= 0 && ny < map.length && nx < map[0].length) {
                    if (!visited[ny][nx] && map[ny][nx] == 0) {
                        visited[ny][nx] = true;
                        distance[ny][nx] = distance[cy][cx] + 1;
                        map[ny][nx] = 1;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}
