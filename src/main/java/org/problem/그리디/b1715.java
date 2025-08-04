package org.problem.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 골드 4 카드 정렬하기
public class b1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cardTotal = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < cardTotal; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        // 최소 비교 횟수를 찾는 문제다. 그냥 수학 문제라고 생각해도 된다. A B C가 있을 때 A + B + (A + B) + C 인지 B + C + (B + C) + A 뭐가 더 숫자가 적은지 판별하는 것
        // 공식은 2A + 2B + C로 생각 해도 될듯 만약 A B C D 가 있으면 A + B + (A + B) + C + (A + B + C) + D = 3A + 3B + 2C + D 또는
        // A + B + C + D + (A + B) + (C + D) = 2A + 2B + 2C + 2D도 된다.
        // 경우의 수가 생각보다 다양하다 그냥 직관적으로 생각해보자 작은 것들을 먼저 더하는게 좋긴하다.
        // 왜냐하면 더한것의 합을 다른 것과 더하는 것이기 때문에 이 문제에서는 더한것의 합이 2배가 되기 때문에 작은것들부터 더하는것이 좋다.

        int result = 0;

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int c = a + b;
            pq.offer(c);
            result += c;
        }

        System.out.println(result);
    }
}
