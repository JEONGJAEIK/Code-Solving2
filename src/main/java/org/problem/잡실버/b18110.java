package org.problem.잡실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b18110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(score);
        int minCount = Math.round((float) score.length / 100 * 15);
        int maxCount = Math.round((float) score.length / 100 * 15);
        int total = 0;
        float average = 0;
        for (int i = minCount; i < score.length - maxCount; i++) {
            total += score[i];
        }
        average = (float) total / (score.length - maxCount - minCount);
        System.out.println(Math.round(average));
    }
}
