package org.example;

import java.util.Arrays;

public class 순열 {

    // arr: 원본 배열
    // output: 선택된 순열 저장 배열
    // visited: 해당 인덱스를 사용했는지 여부
    // depth: 현재까지 선택한 원소 개수
    // r: 뽑을 개수
    public static void generatePermutation(int[] arr, int r) {
        int[] output = new int[r];
        boolean[] visited = new boolean[arr.length];
        backtrack(arr, output, visited, 0, r);
    }

    private static void backtrack(int[] arr, int[] output, boolean[] visited, int depth, int r) {
        // r개를 모두 선택했을 경우 출력
        if (depth == r) {
            System.out.println(Arrays.toString(output));
            return;
        }

        // 모든 원소를 확인하며 선택되지 않은 원소를 뽑는다
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;             // 방문 체크
                output[depth] = arr[i];        // 현재 위치에 값 저장
                backtrack(arr, output, visited, depth + 1, r); // 다음 자리로 이동
                visited[i] = false;            // 백트래킹 (다시 되돌리기)
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3}; // 순열을 생성할 원소들
        int r = 2;             // 2개를 뽑는 순열

        System.out.println("순열 결과:");
        generatePermutation(arr, r);
    }
}

