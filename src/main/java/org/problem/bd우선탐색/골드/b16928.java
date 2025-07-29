package org.problem.bd우선탐색.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 골드 5 뱀과 사다리 게임

public class b16928 {
    static Map<Integer, Integer> ladder = new HashMap<>();
    static Map<Integer, Integer> snake = new HashMap<>();
    static int[] map = new int[101];
    static boolean[] visited = new boolean[101];
    static int[] dy = {1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ladderCount = Integer.parseInt(st.nextToken());
        int snakeCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < ladderCount; i++) {
            st = new StringTokenizer(br.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < snakeCount; i++) {
            st = new StringTokenizer(br.readLine());
            snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        visited[1] = true;
        map[1] = 0;
        bfs();
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (queue.peek() != 100) {
            int cy = queue.poll();
            for (int i = 0; i < 6; i++) {
                int ny = cy + dy[i];
                if (ny > 100 || visited[ny]) continue;

                // 사다리나 뱀 위치면 도착 위치로 갱신
                if (ladder.containsKey(ny)) {
                    ny = ladder.get(ny);
                } else if (snake.containsKey(ny)) {
                    ny = snake.get(ny);
                }

                if (!visited[ny]) {
                    visited[ny] = true;
                    map[ny] = map[cy] + 1;
                    queue.add(ny);

                    if (ny == 100) {
                        System.out.println(map[ny]);
                        return;
                    }
                }
            }
        }
    }
}
