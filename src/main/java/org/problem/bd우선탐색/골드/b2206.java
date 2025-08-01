package org.problem.bd우선탐색.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 문제
// N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
// 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
// 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
// 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
// 입력
// 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
// 출력
// 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
// 골드 3 벽 부수고 이동하기
public class b2206 {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        int[][] map = new int[col][row]; // 세로, 가로

        for (int i = 0; i < col; i++) {
            String line = br.readLine();
            for (int j = 0; j < row; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = bfs(map, col, row);
        System.out.println(result);
    }

    static int bfs(int[][] map, int col, int row) {
        // 해당 위치에 벽을 부술 수 있는 상태로 방문했는지 체크
        boolean[][][] visited = new boolean[col][row][2];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0, 0, 1}); // y, x, 벽 부순 여부, 현재 거리
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];
            int broken = current[2];
            int distance = current[3];

            // 목적지 도달하면 최단거리 반환
            // 벽을 부쉈든 안 부쉈든 첫 번째 도달이 최단거리이기 때문
            if (y == col - 1 && x == row - 1) {
                return distance;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (nx < 0 || ny < 0 || nx >= row || ny >= col) continue;

                if (map[ny][nx] == 0) {
                    // 빈 칸으로 이동
                    if (!visited[ny][nx][broken]) {
                        visited[ny][nx][broken] = true;
                        queue.offer(new int[]{ny, nx, broken, distance + 1});
                    }
                } else if (broken == 0) { // 다음 위치가 벽이고 아직 부수지 않은 경우
                    // 벽을 부수고 이동 (아직 벽을 부수지 않은 경우만)
                    if (!visited[ny][nx][1]) {
                        visited[ny][nx][1] = true;
                        queue.offer(new int[]{ny, nx, 1, distance + 1});
                    }
                }
            }
        }
        // 목적지에 도달할 수 없는 경우
        return -1;
    }
}