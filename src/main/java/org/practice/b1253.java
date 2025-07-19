package org.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);


        int count = 0;


        for (int i = 0; i < n; i++) {
            int target = arr[i];
            int start = 0;
            int end = n - 1;
            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }
                if (end == i) {
                    end--;
                    continue;
                } else if (arr[start] + arr[end] == target) {
                    count++;
                    break;
                } else if (arr[start] + arr[end] < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        System.out.println(count);
    }
}
