package org.problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class b2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] digits = br.readLine().toCharArray();
        Character[] digitChars = new Character[digits.length];
        for (int i = 0; i < digits.length; i++) {
            digitChars[i] = digits[i];
        }

        Arrays.sort(digitChars, Comparator.reverseOrder());


        for (char c : digitChars) {
            sb.append(c);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
