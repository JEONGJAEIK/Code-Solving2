package org.problem.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 실버 1 나이트의 이동
public class b7562 {
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx = {-1, -2, -2, -1 ,1, 2, 2, 1};
    static int[][] map;
    static int length;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            length = Integer.parseInt(br.readLine());
            map = new int[length][length];
            visited = new boolean[length][length];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int targetY = Integer.parseInt(st.nextToken());
            int targetX = Integer.parseInt(st.nextToken());
            bfs(y, x);
            int result = map[targetY][targetX];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cy = poll[0];
            int cx = poll[1];

            for (int i = 0; i < 8; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny >= 0 && nx >= 0 && ny < length && nx < length) {
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        map[ny][nx] = map[cy][cx] + 1;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}