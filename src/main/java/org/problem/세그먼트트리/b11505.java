package org.problem.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드 1 구간 곱 구하기
public class b11505 {
    static long[] arr;
    static int l;
    static int mid;
    static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 세그먼트트리 길이 구하기
        for (int i = 1; i < n; i++) {
            if (Math.pow(2, i) >= n) {
                l = i;
                break;
            }
        }
        mid = (int) Math.pow(2, l);
        arr = new long[mid * 2];

        // 모든 변수 1로 초기화
        for (int i = 1; i < arr.length; i++) {
            arr[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            arr[mid + i] = Integer.parseInt(br.readLine());
        }

        // 세그먼트트리 초기화 (모듈러 연산)
        for (int i = arr.length - 1; i > 0; i--) {
            int index = i / 2;
            arr[index] = (arr[index] * arr[i] % MOD);
        }

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) { // b를 c로 교체
                update(b, c);
            } else {  // b부터 c까지 합을 구하여 출력
                long result = query(b, c);
                System.out.println(result);
            }
        }
    }

    private static void update(int b, int c) {
        int index = b + mid - 1;
        arr[index] = c;
        index = index / 2;
        while (index > 0) {
            arr[index] = (arr[index * 2] * arr[index * 2 + 1]) % MOD;
            index = index / 2;
        }
    }

    private static long query(int start, int end) {
        int startIndex = start + mid - 1;
        int endIndex = end + mid - 1;
        long result = 1;

        while (startIndex <= endIndex) {
            if (startIndex % 2 == 1) {
                result = (result * arr[startIndex]) % MOD;
                startIndex = (startIndex + 1) / 2;
            } else {
                startIndex = startIndex / 2;
            }

            if (endIndex % 2 == 0) {
                result = (result * arr[endIndex]) % MOD;
                endIndex = (endIndex - 1) / 2;
            } else {
                endIndex = endIndex / 2;
            }
        }
        return result;
    }
}
