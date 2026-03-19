package org.problem.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class b10799 {
    static char[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        arr = input.toCharArray();

        int answer = 0;
        int stick = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stick++;
            }

            if (arr[i] == ')') {
                stick--;
                if (arr[i - 1] == '(') {
                    answer += stick;
                } else {
                    answer += 1;
                }
            }
        }

        System.out.println(answer);
    }
}
