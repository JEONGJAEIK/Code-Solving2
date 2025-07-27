package org.example;

/*
 * 중복 순열 (Permutation with repetition)은 순서를 고려하며,
 * 같은 원소를 여러 번 선택할 수 있는 경우입니다.
 * 예를 들어 [1, 2, 3]에서 2개를 뽑으면:
 * → [1,1], [1,2], [1,3], [2,1], [2,2], [2,3], [3,1], [3,2], [3,3]
 *
 * 특징:
 * - 중복 허용
 * - 순서 중요
 * - 매번 모든 원소를 탐색 (i는 0부터 arr.length - 1까지)
 */
public class 중복순열 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3}; // 원소 집합
        int r = 2;             // 뽑을 개수

        System.out.println("중복 순열 결과:");
        permutation(arr, new int[r], 0, r);
    }

    /**
     * @param arr   선택 가능한 원소들
     * @param out   현재까지 선택된 순열 저장 배열
     * @param depth 현재까지 선택한 개수
     * @param r     뽑을 원소 개수
     */
    static void permutation(int[] arr, int[] out, int depth, int r) {
        // r개 다 뽑았으면 출력
        if (depth == r) {
            for (int num : out) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        // 매번 0부터 끝까지 탐색하며 중복 허용
        for (int i = 0; i < arr.length; i++) {
            out[depth] = arr[i];
            permutation(arr, out, depth + 1, r);
        }
    }
}

