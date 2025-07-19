package org.practice.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 2
// 피보나치 수 6
// 100경 번째 피보나치를 구해야 함 시간 제한은 1억 번 연산 이내로 제한
public class b11444 {
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.println(fibonacci(n));

    }

    // 피보나치를 행렬로 변환
    static long fibonacci(long n) {
        if (n == 0) return 0;
        long[][] base = {
                {1, 1},
                {1, 0}
        };
        long[][] result = power(base, n);
        return result[0][1];
    }

    // 행렬 제곱 계산
    static long[][] power(long[][] matrix, long exp) {
        if (exp == 1) return matrix;

        long[][] half = power(matrix, exp / 2);
        long[][] result = multiply(half, half);

        if (exp % 2 == 1) {
            result = multiply(result, matrix);
        }

        return result;
    }

    // 행렬 곱셈
    static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;
        return result;
    }
}
