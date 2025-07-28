package org.example;

import java.util.*;

/*
=== 다음순열(Next Permutation) 알고리즘 기본 개념 ===
- 현재 순열에서 사전순으로 바로 다음에 오는 순열을 찾는 알고리즘
- 모든 순열을 생성하지 않고도 순차적으로 다음 순열만 구할 수 있음
- 시간복잡도: O(n) - 한 번의 다음순열 계산
- 공간복잡도: O(1) - 추가 공간 불필요 (in-place 알고리즘)
- 전체 순열 생성시: O(n! * n) - n!개의 순열 각각 O(n)시간

=== 선수 지식 ===
1. 배열 조작과 인덱스 개념
2. 사전순(Lexicographic Order) 이해
3. 순열(Permutation)의 기본 개념
4. 배열 역순 정렬 알고리즘

=== 코딩테스트에서 다음순열이 사용되는 문제 유형 ===
1. 순열 생성 문제 (모든 순열을 순서대로 나열)
2. 사전순 다음/이전 문자열 찾기
3. 숫자 순열에서 다음으로 큰 수 찾기
4. 조합론적 열거 문제
5. 백트래킹 최적화 (순열 공간 탐색)
6. 문자열 재배열 문제
7. 경우의 수 문제에서 특정 순서의 다음 경우 찾기

=== 혼동하기 쉬운 유사 알고리즘들 ===
1. 전체 순열 생성(DFS/백트래킹) vs 다음순열
   - 전체 순열: 모든 순열을 한 번에 생성 (메모리 많이 사용)
   - 다음순열: 필요할 때마다 다음 순열만 생성 (메모리 효율적)

2. 조합(Combination) vs 순열(Permutation)
   - 조합: 순서 상관없음, 선택만 중요
   - 순열: 순서가 중요함, 배치도 고려

3. 문자열 정렬 vs 다음순열
   - 정렬: 전체를 사전순으로 배치
   - 다음순열: 현재 상태에서 바로 다음만 구함

=== 실수하기 쉬운 접근법 ===
- 모든 순열을 생성한 후 다음것 찾기 (비효율적)
- 사전순 개념 이해 없이 단순 증가로 접근
- 마지막 순열 처리를 놓치는 경우
- 중복된 원소가 있는 경우의 처리 미고려

=== 핵심 아이디어 ===
사전순에서 다음 순열을 찾는 4단계:
1. 뒤에서부터 찾아서 arr[i] < arr[i+1]인 첫 번째 i 찾기
2. 뒤에서부터 찾아서 arr[i] < arr[j]인 첫 번째 j 찾기
3. arr[i]와 arr[j] 교환
4. i+1부터 끝까지 역순 정렬
*/

public class 다음순열 {

    public static void main(String[] args) {
        // 실전 시나리오: 숫자 배열의 다음 순열 찾기
        int[] arr = {1, 2, 3, 4};

        System.out.println("시작 순열: " + Arrays.toString(arr));

        // 모든 순열을 순차적으로 생성
        int count = 1;
        while (nextPermutation(arr)) {
            count++;
            System.out.println(count + "번째 순열: " + Arrays.toString(arr));

            // 무한루프 방지 (실전에서는 필요시에만)
            if (count >= 24) break; // 4! = 24
        }

        System.out.println("마지막 순열에 도달했습니다.");

        // 실전 예시: 특정 상황에서 다음 순열 구하기
        int[] testCase = {1, 3, 2};
        System.out.println("\n특정 순열: " + Arrays.toString(testCase));
        if (nextPermutation(testCase)) {
            System.out.println("다음 순열: " + Arrays.toString(testCase));
        }
    }

    /**
     * 다음 순열을 구하는 핵심 메서드
     * @param arr 순열을 구할 배열 (in-place로 수정됨)
     * @return 다음 순열이 존재하면 true, 마지막 순열이면 false
     */
    public static boolean nextPermutation(int[] arr) {
        int n = arr.length;

        // Step 1: 뒤에서부터 arr[i] < arr[i+1]인 첫 번째 위치 i 찾기
        // 이 위치가 "꺾이는 지점"으로, 증가시킬 수 있는 마지막 위치
        int i = n - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--; // 내림차순 구간을 건너뛰기
        }

        // 전체가 내림차순이면 마지막 순열 (더 이상 다음 순열 없음)
        // 예: [4,3,2,1]은 가장 큰 순열이므로 다음이 없음
        if (i < 0) {
            return false;
        }

        // Step 2: 뒤에서부터 arr[i] < arr[j]인 첫 번째 위치 j 찾기
        // arr[i]보다 크면서 가장 작은 값의 위치를 찾는 것
        int j = n - 1;
        while (arr[j] <= arr[i]) {
            j--; // arr[i]보다 큰 첫 번째 원소 찾기
        }

        // Step 3: arr[i]와 arr[j] 교환
        // 이렇게 하면 i 위치에 더 큰 값이 와서 순열이 증가함
        swap(arr, i, j);

        // Step 4: i+1부터 끝까지 오름차순 정렬 (역순 뒤집기)
        // i 이후 부분은 내림차순으로 되어있으므로 뒤집으면 오름차순
        // 이렇게 해야 i 위치의 새로운 값에 대해 가장 작은 순열이 됨
        reverse(arr, i + 1, n - 1);

        return true;
    }

    /**
     * 배열의 두 원소를 교환하는 헬퍼 메서드
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 배열의 특정 구간을 역순으로 뒤집는 헬퍼 메서드
     * @param arr 대상 배열
     * @param start 시작 인덱스 (포함)
     * @param end 끝 인덱스 (포함)
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    /**
     * 이전 순열을 구하는 메서드 (다음순열의 역과정)
     * 실전에서 가끔 필요한 경우가 있음
     */
    public static boolean previousPermutation(int[] arr) {
        int n = arr.length;

        // Step 1: 뒤에서부터 arr[i] > arr[i+1]인 첫 번째 위치 i 찾기
        int i = n - 2;
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }

        // 전체가 오름차순이면 첫 번째 순열 (더 이상 이전 순열 없음)
        if (i < 0) {
            return false;
        }

        // Step 2: 뒤에서부터 arr[i] > arr[j]인 첫 번째 위치 j 찾기
        int j = n - 1;
        while (arr[j] >= arr[i]) {
            j--;
        }

        // Step 3: arr[i]와 arr[j] 교환
        swap(arr, i, j);

        // Step 4: i+1부터 끝까지 내림차순 정렬 (역순 뒤집기)
        reverse(arr, i + 1, n - 1);

        return true;
    }

    /**
     * 특정 순열이 몇 번째 순열인지 계산하는 메서드
     * 팩토리얼 수 체계를 이용한 순열 인덱스 계산
     */
    public static long getPermutationIndex(int[] arr) {
        int n = arr.length;
        long index = 0;
        long factorial = 1;

        // 팩토리얼 계산: (n-1)!
        for (int i = 1; i < n; i++) {
            factorial *= i;
        }

        // 각 자리수에 대해 앞서 오는 원소들의 개수 계산
        for (int i = 0; i < n; i++) {
            int smaller = 0;

            // 현재 원소보다 작으면서 아직 사용되지 않은 원소 개수
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    smaller++;
                }
            }

            // 현재 자리에서의 기여도 계산
            index += smaller * factorial;

            // 다음 자리를 위한 팩토리얼 업데이트
            if (i < n - 1) {
                factorial /= (n - 1 - i);
            }
        }

        return index;
    }

    /**
     * k번째 순열을 직접 생성하는 메서드
     * 모든 순열을 생성하지 않고도 특정 순열을 바로 구할 수 있음
     */
    public static int[] getKthPermutation(int n, long k) {
        // 1부터 n까지의 숫자 리스트 생성
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        int[] result = new int[n];
        long factorial = 1;

        // (n-1)! 계산
        for (int i = 1; i < n; i++) {
            factorial *= i;
        }

        k--; // 0-based 인덱스로 변환

        // 각 자리수 결정
        for (int i = 0; i < n; i++) {
            // 현재 자리에 올 숫자의 인덱스 계산
            int index = (int)(k / factorial);

            // 해당 숫자를 결과에 추가하고 리스트에서 제거
            result[i] = numbers.remove(index);

            // 다음 자리를 위한 k와 factorial 업데이트
            k %= factorial;
            if (i < n - 1) {
                factorial /= (n - 1 - i);
            }
        }

        return result;
    }
}