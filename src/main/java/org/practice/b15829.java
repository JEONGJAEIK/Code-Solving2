package org.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b15829 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        int r = 1;
        long mod = 1234567891;
        long result = 0;

        String str = br.readLine();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            int value = c - 96;
            result = (result + (long) value * r) % mod;
            r = Math.toIntExact((r * 31L) % mod);
        }
        System.out.println(result);
    }
}
