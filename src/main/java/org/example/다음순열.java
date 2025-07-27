package org.example;

/*
 * 다음 순열 (Next Permutation) 알고리즘 구현 예시
 *
 * 주어진 배열이 사전순으로 다음에 오는 순열을 구합니다.
 * 만약 다음 순열이 없으면 false를 반환합니다.
 *
 * 예시: [1,2,3] → [1,3,2]
 *       [3,2,1] → 없음 (-1 출력)
 */
public class 다음순열 {
    public static void main(String[] args) {
        // 직접 초기화한 배열
        int[] arr = {1, 2, 3};

        boolean hasNext = nextPermutation(arr);

        if (hasNext) {
            System.out.print("다음 순열: ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
        } else {
            System.out.println(-1); // 다음 순열 없음
        }
    }

    private static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;

        // 1. 뒤에서부터 (i-1)번째가 i번째보다 작은 위치 찾기
        //    (오른쪽 끝에서부터 비내림차순 구간을 찾음)
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        // i가 0이면 이미 마지막 순열임
        if (i <= 0) return false;

        // 2. (i-1)번째 값보다 큰 수 중 가장 오른쪽에 있는 수 찾기
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) {
            j--;
        }

        // 3. swap(arr[i-1], arr[j])
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        // 4. i부터 끝까지 배열을 뒤집어서 오름차순으로 만듦
        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return true; // 다음 순열 존재
    }
}
