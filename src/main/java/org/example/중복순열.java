package org.example;

import java.util.Arrays;
import java.util.*;

/*
===============================================================================
중복순열(Permutation with Repetition) 알고리즘 - 코딩테스트 필수 알고리즘
===============================================================================

【알고리즘 기본 개념】
- 중복순열: n개의 원소에서 중복을 허용하여 r개를 선택해 순서를 고려해 나열하는 것
- 공식: n^r (각 위치마다 n개의 선택지가 있음)
- 예: [1,2,3]에서 중복허용 2개 선택 → (1,1), (1,2), (1,3), (2,1), (2,2), (2,3), (3,1), (3,2), (3,3)

【일반 순열과의 핵심 차이점】
- 일반 순열: 같은 원소를 중복해서 선택할 수 없음 (visited 배열 사용)
- 중복순열: 같은 원소를 중복해서 선택할 수 있음 (visited 배열 불필요!)

【시간복잡도】
- 중복순열: O(n^r × r) - 각 위치마다 n개 선택지, r개 위치, 각 순열 복사에 r시간
- 일반순열: O(n!/(n-r)! × r) - 선택할수록 선택지가 줄어듦
- 공간복잡도: O(r) - 재귀 스택 깊이 (visited 배열 불필요!)

【사전 필수 지식】
1. 재귀 함수의 동작 원리와 백트래킹
2. 배열과 리스트 조작
3. 일반 순열과의 차이점 이해
4. 지수 함수의 시간복잡도 개념 (n^r은 매우 빠르게 증가!)

【코딩테스트 출제 유형】
1. 암호/비밀번호 생성: "4자리 숫자 조합", "특정 조건 만족하는 코드"
2. 게임/주사위: "주사위 n번 던져서 합이 k", "동전 던지기 시퀀스"
3. 문자열 생성: "길이 n인 특정 패턴 문자열", "중복 허용 단어 만들기"
4. DP 전처리: "모든 경우의 수를 미리 계산", "메모이제이션 키 생성"
5. 브루트포스: "모든 가능한 입력 조합 시도", "완전탐색 최적화"

【유사 알고리즘과의 구분】
⚠️ 일반 순열과 혼동 주의!
- 일반순열: visited 배열 필요, P(n,r) = n!/(n-r)!
- 중복순열: visited 배열 불필요, n^r

⚠️ 중복조합과 혼동 주의!
- 중복순열: 순서 중요 O, (1,2) ≠ (2,1)
- 중복조합: 순서 중요 X, (1,2) = (2,1)

⚠️ 단순 반복문과 혼동하지 말 것!
- 반복문: 단순 계산 반복
- 중복순열: 모든 가능한 조합을 체계적으로 생성

【실전 적용 팁】
- 문제에서 "중복 허용", "같은 것을 여러 번" 키워드 발견시 중복순열 고려
- r이 5 이하일 때 대부분 실행 가능 (n=10, r=5면 10^5 = 100,000)
- n^r이 10^6을 넘으면 다른 알고리즘이나 최적화 고려 필요
- 중복순열은 visited 배열이 없어서 일반순열보다 구현이 더 간단!
===============================================================================
*/

public class 중복순열 {

    public static void main(String[] args) {
        // 직접 입력으로 테스트 (코딩테스트 실전 방식)
        int[] arr = {1, 2, 3};  // 원본 배열 (중복 선택 가능한 원소들)
        int r = 3;  // 선택할 개수

        List<List<Integer>> result = new ArrayList<>();

        // 중복순열 생성 및 시각화
        System.out.println("=== 중복순열 생성 과정 시각화 ===");
        System.out.println("원소: " + Arrays.toString(arr));
        System.out.println("선택 개수: " + r + "개");
        System.out.println("예상 결과 수: " + Math.pow(arr.length, r) + "개 (" + arr.length + "^" + r + ")");
        System.out.println();

        // 단계별 생성 과정을 보여주는 중복순열
        generatePermutationWithVisualization(arr, r, new ArrayList<>(), result, 0);

        System.out.println("\n=== 최종 결과 ===");
        printResult(result);

        // 실전 활용 예시 1: 특정 합을 만드는 경우의 수
        System.out.println("\n=== 실전 예시 1: 합이 6인 3자리 조합 개수 ===");
        int targetSum = 6;
        int count = countCombinationsWithSum(arr, r, targetSum);
        System.out.println("합이 " + targetSum + "인 조합 개수: " + count + "개");

        // 실전 활용 예시 2: 비밀번호 생성
        System.out.println("\n=== 실전 예시 2: 4자리 숫자 비밀번호 중 조건 만족 개수 ===");
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int passwordLength = 4;
        int validPasswords = countValidPasswords(digits, passwordLength);
        System.out.println("첫자리가 0이 아닌 4자리 비밀번호 개수: " + validPasswords + "개");
    }

    /**
     * 중복순열 생성 (핵심 알고리즘)
     * 일반 순열과 달리 visited 배열이 필요 없음!
     *
     * @param arr 원본 배열 (중복 선택 가능한 원소들)
     * @param r 선택할 개수
     * @param current 현재까지 만든 순열
     * @param result 결과 저장소
     */
    public static void generatePermutationWithRepetition(int[] arr, int r,
                                                         List<Integer> current, List<List<Integer>> result) {

        // 기저 조건: r개를 다 선택했을 때
        if (current.size() == r) {
            result.add(new ArrayList<>(current));  // 깊은 복사로 결과 저장
            return;
        }

        // 핵심: 모든 원소에 대해 매번 시도 가능! (visited 체크 없음)
        // 이것이 일반 순열과의 가장 큰 차이점
        for (int i = 0; i < arr.length; i++) {
            // 선택: 현재 원소를 순열에 추가 (중복 허용이므로 조건 없음)
            current.add(arr[i]);

            // 재귀: 다음 위치 채우기 (백트래킹의 핵심)
            // 여기서 visited 배열을 전달하지 않음 = 중복 허용의 핵심!
            generatePermutationWithRepetition(arr, r, current, result);

            // 복원: 백트래킹으로 이전 상태로 되돌리기
            current.remove(current.size() - 1);  // 마지막 원소 제거
            // visited[i] = false; <- 이 줄이 없음! 중복순열의 특징
        }
    }

    /**
     * 생성 과정을 시각화하는 중복순열 (교육용 + 디버깅용)
     * 실제 코딩테스트에서는 위의 간단한 버전을 사용
     */
    public static void generatePermutationWithVisualization(int[] arr, int r,
                                                            List<Integer> current, List<List<Integer>> result, int depth) {

        // 들여쓰기로 재귀 깊이 표현
        String indent = "  ".repeat(depth);

        // 현재 상태 출력
        if (current.size() < r) {
            System.out.println(indent + "깊이 " + depth + ": 현재 순열 = " + current +
                    ", 남은 선택 = " + (r - current.size()) + "개");
        }

        // 기저 조건: r개를 다 선택했을 때
        if (current.size() == r) {
            result.add(new ArrayList<>(current));
            System.out.println(indent + "✓ 완성된 순열: " + current);
            return;
        }

        // 각 원소에 대해 시도 (중복 허용)
        for (int i = 0; i < arr.length; i++) {
            System.out.println(indent + "→ " + arr[i] + " 선택 시도");

            current.add(arr[i]);
            generatePermutationWithVisualization(arr, r, current, result, depth + 1);
            current.remove(current.size() - 1);

            System.out.println(indent + "← " + arr[i] + " 선택 취소 (백트래킹)");
        }
    }

    /**
     * 실전 활용 예시 1: 특정 합을 만드는 중복순열 개수 세기
     * 동적계획법과 결합하여 자주 사용되는 패턴
     *
     * 핵심 아이디어:
     * 1. 모든 중복순열을 생성하면서
     * 2. 각 순열의 합을 계산하고
     * 3. 목표 합과 일치하는 경우만 카운트
     */
    public static int countCombinationsWithSum(int[] arr, int r, int targetSum) {
        // 결과를 저장할 리스트 대신 카운터 사용 (메모리 최적화)
        return countCombinationsHelper(arr, r, targetSum, 0, 0);
    }

    /**
     * 재귀적으로 합을 계산하며 카운트하는 헬퍼 메서드
     * 실제 순열을 생성하지 않고 조건만 체크하여 효율적
     */
    private static int countCombinationsHelper(int[] arr, int r, int targetSum, int currentSum, int depth) {
        // 기저 조건 1: 길이를 다 채웠을 때
        if (depth == r) {
            return currentSum == targetSum ? 1 : 0;  // 합이 일치하면 1, 아니면 0
        }

        // 기저 조건 2: 가지치기 - 이미 목표 합을 초과했을 때 (최적화)
        if (currentSum > targetSum) {
            return 0;  // 더 이상 진행할 필요 없음
        }

        int count = 0;
        // 모든 원소에 대해 시도 (중복 허용)
        for (int i = 0; i < arr.length; i++) {
            // 현재 원소를 선택했을 때의 카운트를 누적
            count += countCombinationsHelper(arr, r, targetSum, currentSum + arr[i], depth + 1);
        }

        return count;
    }

    /**
     * 실전 활용 예시 2: 조건을 만족하는 비밀번호 개수 (중복순열 + 조건)
     * 첫 자리가 0이 아닌 4자리 숫자의 개수
     *
     * 핵심 아이디어:
     * 1. 첫 자리: 1~9 중 선택 (9가지)
     * 2. 나머지 자리: 0~9 중 선택 (각각 10가지)
     * 3. 총 경우의 수: 9 × 10^3 = 9,000가지
     */
    public static int countValidPasswords(int[] digits, int length) {
        return countValidPasswordsHelper(digits, length, 0, true);
    }

    /**
     * 조건부 중복순열 생성 헬퍼 메서드
     * @param digits 사용 가능한 숫자들
     * @param length 비밀번호 길이
     * @param currentPos 현재 채우고 있는 위치
     * @param isFirstDigit 첫 번째 자리인지 여부
     */
    private static int countValidPasswordsHelper(int[] digits, int length, int currentPos, boolean isFirstDigit) {
        // 기저 조건: 모든 자리를 다 채웠을 때
        if (currentPos == length) {
            return 1;  // 유효한 비밀번호 하나 완성
        }

        int count = 0;
        for (int digit : digits) {
            // 첫 자리가 0인 경우는 제외 (조건)
            if (isFirstDigit && digit == 0) {
                continue;  // 첫 자리에는 0을 사용할 수 없음
            }

            // 다음 자리로 재귀 진행
            count += countValidPasswordsHelper(digits, length, currentPos + 1, false);
        }

        return count;
    }

    /**
     * 결과 출력 헬퍼 메서드
     * 실전에서는 보통 결과의 개수나 특정 조건만 확인
     */
    private static void printResult(List<List<Integer>> result) {
        System.out.println("총 " + result.size() + "개의 중복순열:");

        // 결과가 많을 때는 일부만 출력 (실전에서는 보통 개수만 중요)
        int printLimit = Math.min(result.size(), 15);
        for (int i = 0; i < printLimit; i++) {
            System.out.println((i + 1) + ": " + result.get(i));
        }

        if (result.size() > printLimit) {
            System.out.println("... 및 " + (result.size() - printLimit) + "개 더");
        }

        System.out.println("검증: 3^3 = " + Math.pow(3, 3) + " (예상값과 일치하는지 확인)");
    }
}