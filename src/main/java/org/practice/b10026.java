package org.practice;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class b10026 {
    static int length;
    static int[][] field;
    static boolean[][] visited, colorBlindVisited;
    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        length = Integer.parseInt(br.readLine());
        field = new int[length][length];
        visited = new boolean[length][length];
        colorBlindVisited = new boolean[length][length];

        for (int y = 0; y < length; y++) {
            String line = br.readLine();
            for (int x = 0; x < length; x++) {
                field[y][x] = convert(line.charAt(x));
            }
        }

        int normalCount = 0;
        int colorBlindCount = 0;

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                if (!visited[y][x]) {
                    bfs(y, x, field[y][x], visited, false);
                    normalCount++;
                }
            }
        }

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                if (!colorBlindVisited[y][x]) {
                    bfs(y, x, field[y][x], colorBlindVisited, true);
                    colorBlindCount++;
                }
            }
        }

        System.out.println(normalCount + " " + colorBlindCount);
    }

    static int convert(char str) {
        switch (str) {
            case 'R' : return 0;
            case 'B' : return 1;
            case 'G' : return 2;
            default: throw new IllegalArgumentException("Invalid character: " + str);

        }
    }

    static void bfs(int startY, int startX, int target, boolean[][] visited, boolean isColorBlind) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX});
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < length && nx >= 0 && nx < length) {
                    if (!visited[ny][nx]) {
                        if (isColorBlind) {
                            if ((target == 0 || target == 2) && (field[ny][nx] == 0 || field[ny][nx] == 2)) {
                                visited[ny][nx] = true;
                                queue.add(new int[]{ny, nx});
                            } else if (target == 1 && field[ny][nx] == 1) {
                                visited[ny][nx] = true;
                                queue.add(new int[]{ny, nx});
                            }
                        } else {
                            if (field[ny][nx] == target) {
                                visited[ny][nx] = true;
                                queue.add(new int[]{ny, nx});
                            }
                        }
                    }
                }
            }
        }
    }
}
