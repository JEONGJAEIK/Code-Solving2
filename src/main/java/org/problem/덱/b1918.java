package org.problem.덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class b1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringBuilder sb = new StringBuilder();

        Map<Character, Integer> priority = Map.of(
                '+', 1,
                '-', 1,
                '*', 2,
                '/', 2
        );

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : line.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop(); // '(' 제거
            } else { // 연산자
                while (!stack.isEmpty() && stack.peek() != '('
                        && priority.get(stack.peek()) >= priority.get(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // 남은 연산자 출력
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
