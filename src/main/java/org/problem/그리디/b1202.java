package org.problem.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 골드 2 보석 도둑
public class b1202 {
    // 우선 순위 큐에 가장 비싼거 부터 나오도록 한다.
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    static int[][] arr;
    static int[] maxWeight;
    static long totalWeight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 보석 개수 최대 30만
        int k = Integer.parseInt(st.nextToken()); // 가방 개수 최대 30만

        arr = new int[n][2];
        maxWeight = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 보석 무게 최대 100만
            arr[i][1] = Integer.parseInt(st.nextToken()); // 보석 가격 최대 100만
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            maxWeight[i] = Integer.parseInt(st.nextToken()); // 각 가방에 담을 수 있는 최대 무게 최대 1억
        }

        // DP로 풀면 시간초과가 난다.
        // 그리디로 풀어야함 분할이 불가능한 배낭 문제지만 각 가방에 1개만 담을 수 있는 특성 때문에 그리디가 가능함
        // 제일 작은 가방에 자기가 담을 수 있는 모든 보석을 구하고 그 보석중에서 가장 비싼 보석을 담는다.

        // 가방 정렬
        Arrays.sort(maxWeight);

        // 보석 무게 정렬
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // 보석 배열 포인터
        int jewelryIndex = 0;

        for (int i = 0; i < k; i++) {
            int currentWeight = maxWeight[i]; // 현재 가방 무게

            // 넣을 수 있는 보석을 구한다.
            while (jewelryIndex < n && arr[jewelryIndex][0] <= currentWeight) {
                pq.add(arr[jewelryIndex][1]);
                jewelryIndex++;
            }

            // 큐가 비어있지 않으면 꺼낸다. 비어있다면 넘어감
            if (!pq.isEmpty()) {
                totalWeight += pq.poll();
            }
        }

        System.out.println(totalWeight);
    }
}
