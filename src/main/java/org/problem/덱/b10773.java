package org.problem.덱;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class b10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> deque = new ArrayDeque<>();
        int answer = 0;

        int count = Integer.parseInt(br.readLine());

        while (count > 0) {
            int number = Integer.parseInt(br.readLine());
            if (number != 0) {
                deque.push(number);
            } else {
                deque.pop();
            }
            count --;
        }

        while (!deque.isEmpty()) {
            answer += deque.pollFirst();
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}
