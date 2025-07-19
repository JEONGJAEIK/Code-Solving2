package org.practice;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class b2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> queue = new ArrayDeque<>();
        int count = Integer.parseInt(br.readLine());

        for (int i = 1; i < count + 1; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            queue.remove();
            queue.add(queue.remove());
        }

        bw.write(String.valueOf(queue.element()));
        bw.flush();
        bw.close();
    }
}
