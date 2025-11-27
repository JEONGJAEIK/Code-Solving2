package org.problem.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1987 {
    static char[][] map;
    static boolean[] visitedAlpha = new boolean[26];
    static int y, x;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        map = new char[y][x];

        for (int i = 0; i < y; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                map[i][j] = input.charAt(j);
            }
        }

        visitedAlpha[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(result);
    }

    static void dfs(int cy, int cx, int count) {
        result = Math.max(count, result);
        for (int i = 0; i < 4; i++) {
            int ny = cy + dy[i];
            int nx = cx + dx[i];

            if (ny >= 0 && nx >= 0 && ny < y && nx < x) {
                int nextIndex = map[ny][nx] - 'A';
                if (!visitedAlpha[nextIndex]) {
                    visitedAlpha[nextIndex] = true;
                    dfs(ny, nx, count + 1);

                    // 백트래킹
                    visitedAlpha[nextIndex] = false;
                }
            }
        }
    }
}
