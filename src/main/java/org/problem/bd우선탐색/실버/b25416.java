package org.problem.bd우선탐색.실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀로 구현해 본다.
public class b25416 {
    static int[][] map;
    static boolean[][] visited;
    static int startY;
    static int startX;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int min = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[5][5];
        visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());

        if (map[startY][startX] != 1) {
            visited[startY][startX] = true;
            recursion(startY, startX, 1);
            System.out.println(min);
        } else {
            System.out.println(0);
        }
    }

    public static int recursion(int cy, int cx, int count) {
        for (int i = 0; i < 4; i++) {
            int ny = cy + dy[i]; // 리커전 케이스 : 베이스 케이스에 수렴
            int nx = cx + dx[i];

            if (ny >= 0 && nx >= 0 && ny < 5 && nx < 5 && !visited[ny][nx] && map[ny][nx] != -1) {
                if (map[ny][nx] == 1) { // 베이스 케이스 : 탈출 경로
                    min = Math.min(min, count);
                } else {
                    visited[ny][nx] = true;
                    int result = recursion(ny, nx, count + 1);
                    if (result != -1) {
                        min = Math.min(min, result);
                    }
                }
            }
        }
        return -1;
    }
}
