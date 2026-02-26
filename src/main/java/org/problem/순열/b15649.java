package org.problem.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class b15649 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        recursion(n, m, 1, new ArrayList<>(), new boolean[n + 1]);
    }

    static void recursion(int n, int m, int depth, List<Integer> numbers, boolean[] visited) {
        if (depth > m) {
            for (Integer number : numbers) {
                System.out.print(number + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            numbers.add(i);
            visited[i] = true;
            recursion(n, m, depth + 1, numbers, visited);
            visited[i] = false;
            numbers.remove(numbers.size() - 1);
        }
    }
}
