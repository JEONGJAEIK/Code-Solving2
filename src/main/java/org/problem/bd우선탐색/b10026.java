package org.problem.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b10026 {
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] blindVisited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        map = new char[size][size];
        visited = new boolean[size][size];
        blindVisited = new boolean[size][size];
        int count = 0;
        int blindCount = 0;

        for (int i = 0; i < size; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!blindVisited[i][j]) {
                    blindDfs(i, j);
                    blindCount++;
                }
            }
        }

        System.out.println(count + " " + blindCount);
    }


    static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && nx >= 0 && ny < size && nx < size) {
                if (!visited[ny][nx] && map[ny][nx] == map[y][x]) {
                    dfs(ny, nx);
                }
            }
        }
    }

    static void blindDfs(int y, int x) {
        blindVisited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && nx >= 0 && ny < size && nx < size) {
                if (!blindVisited[ny][nx] && (map[y][x] == 'R' || map[y][x] == 'G') && (map[ny][nx] == 'R' || map[ny][nx] == 'G')) {
                    blindDfs(ny, nx);
                } else if (!blindVisited[ny][nx] && map[ny][nx] == map[y][x]) {
                    blindDfs(ny, nx);
                }
            }
        }
    }
}


