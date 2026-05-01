package org.problem.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1406 {
    static int cursor;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        cursor = input.length();
        sb = new StringBuilder(input);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            if (s.equals("P")) {
                String appendString = st.nextToken();
                append(appendString);
            } else {
                modify(s);
            }
        }

        System.out.println(sb);
    }
    
    
    
    static void modify(String input) {
        if (input.equals("L")) {
            if (cursor > 0) {
                cursor--;
            }
        } else if (input.equals("D")) {
            if (cursor < sb.length()) {
                cursor++;
            }
        } else if (input.equals("B")) {
            if (cursor > 0) {
                sb.deleteCharAt(cursor - 1);
                cursor--;
            }
        }
    }

    static void append(String append) {
        sb.insert(cursor, append);
        cursor++;
    }
}
