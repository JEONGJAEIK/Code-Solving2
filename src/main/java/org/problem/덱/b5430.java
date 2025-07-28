package org.problem.덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Deque<Integer> deque;
        int tc = Integer.parseInt(br.readLine());

        while (tc > 0) {
            String function = br.readLine();
            int arrLength = Integer.parseInt(br.readLine());
            String arrString = br.readLine();
            arrString = arrString.substring(1, arrString.length() - 1);

            deque = new ArrayDeque<>();
            st = new StringTokenizer(arrString, ",");
            if (st.countTokens() > 0) {
                for (int i = 0; i < arrLength; i++) {
                    deque.add(Integer.parseInt(st.nextToken()));
                }
            }

            boolean isError = false;
            boolean isReversed = false;

            for (char cmd : function.toCharArray()) {
                if (cmd == 'R') {
                    isReversed = !isReversed;
                } else {
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    } else {
                        if (isReversed) deque.pollLast();
                        else deque.pollFirst();
                    }
                }
            }

            if (isError) {
                System.out.println("error");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                while (!deque.isEmpty()) {
                    sb.append(isReversed ? deque.pollLast() : deque.pollFirst());
                    if (!deque.isEmpty()) sb.append(",");
                }
                sb.append("]");
                System.out.println(sb);
            }

            tc--;
        }
    }
}
