package org.pratice;

import java.util.ArrayList;
import java.util.List;

public class 순열 {
    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> list = new ArrayList<>();
    static int[] arr = {1, 2, 3, 4};
    static boolean[] visited = new boolean[4];
    public static void main(String[] args) {

        dd();

        for (List<Integer> integers : result) {
            System.out.println(integers);
        }
    }

    static void dd () {
        if (list.size() == 4) {
            result.add(new ArrayList<>(list));
        }

        for (int i = 0; i < arr.length; i++) {

            if (visited[i]) {
                continue;
            }

            list.add(arr[i]);
            visited[i] = true;
            dd();

            list.removeLast();
            visited[i] = false;
        }
    }
}
