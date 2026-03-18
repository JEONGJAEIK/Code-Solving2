package org.problem.bd우선탐색.실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 실버1 영역 구하기
public class b2583 {
    static int m;
    static int n;
    static int k;
    static boolean[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static boolean[][] visited;
    static List<Integer> widthCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new boolean[m][n];
        visited = new boolean[m][n];
        widthCount = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = y1; j < y2; j++) {
                for (int l = x1; l < x2; l++) {
                    map[j][l] = true;
                }
            }
        }

        int areaCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && !map[i][j]) {
                    widthCount.add(dfs(i, j));
                    areaCount++;
                }
            }
        }

        System.out.println(areaCount);
        Collections.sort(widthCount);
        for (Integer i : widthCount) {
            System.out.print(i + " ");
        }

    }


    static int dfs(int y, int x) {
        visited[y][x] = true;
        int count = 1;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && nx >= 0 && ny < m && nx < n) {
                if (!visited[ny][nx] && !map[ny][nx]) {
                    count += dfs(ny, nx);
                    visited[ny][nx] = true;
                }
            }
        }

        return count;
    }
}
