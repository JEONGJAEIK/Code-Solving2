package org.problem.잡브론즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b2587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        int average = recursion(array.length, array) / 5;
        System.out.println(average);
        Arrays.sort(array);
        System.out.println(array[2]);
    }

    public static int recursion(int n, int[] array) {
        if (n == 0) {
            return 0;
        }
        return recursion(n - 1, array) + array[n - 1];
    }
}
