package org.problem.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 골드 5 탑
public class b2493 {
    static int[] towers;
    static Deque<Integer> stack = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        towers = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        // 6 9 5 7 4
        // 4 삽입
        // 7이 4보다 크니 4꺼내고 7삽입 7입력 7
        // 5가 7보다 작으니 5삽입 5 7
        // 9가 5보다 크니 5꺼냄 7
        // 9가 7보다 크니 7꺼냄 9입력 5
        // 6이 9보다 작으니 6입력
        // 6은 마지막이라 남은 만큼 전부 0 입력


        // 6 스택빔 정답 0 추가 인덱스 1 스택추가
        // 9 9가 더 크므로 6팝 스택이 비었으므로 정답 0 추가 인덱스 2추가
        // 5 5가 6보다 작고 스택이 안비었음 정답에 2추가 스택에 인덱스 3추가
        // 7이 5보다 크고 스택안비었음 6이랑 5이랑 꺼내기 스택비었으니 정답 0추가

        for (int i = 1; i < n + 1; i++) {
            while (!stack.isEmpty() && towers[stack.peek()] < towers[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append(0).append(" ");
            } else {
                sb.append(stack.peek()).append(" ");
            }

            stack.push(i);
        }

        System.out.println(sb);
    }
}
