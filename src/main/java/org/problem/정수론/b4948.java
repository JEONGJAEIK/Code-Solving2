package org.problem.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 실버 2 베르트랑 공준
public class b4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }


            int range = n * 2;
            boolean[] isPrime = new boolean[range + 1];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;

            for (int i = 2; i * i <= range ; i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j <= range; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            int result = 0;
            for (int i = n + 1; i < isPrime.length; i++) {
                if (isPrime[i]) {
                    result++;
                }
            }

            System.out.println(result);
        }
    }
}
