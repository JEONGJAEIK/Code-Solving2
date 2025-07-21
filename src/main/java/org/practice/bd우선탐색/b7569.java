package org.practice.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 5 3차원 토마토
// 3차원 BFS 문제
public class b7569 {

    static int[][][] maps;
    static boolean[][][] visited;
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, 0, 1, -1};
    static int[] dz = {-1, 1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        maps = new int[height][column][row];
        visited = new boolean[height][column][row];
        int tomatoCheck = 0;

        List<int[]> start = new ArrayList<>();

        for (int h = 0; h < height; h++) {
            for (int r = 0; r < column; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < row; c++) {
                    maps[h][r][c] = Integer.parseInt(st.nextToken());
                    tomatoCheck++;
                    if (maps[h][r][c] == 1) {
                        start.add(new int[]{h, r, c});
                    }
                }
            }
        }

        if (tomatoCheck == 0) {
            System.out.println(0);
            return;
        }

        bfs(start);

        for (int h = 0; h < height; h++) {
            for (int r = 0; r < column; r++) {
                for (int c = 0; c < row; c++) {
                    if (maps[h][r][c] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int h = 0; h < height; h++) {
            for (int r = 0; r < column; r++) {
                for (int c = 0; c < row; c++) {
                    if (maps[h][r][c] > max) {
                        max = maps[h][r][c];
                    }
                }
            }
        }
        System.out.println(max - 1);
    }

    static void bfs(List<int[]> start) {
        Queue<int[]> queue = new LinkedList<>();

        for (int[] ints : start) {
            queue.offer(ints);
            visited[ints[0]][ints[1]][ints[2]] = true;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int z = current[0];
            int y = current[1];
            int x = current[2];

            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int ny = y + dy[i];
                int nx = x + dx[i];


                if (nz >= 0 && nz < maps.length && ny >= 0 && ny < maps[0].length && nx >= 0 && nx < maps[0][0].length) {
                    if (!visited[nz][ny][nx] && maps[nz][ny][nx] == 0) {
                        maps[nz][ny][nx] = maps[z][y][x] + 1;
                        visited[nz][ny][nx] = true;
                        queue.offer(new int[]{nz, ny, nx});
                    }
                }
            }
        }
    }
}
