package org.problem.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b21736 {
    static char[][] maps;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int col = Integer.parseInt(st.nextToken()); // 열
        int row = Integer.parseInt(st.nextToken()); // 행

        maps = new char[col][row];
        visited = new boolean[col][row];
        int[] current = new int[2];

        for (int i = 0; i < col; i++) {
            String value = br.readLine();
            for (int j = 0; j < row; j++) {
                maps[i][j] = value.charAt(j);
                if (maps[i][j] == 'I') {
                    current[0] = i;
                    current[1] = j;
                }

            }
        }
        dfs(current[0], current[1]);
        System.out.println(result == 0 ? "TT" : result);
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;


        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && nx >= 0 && ny < maps.length && nx < maps[0].length) {
                if (!visited[ny][nx] && (maps[ny][nx] == 'O' || maps[ny][nx] == 'P')) {

                    if (maps[ny][nx] == 'P') {
                        result += 1;
                    }


                    dfs(ny, nx);
                }
            }
        }
    }
}
