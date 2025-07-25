package org.practice.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실버1 IOIOI
public class b5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int count = 0;   // OI가 몇 번 반복되었는지
        int result = 0;

        for (int i = 1; i < m - 1; ) {
            if (s.charAt(i - 1) == 'I' && s.charAt(i) == 'O' && s.charAt(i + 1) == 'I') {
                count++;
                if (count == n) {
                    result++;
                    count--; // 겹치는 패턴을 위해 한 번 줄이기
                }
                i += 2;
            } else {
                count = 0;
                i++;
            }
        }

        System.out.println(result);
    }
}