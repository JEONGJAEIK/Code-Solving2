package org.example;

import java.util.Arrays;

import java.util.*;

/*
===============================================================================
순열(Permutation) 알고리즘 - 코딩테스트 필수 알고리즘
===============================================================================

【알고리즘 기본 개념】
- 순열: n개의 서로 다른 원소에서 r개를 선택하여 순서를 고려해 나열하는 것
- 공식: P(n,r) = n!/(n-r)!
- 예: [1,2,3]에서 2개 선택 → (1,2), (1,3), (2,1), (2,3), (3,1), (3,2)

【시간복잡도】
- 전체 순열: O(n! × n) - n개 원소의 모든 순열 생성
- 부분 순열: O(n!/(n-r)! × r) - r개 선택하는 순열
- 공간복잡도: O(n) - 재귀 스택과 방문 배열

【사전 필수 지식】
1. 재귀 함수의 동작 원리와 백트래킹
2. 배열과 리스트 조작
3. 방문 체크를 위한 boolean 배열 사용법

【코딩테스트 출제 유형】
1. 순열 직접 생성: "모든 경우의 수 나열", "순서가 중요한 조합 문제"
2. 최적화 문제: "외판원 순회(TSP)", "작업 스케줄링"
3. 게임/퍼즐: "숫자 맞추기", "카드 배치"
4. 문자열: "애너그램", "단어 순서 바꾸기"
5. 그래프: "모든 경로 탐색", "방문 순서 최적화"

【유사 알고리즘과의 구분】
⚠️ 조합(Combination)과 혼동 주의!
- 순열: 순서 중요 O, [1,2] ≠ [2,1]
- 조합: 순서 중요 X, [1,2] = [2,1]

⚠️ 중복순열과 구분!
- 일반순열: 같은 원소 중복 선택 불가
- 중복순열: 같은 원소 중복 선택 가능

⚠️ DFS와 혼동하지 말 것!
- DFS: 그래프 탐색이 목적
- 순열: 모든 경우의 수 생성이 목적

【실전 적용 팁】
- 문제에서 "모든 경우", "순서가 중요한" 키워드 발견시 순열 고려
- n이 8 이하일 때 전체 순열 가능 (8! = 40,320)
- n이 클 때는 가지치기나 다른 알고리즘 고려 필요
===============================================================================
*/

public class 순열 {

    public static void main(String[] args) {
        // 직접 입력으로 테스트 (코딩테스트 실전 방식)
        int[] arr = {1, 2, 3, 4};  // 원본 배열
        int r = 3;  // 선택할 개수

        List<List<Integer>> result = new ArrayList<>();

        // 전체 순열 생성
        System.out.println("=== 전체 순열 (4개 원소) ===");
        generateFullPermutation(arr, new ArrayList<>(), new boolean[arr.length], result);
        printResult(result);

        // 부분 순열 생성 (4개 중 3개 선택)
        System.out.println("\n=== 부분 순열 (4개 중 3개 선택) ===");
        result.clear();
        generatePartialPermutation(arr, r, new ArrayList<>(), new boolean[arr.length], result);
        printResult(result);

        // 실전 활용 예시: 최소값 찾기
        System.out.println("\n=== 실전 예시: 순열 중 최소 합 구하기 ===");
        int[][] cost = {{0,1,2,3}, {1,0,4,2}, {2,4,0,1}, {3,2,1,0}};  // 비용 행렬
        int minCost = findMinCostPath(cost);
        System.out.println("최소 비용: " + minCost);
    }

    /**
     * 전체 순열 생성 (모든 원소 사용)
     * @param arr 원본 배열
     * @param current 현재까지 만든 순열
     * @param visited 방문 체크 배열
     * @param result 결과 저장소
     */
    public static void generateFullPermutation(int[] arr, List<Integer> current,
                                               boolean[] visited, List<List<Integer>> result) {

        // 기저 조건: 모든 원소를 다 선택했을 때
        if (current.size() == arr.length) {
            result.add(new ArrayList<>(current));  // 깊은 복사로 결과 저장
            return;
        }

        // 각 원소에 대해 시도 (핵심: 방문하지 않은 원소만 선택)
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {  // 아직 사용하지 않은 원소라면

                // 선택: 현재 원소를 순열에 추가
                current.add(arr[i]);
                visited[i] = true;  // 방문 표시

                // 재귀: 다음 위치 채우기 (백트래킹의 핵심)
                generateFullPermutation(arr, current, visited, result);

                // 복원: 백트래킹으로 이전 상태로 되돌리기
                current.remove(current.size() - 1);  // 마지막 원소 제거
                visited[i] = false;  // 방문 표시 해제
            }
        }
    }

    /**
     * 부분 순열 생성 (r개만 선택)
     * 전체 순열과 거의 동일하지만 기저 조건만 다름
     */
    public static void generatePartialPermutation(int[] arr, int r, List<Integer> current,
                                                  boolean[] visited, List<List<Integer>> result) {

        // 기저 조건: r개를 다 선택했을 때 (전체 순열과의 차이점)
        if (current.size() == r) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 나머지 로직은 전체 순열과 동일
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                current.add(arr[i]);
                visited[i] = true;

                generatePartialPermutation(arr, r, current, visited, result);

                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 실전 활용 예시: 외판원 순회 문제 (TSP) 간소화 버전
     * 모든 도시를 순회하는 최소 비용 경로 찾기
     *
     * 핵심 아이디어:
     * 1. 시작점을 0으로 고정하여 순열 수를 줄임 (최적화)
     * 2. 각 순열에 대해 총 비용을 계산
     * 3. 최소 비용을 추적
     */
    public static int findMinCostPath(int[][] cost) {
        int n = cost.length;
        int[] cities = new int[n-1];  // 0번 도시는 시작점으로 고정

        // 1~n-1 도시들을 배열에 저장 (0번 제외)
        for (int i = 0; i < n-1; i++) {
            cities[i] = i + 1;
        }

        int minCost = Integer.MAX_VALUE;

        // 1~n-1 도시의 모든 순열에 대해 비용 계산
        List<List<Integer>> routes = new ArrayList<>();
        generateFullPermutation(cities, new ArrayList<>(), new boolean[cities.length], routes);

        for (List<Integer> route : routes) {
            int currentCost = 0;
            int prev = 0;  // 시작점은 항상 0번 도시

            // 경로를 따라 이동하며 비용 누적
            for (int city : route) {
                currentCost += cost[prev][city];
                prev = city;
            }

            // 마지막 도시에서 시작점으로 돌아가는 비용
            currentCost += cost[prev][0];

            // 최소 비용 갱신
            minCost = Math.min(minCost, currentCost);
        }

        return minCost;
    }

    /**
     * 결과 출력 헬퍼 메서드 (실전에서는 보통 필요 없음)
     * 코딩테스트에서는 결과를 직접 처리하는 경우가 대부분
     */
    private static void printResult(List<List<Integer>> result) {
        System.out.println("총 " + result.size() + "개의 순열:");
        for (int i = 0; i < Math.min(result.size(), 10); i++) {  // 처음 10개만 출력
            System.out.println(result.get(i));
        }
        if (result.size() > 10) {
            System.out.println("... 및 " + (result.size() - 10) + "개 더");
        }
    }
}