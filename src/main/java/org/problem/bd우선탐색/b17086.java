package org.problem.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b17086 {
    static int[][] arr;
    static int N;
    static int M;
    static int[] dy = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static List<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        Collections.sort(answers);
        Integer i = answers.get(answers.size() - 1);
        System.out.println(i);
    }

    static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        boolean[][] visited = new boolean[N][M];
        int[][] dist = new int[N][M];
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cy = poll[0];
            int cx = poll[1];

            for (int i = 0; i < 8; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    dist[ny][nx] = dist[cy][cx] + 1;

                    if (arr[ny][nx] == 1) {
                        answers.add(dist[ny][nx]);
                        return;
                    }
                    queue.add(new int[]{ny, nx});
                }
            }
        }
    }
}

