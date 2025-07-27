package org.example;

import java.io.*;

public class 에라토스테네스의체 {
    public static void main(String[] args) throws IOException {
        int n = 50; // 소수를 구할 최대값 (직접 입력)

        boolean[] isNotPrime = new boolean[n + 1]; // 소수 여부 배열 (true면 소수가 아님)
        isNotPrime[0] = true; // 0은 소수가 아님
        isNotPrime[1] = true; // 1도 소수가 아님

        // 2부터 n까지 모든 수를 검사할 필요 없이, i*i <= n 까지만 검사하면 된다.
        // 왜냐하면 어떤 수의 배수들은 그 이전에 이미 처리되기 때문.
        for (int i = 2; i <= n; i++) {
            if (isNotPrime[i]) {
                // i가 이미 소수가 아니면 넘어감
                continue;
            }

            // i가 소수이면, i의 배수들을 모두 소수가 아니라고 표시
            // i의 배수 중 i*i 보다 작은 수는 이미 이전 단계에서 처리됨
            int start = i * i; // i*i부터 시작하는 이유: i의 배수 중 i*i 이전의 배수들은 이미 지워짐

            for (int j = start; j <= n; j += i) {
                isNotPrime[j] = true; // i의 배수이므로 소수가 아님 표시
            }
        }

        // 소수 출력 (필요하면)
        System.out.println("2부터 " + n + "까지의 소수 목록:");
        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }
}

