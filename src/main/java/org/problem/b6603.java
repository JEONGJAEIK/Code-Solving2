package org.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 실버 2 로또
// 조합 문제
public class b6603 {
    static StringBuilder sb = new StringBuilder();
    static int count;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            count = Integer.parseInt(st.nextToken());

            if (count == 0) {
                break;
            }

            arr = new int[count + 1];
            for (int i = 1; i < count + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            recursion(new ArrayList<>(), 1);
            System.out.println(sb);
            sb = new StringBuilder();
        }
    }


    static void recursion(List<Integer> list, int start) {
        if (list.size() == 6) {
            for (Integer i : list) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= count; i++) {
            list.add(arr[i]);
            recursion(list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

