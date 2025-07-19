package org.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class b11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Comparator<Integer> comparator = (o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if (abs1 == abs2) return o1 - o2;
            return abs1 - abs2;
        };


        PriorityQueue<Integer> pq = new PriorityQueue<>(comparator);

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            if (input.equals("0")) {
                if (pq.isEmpty()) {
                    sb.append("0\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }
            } else {
                int num = Integer.parseInt(input);
                pq.add(num);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
