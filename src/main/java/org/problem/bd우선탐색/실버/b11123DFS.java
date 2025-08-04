package org.problem.bd우선탐색.실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버 2 양 한마리... 양 두마리...
public class b11123DFS {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {-1, 1, 0, 0};
    static boolean[][] visited;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            map = new char[col][row];
            visited = new boolean[col][row];

            for (int j = 0; j < col; j++) {
                String line = br.readLine();
                for (int k = 0; k < row; k++) {
                    map[j][k] = line.charAt(k);
                }
            }
            int count = 0;
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < row; k++) {
                    if (!visited[j][k] && map[j][k] == '#') {
                        visited[j][k] = true;
                        dfs(j, k);
                        count++;
                    }
                }
            }
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < row; k++) {
                    visited[j][k] = false;
                    map[j][k] = ' ';
                }
            }
            System.out.println(count);
        }
    }

    static void dfs(int col, int arr) {

        for (int i = 0; i < 4; i++) {
            int ny = col + dy[i];
            int nx = arr + dx[i];

            if (ny >= 0 && nx >= 0 && ny < map.length && nx < map[0].length) {
                if (!visited[ny][nx] && map[ny][nx] == '#') {
                    visited[ny][nx] = true;
                    dfs(ny, nx);
                }
            }
        }
    }
}
