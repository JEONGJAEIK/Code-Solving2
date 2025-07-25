package org.practice.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 실버 1 케빈 베이컨의 6단계 법칙
// bfs로 풀었지만 플로이드-워셜도 가능하다
public class b1389 {
    static int[][] arr;
    static boolean[] visited;
    static int userCount;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        userCount = Integer.parseInt(st.nextToken());
        int lineCount = Integer.parseInt(st.nextToken());

        arr = new int[userCount][userCount];
        result = new int[userCount];
        for (int i = 0; i < lineCount; i++) {
            st = new StringTokenizer(br.readLine());
            int user = Integer.parseInt(st.nextToken()) - 1;
            int friend = Integer.parseInt(st.nextToken()) - 1;
            arr[user][friend] = 1;
            arr[friend][user] = 1;
        }

        for (int i = 0; i < userCount; i++) {
            visited = new boolean[userCount];
            bfs(i);
        }

        int min = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 0; i < userCount; i++) {
            if (result[i] < min) {
                min = result[i];
                answer = i;
            }
        }
        System.out.println(answer + 1);
    }

    static void bfs(int startUser) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startUser, 0});
        visited[startUser] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currentUser = curr[0];
            int depth = curr[1];

            for (int i = 0; i < userCount; i++) {
                if(arr[currentUser][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    result[startUser] += depth + 1;
                    queue.offer(new int[]{i, depth + 1});
                }
            }
        }
    }
}
