package org.example;

import java.util.*;

/*
 * ===== LIS (Longest Increasing Subsequence) - 최장 증가 부분수열 =====
 *
 * 알고리즘 소개:
 * - 주어진 수열에서 원소들이 증가하는 순서로 배열된 가장 긴 부분수열을 찾는 알고리즘
 * - 부분수열: 원래 순서는 유지하되 연속될 필요 없는 원소들의 나열
 * - 예: [10, 9, 2, 5, 3, 7, 101, 18]에서 LIS는 [2, 3, 7, 101] (길이 4)
 * - 두 가지 접근법: DP O(N²)와 이분탐색 O(N log N)
 *
 * 시간복잡도: 
 * - DP 방식: O(N²) - 모든 쌍을 비교
 * - 이분탐색 방식: O(N log N) - 각 원소마다 이분탐색 수행
 * 공간복잡도: O(N) - DP 배열 또는 LIS 배열 사용
 *
 * 선행 지식:
 * 1. 동적계획법(DP)의 기본 개념과 점화식 설계
 * 2. 이분탐색(Binary Search) 알고리즘과 lower_bound 개념
 * 3. 배열과 리스트 자료구조 활용
 * 4. 부분수열의 개념 (연속하지 않아도 됨)
 *
 * ===== 코딩테스트 출제 유형 =====
 *
 * 1. 기본 LIS 길이 구하기 (가장 빈출)
 *    - 단순히 가장 긴 증가하는 부분수열의 길이만 구하는 문제
 *    - 보통 실버2~골드3 난이도
 *
 * 2. LIS 개수 구하기
 *    - 가장 긴 길이를 갖는 LIS가 몇 개인지 구하는 문제
 *    - 조합론과 DP가 결합된 고난도 문제
 *
 * 3. LIS 복원하기 (실제 수열 구하기)
 *    - 길이뿐만 아니라 실제 LIS 수열도 출력
 *    - 역추적 또는 parent 배열 활용
 *
 * 4. 변형된 LIS 문제들
 *    - 감소하는 부분수열 (LDS - Longest Decreasing Subsequence)
 *    - 이차원 좌표에서의 LIS (정렬 후 LIS 적용)
 *    - 순환 배열에서의 LIS
 *
 * 5. LIS + 다른 알고리즘 결합
 *    - LIS + 그리디: 활동 선택 문제의 변형
 *    - LIS + 좌표압축: 매우 큰 수가 포함된 경우
 *
 * 6. 실제 응용 문제들
 *    - 상자 쌓기 문제 (3차원 정렬 후 LIS)
 *    - 전깃줄 문제 (교차점 최소화)
 *    - 계단 오르기의 변형
 *
 * ===== 비슷한 알고리즘과의 구분 및 주의사항 =====
 *
 * vs LCS (Longest Common Subsequence - 최장 공통 부분수열):
 * - LIS: 하나의 수열에서 증가하는 가장 긴 부분수열
 * - LCS: 두 수열에서 공통인 가장 긴 부분수열  
 * - 착각 포인트: "증가" 키워드가 있으면 LIS, "공통" 키워드가 있으면 LCS
 * - 구별 팁: 입력이 수열 1개면 LIS, 수열 2개면 LCS 가능성 높음
 *
 * vs 최장 연속 증가 부분수열:
 * - LIS: 연속되지 않아도 됨 ([1,3,2,4]에서 [1,3,4] 가능)
 * - 연속 증가: 반드시 연속되어야 함 ([1,2,3,4] 형태만 가능)
 * - 착각 포인트: 문제에서 "연속" 키워드 확인 필수
 * - 연속이면 O(N) 투 포인터, 비연속이면 LIS 알고리즘
 *
 * vs 그리디 알고리즘 (활동 선택 문제):
 * - 활동 선택: 끝나는 시간 기준 정렬 후 그리디
 * - LIS: 전체 수열을 고려한 DP 또는 이분탐색
 * - 구별: 시간 구간 문제면 그리디, 단순 수열 문제면 LIS
 *
 * vs 정렬 알고리즘:
 * - 정렬: 전체 배열의 순서를 바꿈
 * - LIS: 원래 순서 유지하면서 부분수열만 선택
 * - 착각 포인트: LIS는 원본 순서를 절대 바꾸면 안됨
 *
 * 올바른 알고리즘 선택 기준:
 * - 하나의 수열 + 증가 순서 + 부분수열 → LIS
 * - 두 수열 + 공통 부분 → LCS
 * - 연속된 증가 → 투 포인터
 * - 시간 구간 최적화 → 그리디
 * - 전체 정렬 → 정렬 알고리즘
 */

public class LIS {
    
    public static void main(String[] args) {
        // 테스트 케이스들 - 실제 코딩테스트에서는 이 부분만 수정
        
        // 기본 예시
        int[] arr1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("배열: " + Arrays.toString(arr1));
        System.out.println("LIS 길이 (DP방식): " + getLISLengthDP(arr1));
        System.out.println("LIS 길이 (이분탐색): " + getLISLengthBinarySearch(arr1));
        System.out.println("LIS 수열: " + Arrays.toString(getLISSequence(arr1)));
        System.out.println();
        
        // 코딩테스트 빈출 예시
        int[] arr2 = {1, 3, 6, 4, 9, 5, 2, 8, 7};
        System.out.println("배열: " + Arrays.toString(arr2));
        System.out.println("LIS 길이 (DP방식): " + getLISLengthDP(arr2));
        System.out.println("LIS 길이 (이분탐색): " + getLISLengthBinarySearch(arr2));
        System.out.println("LIS 수열: " + Arrays.toString(getLISSequence(arr2)));
        System.out.println();
        
        // 단조증가 수열
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("배열: " + Arrays.toString(arr3));
        System.out.println("LIS 길이: " + getLISLengthBinarySearch(arr3));
        System.out.println();
        
        // 단조감소 수열
        int[] arr4 = {5, 4, 3, 2, 1};
        System.out.println("배열: " + Arrays.toString(arr4));
        System.out.println("LIS 길이: " + getLISLengthBinarySearch(arr4));
    }
    
    /**
     * DP 방식으로 LIS 길이 구하기 - 기본적이지만 직관적
     * 시간복잡도: O(N²), 공간복잡도: O(N)
     * 작은 데이터(N ≤ 1000)에서 사용하거나 개념 이해용
     */
    public static int getLISLengthDP(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        
        // ★ dp[i] = i번째 원소를 마지막으로 하는 LIS의 길이
        // 이 정의가 핵심: "i번째 원소를 반드시 포함하는 가장 긴 증가수열"
        int[] dp = new int[n];
        
        // ★ 기저 조건: 모든 원소는 혼자서 길이 1의 LIS를 만들 수 있음
        Arrays.fill(dp, 1);
        
        // ★ DP 점화식 구성 - 이 부분이 LIS의 핵심 로직
        for (int i = 1; i < n; i++) {
            // i번째 원소보다 앞에 있으면서 값이 작은 원소들을 모두 확인
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    // ★ 핵심 아이디어: j번째 원소 뒤에 i번째 원소를 붙이는 경우
                    // 왜 dp[j] + 1인가?
                    // - j번째까지의 최대 길이(dp[j])에 현재 원소(i) 하나를 추가
                    // - arr[j] < arr[i]이므로 증가 조건을 만족
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        // ★ 전체 배열에서 가장 큰 dp 값이 LIS의 길이
        // 왜 전체 최대값을 구하는가?
        // - dp[i]는 "i번째 원소로 끝나는" LIS 길이
        // - 실제 LIS는 어떤 위치에서 끝날지 모르므로 모든 위치 중 최대값
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    /**
     * 이분탐색 방식으로 LIS 길이 구하기 - 효율적이고 실전용
     * 시간복잡도: O(N log N), 공간복잡도: O(N)
     * 큰 데이터(N ≤ 100,000)에서 주로 사용
     * 코딩테스트에서 가장 선호되는 방식
     */
    public static int getLISLengthBinarySearch(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        
        // ★ lis 배열의 의미 (매우 중요하고 직관적이지 않은 부분!):
        // lis[i] = "길이가 i+1인 증가수열의 마지막 원소 중 가장 작은 값"
        // 
        // 예시로 이해하기:
        // 배열 [1, 3, 6, 4, 9]를 처리하면
        // - 길이 1인 증가수열들: [1], [3], [6], [4], [9] → 최소 마지막 원소: 1
        // - 길이 2인 증가수열들: [1,3], [1,6], [1,4], [1,9], [3,6], [3,4], [3,9], [4,9] → 최소 마지막 원소: 3
        // - 길이 3인 증가수열들: [1,3,6], [1,3,4], [1,3,9], [1,4,9] → 최소 마지막 원소: 4
        // 
        // 왜 "가장 작은 값"을 저장하는가?
        // → 나중에 더 긴 수열을 만들 가능성을 최대한 열어두기 위해서!
        List<Integer> lis = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            
            // ★ 이분탐색으로 current가 들어갈 위치 찾기
            // lower_bound: current보다 크거나 같은 첫 번째 원소의 위치
            int pos = Collections.binarySearch(lis, current);
            
            if (pos < 0) {
                // ★ binarySearch가 음수를 반환 = 정확히 일치하는 값이 없음
                // 음수의 의미: -(insertion point) - 1
                // insertion point = current가 들어가야 할 위치
                pos = -(pos + 1);
            }
            
            if (pos == lis.size()) {
                // ★ current가 lis의 모든 원소보다 큰 경우
                // → LIS를 확장할 수 있음 (길이가 1 증가)
                lis.add(current);
            } else {
                // ★ current로 기존 원소를 교체
                // 왜 교체하는가?
                // → 같은 길이의 증가수열이지만 마지막 원소가 더 작아짐
                // → 이후에 더 많은 원소들을 추가할 수 있는 가능성 증가
                lis.set(pos, current);
            }
        }
        
        return lis.size();
    }
    
    /**
     * 실제 LIS 수열을 구하는 메서드 (역추적 사용)
     * DP 방식을 기반으로 parent 배열을 활용하여 실제 수열 복원
     */
    public static int[] getLISSequence(int[] arr) {
        int n = arr.length;
        if (n == 0) return new int[0];
        
        // DP 배열과 부모 추적 배열
        int[] dp = new int[n];
        int[] parent = new int[n]; // parent[i] = i번째 원소의 LIS에서 이전 원소의 인덱스
        
        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1); // -1은 이전 원소가 없음을 의미
        
        // DP로 LIS 길이 계산하면서 부모 정보도 저장
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j; // j번째 원소 다음에 i번째 원소가 온다
                }
            }
        }
        
        // LIS의 마지막 원소 찾기
        int maxLength = 0;
        int lastIndex = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        
        // ★ 역추적으로 LIS 복원
        // parent 배열을 따라가면서 LIS에 포함된 원소들을 찾아냄
        int[] result = new int[maxLength];
        int current = lastIndex;
        
        // 뒤에서부터 채우기 (역추적이므로)
        for (int i = maxLength - 1; i >= 0; i--) {
            result[i] = arr[current];
            current = parent[current]; // 이전 원소로 이동
        }
        
        return result;
    }
    
    /**
     * LIS 개수 구하기 (고급 문제용)
     * 가장 긴 길이를 갖는 LIS가 몇 개인지 계산
     */
    public static int getLISCount(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        
        int[] dp = new int[n];      // dp[i] = i번째 원소로 끝나는 LIS의 길이
        int[] count = new int[n];   // count[i] = i번째 원소로 끝나는 LIS의 개수
        
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        // ★ 더 긴 LIS를 발견한 경우
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // j의 개수를 그대로 가져옴
                    } else if (dp[j] + 1 == dp[i]) {
                        // ★ 같은 길이의 LIS를 또 발견한 경우
                        count[i] += count[j]; // 개수를 누적
                    }
                }
            }
        }
        
        // 최대 길이 찾기
        int maxLength = Arrays.stream(dp).max().orElse(0);
        
        // 최대 길이를 갖는 LIS의 총 개수 계산
        int totalCount = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLength) {
                totalCount += count[i];
            }
        }
        
        return totalCount;
    }
}

/*
 * ===== LIS 알고리즘의 핵심 이해하기 =====
 *
 * 1. DP 방식의 직관:
 *    "각 위치에서 그 원소로 끝나는 가장 긴 증가수열의 길이는?"
 *    → 이전의 더 작은 원소들 중에서 가장 긴 수열에 현재 원소를 붙임
 *
 * 2. 이분탐색 방식의 직관:
 *    "같은 길이라면 마지막 원소가 작을수록 나중에 확장하기 유리하다"
 *    → 항상 각 길이별로 최소 마지막 원소만 기억함
 *
 * 3. 왜 이분탐색이 더 빠른가?
 *    - DP: 모든 이전 원소들과 비교 → O(N²)
 *    - 이분탐색: 정렬된 배열에서 위치 찾기 → O(log N)
 *
 * 4. lis 배열이 실제 LIS가 아닌 이유:
 *    - lis 배열은 "각 길이별 최적의 마지막 원소" 저장
 *    - 실제 LIS는 이 정보만으로는 복원 불가
 *    - 복원하려면 추가 정보(parent 배열 등) 필요
 *
 * ===== 실전 팁 =====
 *
 * 1. 데이터 크기별 방법 선택:
 *    - N ≤ 1,000: DP 방식 (구현 간단)
 *    - N ≤ 100,000: 이분탐색 방식 (효율적)
 *
 * 2. 문제 유형별 접근:
 *    - 길이만 필요: 이분탐색 방식
 *    - 실제 수열 필요: DP + 역추적
 *    - 개수 필요: DP + 카운팅
 *
 * 3. 주의사항:
 *    - "증가"의 정의 확인 (초과 vs 이상)
 *    - 빈 배열이나 단일 원소 처리
 *    - 정수 범위와 오버플로우 주의
 *
 * 4. 변형 문제 대응:
 *    - 감소 수열: 배열을 뒤집거나 부호 변경
 *    - 2차원 좌표: 한 축으로 정렬 후 다른 축으로 LIS
 *    - 순환 배열: 배열을 두 번 이어붙인 후 길이 제한
 */