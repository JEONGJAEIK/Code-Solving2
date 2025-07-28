package org.problem;

import java.io.*;
import java.util.*;

public class b12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<Integer> queue = new LinkedList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        int a = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        int target = 1;

        while (!queue.isEmpty()) {
            int b = queue.poll();

            if (b == target) {
                target++;
            } else {
                stack.push(b);
            }

            while (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                target++;
            }
        }

        if (stack.isEmpty()) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
