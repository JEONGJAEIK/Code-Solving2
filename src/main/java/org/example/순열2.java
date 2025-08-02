package org.example;

import java.util.ArrayList;
import java.util.List;

public class 순열2 {

    public static void generateFullPermutation(int[] arr, List<Integer> current,
                                               boolean[] visited, List<List<Integer>> result, int depth) {

        System.out.printf("%s[재귀 %d] 진입 → current = %s, visited = %s%n",
                "  ".repeat(depth), depth, current, booleanArrayToString(visited));

        if (current.size() == arr.length) {
            result.add(new ArrayList<>(current));
            System.out.printf("%s[재귀 %d] 🎯 순열 완성 → 저장: %s%n",
                    "  ".repeat(depth), depth, current);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%s[재귀 %d, for i=%d] 검사 → visited[%d] = %s%n",
                    "  ".repeat(depth), depth, i, i, visited[i] ? "T" : "F");

            if (!visited[i]) {
                // 선택
                current.add(arr[i]);
                visited[i] = true;

                System.out.printf("%s[재귀 %d, for i=%d] ✅ 선택: arr[%d] = %d → current = %s%n",
                        "  ".repeat(depth), depth, i, i, arr[i], current);

                // 재귀 호출
                generateFullPermutation(arr, current, visited, result, depth + 1);

                // 백트래킹
                current.remove(current.size() - 1);
                visited[i] = false;

                System.out.printf("%s[재귀 %d, for i=%d] ↩ 백트래킹: arr[%d] 제거 → current = %s, visited = %s%n",
                        "  ".repeat(depth), depth, i, i, current, booleanArrayToString(visited));
            }
        }
    }

    private static String booleanArrayToString(boolean[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] ? "T" : "F");
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[arr.length];

        generateFullPermutation(arr, new ArrayList<>(), visited, result, 0);

        System.out.println("\n✅ 최종 순열 결과:");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
}
