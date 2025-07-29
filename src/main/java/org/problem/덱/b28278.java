package org.problem.덱;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class b28278 {
    static Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int number = Integer.parseInt(st.nextToken());
                input(number);
            } else {
                method(command);
            }
        }
    }

    static void method(int command) {
        switch (command) {
            case 2:
                if (stack.isEmpty()) {
                    System.out.println(-1);
                    break;
                } else {
                    int a = stack.pop();
                    System.out.println(a);
                    break;
                }

            case 3:
                System.out.println(stack.size());
                break;

            case 4:
                if (stack.isEmpty()) {
                    System.out.println(1);
                    break;
                } else {
                    System.out.println(0);
                    break;
                }

            case 5:
                if (stack.isEmpty()) {
                    System.out.println(-1);
                    break;
                } else {
                    int a = stack.peek();
                    System.out.println(a);
                    break;
                }
        }
    }

    static void input(int number) {
        stack.push(number);
    }
}

