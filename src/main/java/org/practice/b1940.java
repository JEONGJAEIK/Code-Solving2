package org.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b1940 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int start = 0;
        int end = arr.length - 1;
        int count = 0;
        int sum = 0;

        while (start < end) {
            sum = arr[start] + arr[end];
            if (sum == m) {
                count++;
                start++;
                end--;
            } else if (sum < m) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(count);
    }
}
