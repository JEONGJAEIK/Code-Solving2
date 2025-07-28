package org.example;

import java.util.*;

/*
=== 조합(Combination) 알고리즘 기본 개념 ===
- 조합: n개의 원소 중에서 r개를 순서와 상관없이 선택하는 경우의 수
- 공식: nCr = n! / (r! * (n-r)!)
- 시간복잡도: O(2^n) - 최악의 경우 모든 부분집합을 확인
- 공간복잡도: O(r) - 재귀 깊이와 현재 조합을 저장하는 배열

=== 선수 지식 ===
1. 재귀(Recursion) 개념과 구현
2. 백트래킹(Backtracking) 원리
3. 배열과 리스트 조작
4. 수학적 조합 개념 (nCr)

=== 코딩테스트에서 조합이 사용되는 문제 유형 ===
1. 부분집합 문제 (특정 조건을 만족하는 원소들의 조합)
2. 경우의 수 계산 문제
3. 최적화 문제 (모든 조합을 시도해서 최적값 찾기)
4. 조합론 문제 (순열과 조합의 직접적 계산)
5. 그래프에서 정점/간선 선택 문제
6. 배낭 문제의 변형 (아이템 조합 선택)

=== 혼동하기 쉬운 유사 알고리즘들 ===
1. 순열(Permutation) vs 조합(Combination)
   - 순열: 순서가 중요함 (ABC ≠ BCA)
   - 조합: 순서가 중요하지 않음 (ABC = BCA = CAB)

2. 부분집합(Subset) vs 조합
   - 부분집합: 모든 크기의 조합을 포함 (공집합 포함)
   - 조합: 특정 크기 r의 조합만

3. DFS vs 조합
   - DFS: 그래프/트리 탐색이 목적
   - 조합: 원소 선택이 목적 (그래프 구조 불필요)

=== 실수하기 쉬운 접근법 ===
- 중복조합 문제를 일반 조합으로 접근
- 순서가 중요한 문제를 조합으로 접근 (순열 써야 함)
- 메모리 제한이 큰 문제에서 모든 조합을 저장하려고 시도
*/

public class 조합 {

    public static void main(String[] args) {
        // 실전 코딩테스트 시나리오: 5명 중 3명을 선택하는 조합
        int[] people = {1, 2, 3, 4, 5};  // 사람 번호
        int r = 3;  // 선택할 인원 수

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        // 조합 생성 시작
        generateCombinations(people, r, 0, current, result);

        // 결과 출력 (실전에서는 필요한 처리만)
        System.out.println("총 " + result.size() + "가지 조합:");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    /**
     * 재귀를 이용한 조합 생성 메서드
     * @param arr 전체 원소 배열
     * @param r 선택할 원소의 개수
     * @param start 현재 탐색 시작 인덱스 (중복 방지용)
     * @param current 현재까지 선택된 원소들
     * @param result 모든 조합을 저장할 리스트
     */
    private static void generateCombinations(int[] arr, int r, int start,
                                             List<Integer> current, List<List<Integer>> result) {

        // 기저 조건: r개를 모두 선택했을 때
        if (current.size() == r) {
            // 현재 조합을 결과에 추가 (깊은 복사 필요!)
            result.add(new ArrayList<>(current));
            return;  // 재귀 종료
        }

        // start부터 배열 끝까지 탐색
        // start 사용 이유: 이미 고려한 원소들을 다시 선택하지 않기 위함
        // 예: [1,2]를 선택했다면, 다음엔 3부터 시작해서 [1,2,1] 같은 중복 방지
        for (int i = start; i < arr.length; i++) {

            // 가지치기: 남은 원소 수가 부족한 경우 조기 종료
            // 현재 선택된 개수 + 남은 원소 개수 < 목표 개수라면 불가능
            if (current.size() + (arr.length - i) < r) {
                break;  // 더 이상 진행해도 r개를 못 채움
            }

            // 현재 원소를 조합에 추가
            current.add(arr[i]);

            // 재귀 호출: 다음 위치부터 탐색
            // i+1을 전달하는 이유: 현재 선택한 원소 이후부터 다시 선택
            generateCombinations(arr, r, i + 1, current, result);

            // 백트래킹: 현재 선택을 취소하고 다른 경우 시도
            // 이 부분이 핵심! 재귀에서 돌아오면 선택을 되돌려야 함
            current.remove(current.size() - 1);
        }
    }

    /**
     * 반복문을 이용한 조합 생성 (비재귀 방식)
     * 작은 r값에 대해서만 실용적
     */
    private static void generateCombinationsIterative(int[] arr, int r) {
        int n = arr.length;

        // r개의 인덱스를 저장할 배열
        int[] indices = new int[r];

        // 초기값: 0, 1, 2, ..., r-1
        for (int i = 0; i < r; i++) {
            indices[i] = i;
        }

        do {
            // 현재 조합 출력
            for (int i = 0; i < r; i++) {
                System.out.print(arr[indices[i]] + " ");
            }
            System.out.println();

        } while (nextCombination(indices, n, r));
    }

    /**
     * 다음 조합으로 인덱스 배열을 업데이트
     * 사전식 순서로 다음 조합을 생성
     */
    private static boolean nextCombination(int[] indices, int n, int r) {
        // 오른쪽부터 증가 가능한 위치 찾기
        int i = r - 1;
        while (i >= 0 && indices[i] == n - r + i) {
            i--;
        }

        // 더 이상 증가할 수 없으면 끝
        if (i < 0) return false;

        // i번째 인덱스 증가
        indices[i]++;

        // i 이후의 인덱스들을 연속된 값으로 설정
        for (int j = i + 1; j < r; j++) {
            indices[j] = indices[j - 1] + 1;
        }

        return true;
    }

    /**
     * 조합의 개수만 계산하는 메서드 (실제 조합 생성 X)
     * 메모리를 절약하고 싶을 때 사용
     */
    private static long calculateCombinationCount(int n, int r) {
        if (r > n - r) r = n - r;  // nCr = nC(n-r) 이용해서 계산량 감소

        long result = 1;
        for (int i = 0; i < r; i++) {
            result = result * (n - i) / (i + 1);
            // 오버플로우 방지를 위해 곱셈 후 바로 나눗셈
        }
        return result;
    }
}

