package org.problem.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class b15651 {
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        recursion(n, m, 1, new ArrayList<>());
        System.out.println(stringBuilder);
    }

    static void recursion(int n, int m, int depth, List<Integer> numbers) {
        if (depth > m) {
            for (Integer number : numbers) {
                stringBuilder.append(number).append(" ");
            }
            stringBuilder.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
            recursion(n, m, depth + 1, numbers);
            numbers.remove(numbers.size() - 1);
        }
    }
}
