package org.practice;

import java.io.*;

public class b17068 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sticks = new int[N];

        for (int i = 0; i < N; i++) {
            sticks[i] = Integer.parseInt(br.readLine());
        }

        int visibleCount = 1;
        int maxHeight = sticks[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            if (sticks[i] > maxHeight) {
                visibleCount++;
                maxHeight = sticks[i];
            }
        }

        System.out.println(visibleCount);
    }
}
