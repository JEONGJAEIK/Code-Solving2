package org.practice.정수론;

import java.io.*;

public class b1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        boolean[] isNotPrime = new boolean[n + 1];
        isNotPrime[0] = isNotPrime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        for (int i = m; i <= n; i++) {
            if (!isNotPrime[i]) {
                bw.write(i + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
