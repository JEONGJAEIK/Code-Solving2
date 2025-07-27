package org.example;

/*
 * 조합(Combination)은 순서를 고려하지 않고 r개를 선택하는 경우를 말합니다.
 * 예를 들어 [1, 2, 3]에서 2개를 고르면 → [1,2], [1,3], [2,3]
 *
 * 이 예제는 조합을 백트래킹 방식으로 구현한 코드입니다.
 * - 사용된 visited[] 배열은 현재 선택된 인덱스를 체크합니다.
 * - start 변수로 중복 없이 이후 인덱스만 선택하게 만듭니다.
 * - 시간복잡도는 O(nCr) 수준이며, 중복이 없는 선택에 적합합니다.
 */
public class 조합 {
    // 조합을 구하는 재귀 함수
    public static void combination(int[] arr, boolean[] visited, int start, int depth, int r) {
        // r개를 선택했으면 출력
        if (depth == r) {
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        // start부터 끝까지 순회하며 조합 구성
        for (int i = start; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;                              // 현재 인덱스 선택
                combination(arr, visited, i + 1, depth + 1, r); // 다음 인덱스로 진행
                visited[i] = false;                             // 백트래킹 (되돌리기)
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3}; // 조합을 만들 원소들
        int r = 2;             // r개를 선택하는 조합

        System.out.println("조합 결과:");
        combination(arr, new boolean[arr.length], 0, 0, r);
    }
}

