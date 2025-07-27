package org.example;

/*
 * 중복 조합 (nHr)은 순서를 고려하지 않고, 같은 원소를 여러 번 선택할 수 있는 조합입니다.
 * 예를 들어 [1, 2, 3]에서 2개를 중복 조합으로 고르면:
 * → [1,1], [1,2], [1,3], [2,2], [2,3], [3,3]
 *
 * 특징:
 * - 순서 X, 중복 허용 O
 * - 다음 재귀 호출에 현재 인덱스(i)를 그대로 넘김 (i+1 아님)
 * - 백트래킹 없이 값만 덮어쓰는 방식
 */
public class 중복조합 {
    /**
     * @param arr   선택할 원소들
     * @param out   현재까지 선택된 조합을 저장하는 배열
     * @param start 다음 선택을 시작할 인덱스
     * @param depth 현재까지 뽑은 원소 개수
     * @param r     총 r개를 뽑는 목표
     */
    public static void combination(int[] arr, int[] out, int start, int depth, int r) {
        // r개를 모두 선택한 경우 출력
        if (depth == r) {
            for (int num : out) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        // 현재 인덱스부터 끝까지 반복 (중복 허용 → i 그대로 넘김)
        for (int i = start; i < arr.length; i++) {
            out[depth] = arr[i];
            combination(arr, out, i, depth + 1, r); // i를 그대로 넘김
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3}; // 선택 가능한 숫자들
        int r = 2;             // 뽑을 개수

        System.out.println("중복 조합 결과:");
        combination(arr, new int[r], 0, 0, r);
    }
}

