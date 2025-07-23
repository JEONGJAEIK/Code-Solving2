package org.practice.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버 1 RGB거리
// 한 마디로 양 옆에 집이랑 색깔이 같으면 안된다는 것 양 끝 집은 옆 집이랑만 비교하고
// 색이 같으면 안된다는 것은 일종의 간선으로 표현할 수 있다. 입력에는 배열로 빨,초,파의 각 견적이 나와있는데
// 이것을 선택하는 것을 어떠한 배열에서 특정한 배열로만 이동 가능하다고 보면 코스트를 가진 노드로 표현할 수 있다.
// 최소 비용으로 목적지까지 가는 문제와 동일하며 우선순위 큐를 이용한 다익스트라 알고리즘을 활용하면 된다.
// 인줄 알았는데 DP란다..
public class b1149 {
    static int houseCount;
    static int[][] cost;          // cost[i][j] = i번째 집을 j색으로 칠할 때의 비용
    static int[][] dist;          // dist[i][j] = i번째 집을 j색으로 칠할 때까지의 최소 누적 비용
    static boolean[][] visited;   // visited[i][j] = i번째 집을 j색으로 칠했는지 여부

    static class Node implements Comparable<Node> {
        int house;
        int color;
        int cost;

        public Node(int house, int color, int cost) {
            this.house = house;
            this.color = color;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        houseCount = Integer.parseInt(br.readLine());

        cost = new int[houseCount][3];
        dist = new int[houseCount][3];
        visited = new boolean[houseCount][3];

        // 배열로 나타내면 다음열의 행이 같으면 안된다는 것이다.
        // 하지만 시작 노드가 정해지지 않았다 그래도 3가지의 경우만 있으니 3가지를 비교하면 될 듯함.
        for (int i = 0; i < houseCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = Integer.MAX_VALUE;  // 초기값 무한
            }
        }

        dijkstra();

        int result = Math.min(dist[houseCount - 1][0],
                Math.min(dist[houseCount - 1][1], dist[houseCount - 1][2]));

        System.out.println(result);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작 노드: 첫 번째 집의 빨/초/파
        for (int color = 0; color < 3; color++) {
            dist[0][color] = cost[0][color];
            pq.offer(new Node(0, color, dist[0][color]));
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int house = curr.house;
            int color = curr.color;

            if (visited[house][color]) continue;
            visited[house][color] = true;

            // 다음 집으로 이동
            if (house + 1 < houseCount) {
                for (int nextColor = 0; nextColor < 3; nextColor++) {
                    if (nextColor == color) continue;  // 인접한 집은 같은 색 X

                    int nextCost = dist[house][color] + cost[house + 1][nextColor];

                    if (nextCost < dist[house + 1][nextColor]) {
                        dist[house + 1][nextColor] = nextCost;
                        pq.offer(new Node(house + 1, nextColor, nextCost));
                    }
                }
            }
        }
    }
}
