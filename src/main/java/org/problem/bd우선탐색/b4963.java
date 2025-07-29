package org.problem.bd우선탐색;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 실버 2 섬의 개수
public class b4963 {
    static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int width, height;
    static boolean[][] field, visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            if (width == 0 & height == 0) {
                break;
            }

            field = new boolean[height][width];
            visited = new boolean[height][width];

            for (int y = 0; y < height; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < width; x++) {
                    field[y][x] = Integer.parseInt(st.nextToken()) == 1;
                }
            }

            int islandCount = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (field[y][x] && !visited[y][x]) {
                        bfs(y, x);
                        islandCount++;
                    }
                }
            }
            bw.write(islandCount + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX});
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < height && nx >= 0 && nx < width) {
                    if (field[ny][nx] && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < height && nx >= 0 && nx < width) {
                if (field[ny][nx] && !visited[ny][nx]) {
                    dfs(ny,nx);
                }
            }
        }
    }
}
