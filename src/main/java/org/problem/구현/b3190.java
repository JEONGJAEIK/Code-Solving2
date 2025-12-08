package org.problem.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 골드 4 뱀
public class b3190 {
    static int[][] map;
    static int[][] directionCommand;
    static int n;
    static int round;

    static class Snake {
        int direction;
        Deque<int[]> existed;

        public Snake(int direction, Deque<int[]> existed) {
            this.direction = direction; // 0123 북동남서
            this.existed = existed;
        }

        public boolean move() {
            int[] current = existed.peekFirst();
            int cy = current[0];
            int cx = current[1];
            round++;

            if (direction == 0) {
                cy--;
            } else if (direction == 1) {
                cx++;
            } else if (direction == 2) {
                cy++;
            } else {
                cx--;
            }

            if (!stopCheck(cy, cx)) {
                return false;
            }

            appleCheck(cy, cx);
            existed.offerFirst(new int[]{cy, cx});
            return true;
        }

        public void appleCheck(int ny, int nx) {
            if (map[ny][nx] == 1) {
                map[ny][nx] = 0;
            } else {
                existed.pollLast();
            }
        }

        public boolean stopCheck(int ny, int nx) {
            if (ny >= n || nx >= n || ny < 0 || nx < 0) {
                return false;
            }

            for (int[] ints : existed) {
                int y = ints[0];
                int x = ints[1];
                if (ny == y && nx == x) {
                    return false;
                }
            }
            return true;
        }

        public void setDirection(int command) {
            if (command == 0) {
                direction = (direction + 3) % 4;
            } else {
                direction = (direction + 1) % 4;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y - 1][x - 1] = 1;
        }

        int l = Integer.parseInt(br.readLine()); // 방향정보 개수
        directionCommand = new int[l][2];
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int round = Integer.parseInt(st.nextToken());
            String change = st.nextToken();
            if (change.equals("L")) {
                directionCommand[i] = new int[] {round, 0};
            } else {
                directionCommand[i] = new int[] {round, 1};
            }
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {0, 0});
        Snake snake = new Snake(1, deque);

        while (true) {
            if (!snake.move()) {
                System.out.println(round);
                break;
            } else {
                directionChange(snake);
            }
        }

    }

    private static void directionChange(Snake snake) {
        for (int[] ints : directionCommand) {
            int commandRound = ints[0];
            int command = ints[1];

            if (round == commandRound) {
                snake.setDirection(command);
            }
        }
    }
}
