package org.problem.문자열;

import java.io.*;

// 실버 4 괄호
public class b9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < totalCount; i++) {
            String line = br.readLine();
            if (isValidParenthesis(line)) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static boolean isValidParenthesis(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }

            if (count < 0) return false;
        }

        return count == 0;
    }
}

