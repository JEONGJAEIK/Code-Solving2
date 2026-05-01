package org.problem.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class b1406stack {
    static Deque<Character> left = new ArrayDeque<>();
    static Deque<Character> right = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            left.push(c);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String cmd = br.readLine();
            modify(cmd);
        }

        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty()) {
            right.push(left.pop());
        }

        while (!right.isEmpty()) {
            sb.append(right.pop());
        }
        System.out.println(sb);
    }

    static void modify(String cmd) {
        if (cmd.startsWith("L")) {
            if (!left.isEmpty()) {
                right.push(left.pop());
            }
        } else if (cmd.startsWith("D")) {
            if (!right.isEmpty()) {
                left.push(right.pop());
            }
        } else if (cmd.startsWith("B")) {
            if (!left.isEmpty()) {
                left.pop();
            }
        } else if (cmd.startsWith("P")) {
            left.push(cmd.charAt(2));
        }
    }
}
