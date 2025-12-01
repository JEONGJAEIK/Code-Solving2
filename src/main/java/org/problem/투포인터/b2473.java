package org.problem.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 골드 3 세 용액
public class b2473 {
    static long[] arr;
    static long sum = 3000000001L; //30억 + 1
    static long[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 전체 용액 수 3~5000
        arr = new long[n];
        result = new long[3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // -10억 ~ 10억
        }

        Arrays.sort(arr);

        // 포인터를 하나 고정하고 나머지 투 포인터 5000 * 5000이라 충분하다.
        for (int i = 0; i < arr.length; i++) {
            // 여기서 i는 고정된 포인터
            int start = 0;
            int end = arr.length - 1;
            while (start < end) {
                if (start == i) {
                    start++;
                    continue;
                }

                if (end == i) {
                    end--;
                    continue;
                }

                if (arr[start] + arr[end] + arr[i] == 0) {
                    sum = arr[start] + arr[end] + arr[i];
                    setResult(arr[start], arr[end], arr[i]);
                    break;
                }

                if (arr[start] + arr[end] + arr[i] < 0) {
                    if (Math.abs(arr[start] + arr[end] + arr[i]) < Math.abs(sum)) {
                        sum = arr[start] + arr[end] + arr[i];
                        setResult(arr[start], arr[end], arr[i]);
                    }
                    start++;
                } else if (arr[start] + arr[end] + arr[i] >= 0) {
                    if (Math.abs(arr[start] + arr[end] + arr[i]) < Math.abs(sum)) {
                        sum = arr[start] + arr[end] + arr[i];
                        setResult(arr[start], arr[end], arr[i]);
                    }
                    end--;
                }
            }
        }
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    static void setResult(long a, long b, long c) {
        result[0] = a;
        result[1] = b;
        result[2] = c;
        Arrays.sort(result);
    }
}
