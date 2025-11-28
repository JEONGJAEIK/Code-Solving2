package org.problem.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class b9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String boom = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < origin.length(); i++) {
            stack.push(origin.charAt(i));

            if (stack.size() >= boom.length()) {
                boolean flag = true;
                for (int j = 0; j < boom.length(); j++) {
                    if (stack.get(stack.size() - boom.length() + j) != boom.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < boom.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.empty()) {
            sb.append("FRULA");
        } else {
            for (Character c : stack) {
                sb.append(c);
            }
        }

        System.out.println(sb);
    }
}
