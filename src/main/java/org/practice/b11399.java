package org.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int[] sumArr = new int[count];
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                sumArr[i] = arr[i];
            } else {
                sumArr[i] = sumArr[i - 1] + arr[i];
            }
        }

        int result = 0;
        for (int i = 0; i < count; i++) {
            result += sumArr[i];
        }
        System.out.println(result);
    }
}
