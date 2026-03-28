package org.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 골드 4 리모컨
public class b1107 {
    static int count = 0;
    static StringBuilder sb = new StringBuilder();
    static Set<Integer> button = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 9; i++) {
            button.add(i);
        }

        for (int i = 0; i < n; i++) {
            button.remove(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            if (button.contains((int) c)) {
                sb.append(c);
                count++;
            } else {
                int number = findNumber((int) c);
                sb.append(number);
            }
        }
    }

    static int findNumber(int c) {
        if (c - 1 >= 0 && button.contains(c - 1)) {
            return c - 1;
        } else if (c + 1 < 10 && button.contains(c + 1)){
            return c + 1;
        } else {
            return findNumber(c - 1);
        }
    }
}
