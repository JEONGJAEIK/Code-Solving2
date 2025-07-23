package org.practice.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실버 2 랜선 자르기
public class b1652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[k];

        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long left = 1;
        long right = arr[k - 1];
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int i : arr) {
                count += i / mid;
            }

            if (count < n) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
}
