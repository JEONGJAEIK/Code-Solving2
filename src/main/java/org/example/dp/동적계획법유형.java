package org.example.dp;

import java.util.*;

/*
* ===== 동적계획법(Dynamic Programming) 핵심 알고리즘 =====
* 
* 【기본 원리】
* - 큰 문제를 작은 부분 문제로 나누어 해결
* - 한 번 계산한 결과를 저장해서 중복 계산 방지 (메모이제이션)
* - 점화식을 통해 이전 결과를 활용하여 현재 결과 도출
* - 최적 부분 구조(Optimal Substructure)와 중복되는 부분 문제(Overlapping Subproblems) 조건 필요
* 
* 【시간복잡도】
* - 일반적으로 O(상태의 개수 × 전이당 시간)
* - 1차원 DP: O(n), 2차원 DP: O(n²), 3차원 DP: O(n³)
* - 메모이제이션으로 지수 시간을 다항 시간으로 단축
* - 공간복잡도: O(상태의 개수) 또는 슬라이딩 윈도우로 O(1)~O(k) 최적화 가능
* 
* 【선수 지식】
* - 재귀 함수의 이해 (탑다운 접근)
* - 반복문과 배열 활용 (바텀업 접근)
* - 점화식 도출 능력
* - 수학적 귀납법 개념
* - 최적화 문제의 특성 이해
* 
* 【코딩테스트 출제 유형】
* 1. 기본 수열: 피보나치, 계단 오르기, 타일링 문제
* 2. 경로 문제: 격자 경로, 최단 경로, 경로의 수
* 3. 배낭 문제: 0-1 배낭, 무한 배낭, 부분집합의 합
* 4. 최장 증가 부분 수열(LIS), 최장 공통 부분 수열(LCS)
* 5. 구간 DP: 행렬 곱셈, 팰린드롬 만들기
* 6. 비트마스킹 DP: 외판원 순회(TSP), 집합 DP
* 7. 확률 DP, 기댓값 DP
* 
* 【비슷한 알고리즘과 구분점】
* ⚠️ DP vs 분할정복
*   - DP: 부분 문제가 중복됨, 메모이제이션 필요
*   - 분할정복: 부분 문제가 독립적, 중복 없음
* 
* ⚠️ DP vs 그리디
*   - DP: 모든 경우를 고려, 최적해 보장
*   - 그리디: 매 순간 최선 선택, 항상 최적해는 아님
* 
* ⚠️ 탑다운 vs 바텀업 혼동
*   - 탑다운(메모이제이션): 재귀 + 캐시, 필요한 부분만 계산
*   - 바텀업(테뷸레이션): 반복문, 작은 것부터 순차 계산
* 
* ⚠️ 1차원 vs 2차원 DP 선택 실수
*   - 상태가 하나의 변수로 표현 가능: 1차원
*   - 두 개 이상의 변수 필요: 2차원 이상
* 
* ⚠️ 완전탐색과 혼동
*   - DP: 중복 계산 제거로 효율성 향상
*   - 완전탐색: 모든 경우 다 확인, 비효율적
*/

public class 동적계획법유형 {
    
    public static void main(String[] args) {
        // 실전 코딩테스트 방식 - 직접 입력으로 테스트
        testAllMethods();
    }
    
    private static void testAllMethods() {
        System.out.println("=== 동적계획법 알고리즘 테스트 ===");
        System.out.println("1. 피보나치 (10): " + fibonacci(10));
        System.out.println("2. 계단 오르기 (5): " + climbStairs(5));
        System.out.println("3. 배낭 문제: " + knapsack());
        System.out.println("4. LIS 길이: " + lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("5. 최소 편집 거리: " + editDistance("horse", "ros"));
        System.out.println("6. 동전 교환: " + coinChange(new int[]{1, 3, 4}, 6));
    }
    
    /**
     * 1. 피보나치 수열 - DP의 가장 기본적인 예시
     * F(n) = F(n-1) + F(n-2), F(0) = 0, F(1) = 1
     */
    public static long fibonacci(int n) {
        if (n <= 1) return n;
        
        // ★ 바텀업 방식: 작은 값부터 순차 계산
        // 공간 최적화: 전체 배열 대신 이전 두 값만 저장
        long prev2 = 0; // F(0)
        long prev1 = 1; // F(1)
        
        for (int i = 2; i <= n; i++) {
            long current = prev1 + prev2; // F(i) = F(i-1) + F(i-2)
            prev2 = prev1; // 이전 값들 업데이트
            prev1 = current;
        }
        
        return prev1;
        
        /* ★ 탑다운 방식 (메모이제이션) - 참고용
        static Map<Integer, Long> memo = new HashMap<>();
        
        public static long fibonacciMemo(int n) {
            if (n <= 1) return n;
            
            if (memo.containsKey(n)) {
                return memo.get(n); // 이미 계산한 값 재사용
            }
            
            long result = fibonacciMemo(n-1) + fibonacciMemo(n-2);
            memo.put(n, result); // 계산 결과 저장
            return result;
        }
        */
    }
    
    /**
     * 2. 계단 오르기 - 1차원 DP의 전형적 문제
     * n번째 계단에 도달하는 방법의 수 (1칸 또는 2칸씩 이동 가능)
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        
        // ★ dp[i] = i번째 계단에 도달하는 방법의 수
        // 점화식: dp[i] = dp[i-1] + dp[i-2]
        // i번째 계단은 (i-1)번째에서 1칸 또는 (i-2)번째에서 2칸으로 도달 가능
        
        int[] dp = new int[n + 1];
        dp[1] = 1; // 1번째 계단: 1가지 방법
        dp[2] = 2; // 2번째 계단: 2가지 방법 (1+1, 2)
        
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
        
        /* ★ 공간 최적화 버전
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
        */
    }
    
    /**
     * 3. 0-1 배낭 문제 - 2차원 DP의 대표적 문제
     * 무게 제한이 있는 배낭에 가치를 최대화하여 물건 담기
     */
    public static int knapsack() {
        // 테스트 데이터
        int[] weights = {1, 3, 4, 5};  // 물건의 무게
        int[] values = {1, 4, 5, 7};   // 물건의 가치
        int capacity = 7;              // 배낭 용량
        
        int n = weights.length;
        
        // ★ dp[i][w] = i번째 물건까지 고려했을 때 무게 w 이하로 얻을 수 있는 최대 가치
        int[][] dp = new int[n + 1][capacity + 1];
        
        // ★ 바텀업 방식으로 테이블 채우기
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                // ★ 현재 물건을 담을 수 없는 경우
                if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w]; // 이전 상태 그대로
                } else {
                    // ★ 현재 물건을 담는 경우 vs 담지 않는 경우 중 최대값
                    int includeItem = dp[i - 1][w - weights[i - 1]] + values[i - 1];
                    int excludeItem = dp[i - 1][w];
                    dp[i][w] = Math.max(includeItem, excludeItem);
                }
            }
        }
        
        return dp[n][capacity];
        
        /* ★ 공간 최적화 버전 (1차원 배열 사용)
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < n; i++) {
            // 뒤에서부터 순회해야 이전 값이 덮어씌워지지 않음
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        return dp[capacity];
        */
    }
    
    /**
     * 4. 최장 증가 부분 수열(LIS) - O(n²) 기본 DP 버전
     * 주어진 수열에서 증가하는 부분 수열의 최대 길이
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int n = nums.length;
        // ★ dp[i] = i번째 원소를 마지막으로 하는 LIS의 길이
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 각 원소 자체로 길이 1인 수열 형성
        
        int maxLength = 1;
        
        // ★ 각 위치에서 이전 모든 위치 확인
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // ★ nums[i]가 nums[j]보다 크면 수열 연장 가능
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
        
        /* ★ O(n log n) 이진 탐색 버전 - 더 효율적
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int pos = Collections.binarySearch(tails, num);
            if (pos < 0) pos = -(pos + 1); // 삽입 위치
            
            if (pos == tails.size()) {
                tails.add(num);
            } else {
                tails.set(pos, num);
            }
        }
        
        return tails.size();
        */
    }
    
    /**
     * 5. 편집 거리(Edit Distance) - 2차원 DP 응용
     * 한 문자열을 다른 문자열로 변환하는 최소 연산 횟수
     */
    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // ★ dp[i][j] = word1[0..i-1]을 word2[0..j-1]로 변환하는 최소 연산 수
        int[][] dp = new int[m + 1][n + 1];
        
        // ★ 초기화: 빈 문자열로부터의 변환
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // word1[0..i-1]을 빈 문자열로: i번 삭제
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 빈 문자열을 word2[0..j-1]로: j번 삽입
        }
        
        // ★ DP 테이블 채우기
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // ★ 문자가 같으면 변환 불필요
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // ★ 문자가 다르면 3가지 연산 중 최소값
                    int insert = dp[i][j - 1] + 1;    // 삽입
                    int delete = dp[i - 1][j] + 1;    // 삭제
                    int replace = dp[i - 1][j - 1] + 1; // 교체
                    
                    dp[i][j] = Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 6. 동전 교환 문제 - 무한 배낭 문제의 변형
     * 주어진 금액을 만들기 위한 최소 동전 개수
     */
    public static int coinChange(int[] coins, int amount) {
        // ★ dp[i] = 금액 i를 만들기 위한 최소 동전 개수
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 불가능한 큰 값으로 초기화
        dp[0] = 0; // 0원을 만드는데 필요한 동전 개수는 0개
        
        // ★ 각 금액에 대해 최소 동전 개수 계산
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // ★ 현재 동전을 사용하는 경우
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * 7. 최장 공통 부분 수열(LCS) - 2차원 DP 클래식
     * 두 문자열의 가장 긴 공통 부분 수열의 길이
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // ★ dp[i][j] = text1[0..i-1]과 text2[0..j-1]의 LCS 길이
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // ★ 문자가 같으면 LCS 길이 1 증가
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // ★ 문자가 다르면 각각 한 문자씩 제외한 경우 중 최대값
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 8. 집합 DP (비트마스킹) - 외판원 순회 문제 (TSP)
     * 모든 도시를 방문하고 시작점으로 돌아오는 최소 비용
     */
    public static int tsp() {
        // 테스트 데이터: 4개 도시 간 거리
        int[][] dist = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        
        int n = dist.length;
        // ★ dp[mask][i] = mask로 표현된 도시들을 방문하고 현재 i번 도시에 있을 때의 최소 비용
        int[][] dp = new int[1 << n][n];
        
        // ★ 초기화: 불가능한 상태는 무한대로 설정
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        dp[1][0] = 0; // 시작점(0번 도시)에서 출발
        
        // ★ 모든 부분집합에 대해 DP 수행
        for (int mask = 0; mask < (1 << n); mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0 || dp[mask][u] == Integer.MAX_VALUE) {
                    continue; // u번 도시를 방문하지 않았거나 불가능한 상태
                }
                
                // ★ 다음 도시로 이동
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) != 0) continue; // 이미 방문한 도시
                    
                    int newMask = mask | (1 << v); // v번 도시 방문 표시
                    dp[newMask][v] = Math.min(dp[newMask][v], 
                                            dp[mask][u] + dist[u][v]);
                }
            }
        }
        
        // ★ 모든 도시를 방문하고 시작점으로 돌아가는 최소 비용
        int result = Integer.MAX_VALUE;
        int allVisited = (1 << n) - 1;
        
        for (int i = 1; i < n; i++) {
            if (dp[allVisited][i] != Integer.MAX_VALUE) {
                result = Math.min(result, dp[allVisited][i] + dist[i][0]);
            }
        }
        
        return result;
    }
    
    /**
     * 9. 구간 DP - 팰린드롬 만들기
     * 문자열을 팰린드롬으로 만들기 위한 최소 삽입 횟수
     */
    public static int minInsertionsForPalindrome(String s) {
        int n = s.length();
        // ★ dp[i][j] = s[i..j] 구간을 팰린드롬으로 만드는 최소 삽입 횟수
        int[][] dp = new int[n][n];
        
        // ★ 길이별로 구간 DP 수행
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                if (s.charAt(i) == s.charAt(j)) {
                    // ★ 양 끝 문자가 같으면 내부 구간만 고려
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    // ★ 양 끝 문자가 다르면 한쪽에 문자 삽입
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        
        return dp[0][n - 1];
    }
    
    /**
     * 10. 확률 DP - 주사위 게임
     * n번 주사위를 던져서 합이 target이 될 확률
     */
    public static double diceGameProbability(int n, int faces, int target) {
        // ★ dp[i][j] = i번 던져서 합이 j가 될 경우의 수
        double[][] dp = new double[n + 1][target + 1];
        dp[0][0] = 1; // 0번 던져서 합이 0: 1가지 경우
        
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= target && j <= i * faces; j++) {
                // ★ 현재 던진 주사위의 모든 가능한 값 고려
                for (int k = 1; k <= faces && k <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        
        // ★ 전체 경우의 수로 나누어 확률 계산
        double totalCases = Math.pow(faces, n);
        return dp[n][target] / totalCases;
    }
}