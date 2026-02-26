package org.problem.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실버 5 뒤집기
public class b1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char[] arr = input.toCharArray();
        int zeroCount = 0;
        int oneCount = 0;
        int result = 0;
        char last = arr[0];
        if (arr[0] == '0') {
            zeroCount++;
        } else {
            oneCount++;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != last) {
                last = arr[i];
                if (arr[i] == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
        }
        result = Math.min(oneCount, zeroCount);
        System.out.println(result);
    }
}
