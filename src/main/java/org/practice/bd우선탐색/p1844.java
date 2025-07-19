package org.practice.bd우선탐색;

import java.util.LinkedList;
import java.util.Queue;

public class p1844 {
    static boolean[][] visited;
    // 위, 오른, 아래, 왼쪽
    static int dy[] = {1, 0, -1, 0};
    static int dx[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        int[][] maps = {
                {1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}
        };
        System.out.println(solution(maps));
    }

    static public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];
        visited[0][0] = true;
        bfs(maps, 0, 0);
        int answer = maps[maps.length - 1][maps[0].length - 1];
        if (answer == 0 || answer == 1) {
            return -1;
        }
        return answer;
    }

    static void bfs(int[][] maps, int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny >= 0 && nx >= 0 && ny < maps.length && nx < maps[0].length) {
                    if (maps[ny][nx] == 1 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        maps[ny][nx] = maps[cy][cx] + 1;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}

