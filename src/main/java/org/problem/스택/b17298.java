package org.problem.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 골드 4 오큰수
public class b17298 {
    static int[] arr;
    static int[] rightMaxNumberArr;
    static Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 수열의 크기 1 ~ 100만
        arr = new int[n];
        rightMaxNumberArr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 숫자의 크기 1 ~ 100만
        }

        stack.push(0);
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty()) {
                if (arr[stack.peek()] < arr[i]) {
                    rightMaxNumberArr[stack.peek()] = arr[i];
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(i);
        }

        for (int i = 0; i < rightMaxNumberArr.length; i++) {
            if (rightMaxNumberArr[i] == 0) {
                rightMaxNumberArr[i] = -1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rightMaxNumberArr.length; i++) {
            if (i == rightMaxNumberArr.length - 1) {
                sb.append(rightMaxNumberArr[i]);
            } else {
                sb.append(rightMaxNumberArr[i]).append(" ");
            }
        }

        System.out.println(sb);

    }
}
