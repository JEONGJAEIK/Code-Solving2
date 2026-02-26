package org.problem.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 실버 3 서로 다른 부분 문자열의 개수
public class b11478 {
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        for (int i = 1; i <= input.length(); i++) { // 자르는 길이
            for (int j = 0; j <= input.length() - i; j++) { // 자르는 시작점
                String subString = input.substring(j, j + i); // 자르는 부분의 직전까지
                set.add(subString);
            }
        }

        System.out.println(set.size());
    }
}
