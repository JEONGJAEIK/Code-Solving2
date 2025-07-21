package org.practice.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 실버 1 숨바꼭질
// 수빈이의 현재 위치에서 갈 수 있는 경우의 수는 3가지 그것을 아래의 노드라고 생각한다.
// 현재 위치가 5면 4, 6, 10 의 아래 트리가 생기는 것이다. 거기서 계속 뻗어 나간다.
// 목표 위치에 도착했을때의 거리가 정답이고 BFS를 활용한다.
// 보통의 BFS와 다르게 이 경우는 큐에서 나오면서 동적으로 경로를 만든다.
// DP로 풀기어렵다. DP는 순차적인 관계여야 효율적 해당 문제는 비순차적 연결
// DP <- 최소 연산 횟수, 최대 점수, BFS <- 동등한 비용 최단 거리

public class b1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        int[] dist = new int[100001];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        visited[n] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == k) {
                System.out.println(dist[current]);
                return;
            }

            int[] nextPositions = {current - 1, current + 1, current * 2};

            for (int next : nextPositions) {
                if (next >= 0 && next < 100001 && !visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    dist[next] = dist[current] + 1;
                }
            }
        }
    }
}

