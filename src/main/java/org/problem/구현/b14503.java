package org.problem.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14503 {
    static int[][] map;
    static int y;
    static int x;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        map = new int[y][x];

        st = new StringTokenizer(br.readLine());
        int cy = Integer.parseInt(st.nextToken());
        int cx = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(cy, cx, direction);
        System.out.println(count);
    }

    static void move(int cy, int cx, int direction) {
        while (true) {
            if (map[cy][cx] == 0) {
                map[cy][cx] = 2; // 0은 빈칸 1은 벽 2는 청소
                count++;
            }

            int directionCount = 0;
            boolean moved = false;
            while (directionCount < 4) {
                if (direction == 0) {
                    direction = 3;
                } else {
                    direction--;
                }

                directionCount++;
                if (direction == 0) {
                    if (cy - 1 >= 0 && map[cy - 1][cx] == 0) {
                        map[cy - 1][cx] = 2;
                        cy--;
                        count++;
                        moved = true;
                        break;
                    }
                }

                if (direction == 1) {
                    if (cx + 1 < x && map[cy][cx + 1] == 0) {
                        map[cy][cx + 1] = 2;
                        cx++;
                        count++;
                        moved = true;
                        break;
                    }
                }

                if (direction == 2) {
                    if (cy + 1 < y && map[cy + 1][cx] == 0) {
                        map[cy + 1][cx] = 2;
                        cy++;
                        count++;
                        moved = true;
                        break;
                    }
                }

                if (direction == 3) {
                    if (cx - 1 >= 0 && map[cy][cx - 1] == 0) {
                        map[cy][cx - 1] = 2;
                        cx--;
                        count++;
                        moved = true;
                        break;
                    }
                }
            }

            if (!moved) {
                if (direction == 0) {
                    if (cy + 1 < y && map[cy + 1][cx] != 1) {
                        cy++;
                    } else {
                        return;
                    }
                }

                if (direction == 1) {
                    if (cx - 1 >= 0 && map[cy][cx - 1] != 1) {
                        cx--;
                    } else {
                        return;
                    }
                }

                if (direction == 2) {
                    if (cy - 1 >= 0 && map[cy - 1][cx] != 1) {
                        cy--;
                    } else {
                        return;
                    }
                }

                if (direction == 3) {
                    if (cx + 1 < x && map[cy][cx + 1] != 1) {
                        cx++;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}


