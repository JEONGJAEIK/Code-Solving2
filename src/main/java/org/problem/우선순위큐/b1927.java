package org.problem.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class b1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> tree = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());

            if (value != 0) {
                tree.add(value);
            } else {
                if (tree.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(tree.poll());
                }

            }
        }
    }
}
