package org.practice;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class b4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while (!(line = br.readLine()).equals(".")) {
            Deque<Character> stack = new ArrayDeque<>();
            boolean balanced = true;

            for (char c : line.toCharArray()) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        balanced = false;
                        break;
                    }
                } else if (c == ']') {
                    {
                        if (stack.isEmpty() || stack.pop() != '[') {
                            balanced = false;
                            break;
                        }
                    }
                }
            }

            if (!stack.isEmpty()) {
                balanced = false;
            }

            System.out.println(balanced ? "yes" : "no");
        }
    }
}