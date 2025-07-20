package org.practice.덱;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class b17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (deque.isEmpty()) {
                deque.addLast(Integer.valueOf(a));
            } else if (deque.peekLast() >= Integer.valueOf(a)) {
                deque.addLast(Integer.valueOf(a));
                arr[i - 1] = -1;
            } else {
                deque.addLast(Integer.valueOf(a));
                arr[i - 1] = deque.peekLast();
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(" ");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }
}
