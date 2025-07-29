package org.example.dp;

import java.util.*;

/*
 * ===== 동적계획법(DP) 문제 판단과 접근 사고법 =====
 *
 * 【DP 문제인지 판단하는 핵심 질문들】
 *
 * 1️⃣ "최적해를 구하는 문제인가?"
 *    → 최댓값, 최솟값, 최적 경로, 최대 길이 등을 묻는가?
 *    → YES면 DP 후보, NO면 다른 알고리즘 고려
 *
 * 2️⃣ "큰 문제가 작은 문제들로 나뉘는가?"
 *    → F(n) = F(n-1) + F(n-2) 같은 관계식이 보이는가?
 *    → "n번째는 (n-1)번째와 (n-2)번째에 의해 결정된다" 같은 패턴?
 *
 * 3️⃣ "같은 부분 문제가 여러 번 계산되는가?"
 *    → 재귀로 풀면 똑같은 함수 호출이 반복되는가?
 *    → 브루트포스로 하면 중복 계산이 많은가?
 *
 * 4️⃣ "선택의 여지가 있는가?"
 *    → "이걸 포함할까 말까?", "이 경로로 갈까 저 경로로 갈까?"
 *    → 매 단계에서 결정을 내려야 하는 상황인가?
 *
 * 【시간복잡도】
 * - 브루트포스: 보통 O(2^n) 또는 O(n!) → 지수적 폭발
 * - DP 적용 후: O(상태수 × 전이시간) → 다항시간으로 단축
 * - 1차원: O(n), 2차원: O(n²), 3차원: O(n³)
 *
 * 【DP vs 다른 알고리즘 구분법】
 *
 * ⚠️ DP vs 그리디 구분법:
 *   - 그리디: "지금 당장 최선이 전체 최선이다" (반례가 없어야 함)
 *   - DP: "지금 최선이 나중에 최선이 아닐 수도 있다" (모든 경우 고려)
 *   - 예: 동전교환에서 그리디는 틀릴 수 있음 (11원을 5,4,1원으로 만들 때)
 *
 * ⚠️ DP vs 분할정복 구분법:
 *   - 분할정복: 부분문제들이 독립적 (합병정렬, 퀵정렬)
 *   - DP: 부분문제들이 겹침 (피보나치에서 F(n-2)가 여러 번 계산됨)
 *
 * ⚠️ DP vs 완전탐색 구분법:
 *   - 완전탐색: 모든 경우를 다 해봄 (시간복잡도 폭발)
 *   - DP: 중복 제거로 효율화 (메모이제이션)
 *
 * 【DP 접근 사고 과정 - 4단계】
 *
 * STEP 1: "상태 정의하기"
 *   → dp[i] 또는 dp[i][j]가 무엇을 의미하는지 명확히 정의
 *   → "i번째까지 고려했을 때 최대값" vs "i에서 끝나는 최대값" 구분 중요!
 *
 * STEP 2: "점화식 찾기"
 *   → 현재 상태가 이전 상태들과 어떤 관계인지 파악
 *   → "지금 선택을 하는 경우 vs 하지 않는 경우" 비교
 *   → 모든 가능한 이전 상태들 중 최적값 선택
 *
 * STEP 3: "초기값 설정"
 *   → 가장 작은 문제의 답을 직접 구해서 설정
 *   → 보통 dp[0], dp[1] 같은 기저 상태
 *
 * STEP 4: "계산 순서 정하기"
 *   → 바텀업: 작은 문제부터 큰 문제로 (반복문)
 *   → 탑다운: 큰 문제부터 재귀로 내려가며 메모이제이션
 */

public class 동적계획법사고 {

    public static void main(String[] args) {
        System.out.println("=== DP 문제 판단과 접근법 실전 예시 ===");
        demonstrateThinkingProcess();
    }

    private static void demonstrateThinkingProcess() {
        // 각 예시는 "이 문제가 DP인지 판단하는 과정"을 보여줌
        System.out.println("1. 계단 오르기 결과: " + stairClimbingThinking(5));
        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("2. 배낭 문제 결과: " + knapsackThinking());
        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("3. 최장증가수열 결과: " + lisThinking(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("4. 동전 교환 결과: " + coinChangeThinking(new int[]{1, 3, 4}, 6));
        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("5. 편집 거리 결과: " + editDistanceThinking("horse", "ros"));
    }

    /**
     * 예시 1: 계단 오르기 - DP 판단 과정과 접근 사고법
     *
     * 🤔 문제 분석: n개 계단을 1칸 또는 2칸씩 올라갈 때 방법의 수는?
     *
     * ✅ DP 판단:
     * 1️⃣ 최적해? → 방법의 "수"를 구하는 문제 (카운팅)
     * 2️⃣ 부분문제? → n번째 계단 = (n-1)번째에서 1칸 + (n-2)번째에서 2칸
     * 3️⃣ 중복계산? → f(n-2)가 f(n)과 f(n-1) 계산시 모두 필요
     * 4️⃣ 선택? → 매번 "1칸 갈까 2칸 갈까" 선택
     *
     * 👍 DP가 맞다!
     */
    public static int stairClimbingThinking(int n) {
        System.out.println("=== 계단 오르기 문제 분석 ===");

        // 🧠 사고과정 1: 상태 정의
        // dp[i] = i번째 계단에 도달하는 방법의 수
        // (주의: "i에서 끝나는" vs "i까지 고려한" 구분 중요!)

        if (n <= 2) return n;

        // 🧠 사고과정 2: 점화식 도출
        // n번째 계단에 도달하려면?
        // → (n-1)번째에서 1칸 오르기 OR (n-2)번째에서 2칸 오르기
        // → dp[n] = dp[n-1] + dp[n-2]

        int[] dp = new int[n + 1];

        // 🧠 사고과정 3: 초기값 (기저 상태)
        dp[1] = 1; // 1계단: 1가지 (1칸)
        dp[2] = 2; // 2계단: 2가지 (1+1칸, 2칸)

        System.out.printf("초기값: dp[1]=%d, dp[2]=%d\n", dp[1], dp[2]);

        // 🧠 사고과정 4: 계산 순서 (바텀업)
        // 작은 것부터 계산해야 큰 것을 계산할 수 있음
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2]; // 점화식 적용
            System.out.printf("dp[%d] = dp[%d] + dp[%d] = %d + %d = %d\n",
                    i, i-1, i-2, dp[i-1], dp[i-2], dp[i]);
        }

        return dp[n];

        /* 🔍 다른 접근은 안될까?
         *
         * ❌ 그리디: "무조건 2칸씩 가자" → 틀림 (3계단일 때 1+1+1도 가능)
         * ❌ 완전탐색: 2^n가지 경우 모두 확인 → 너무 느림
         * ✅ DP: 중복 제거로 O(n)에 해결
         */
    }

    /**
     * 예시 2: 배낭 문제 - DP 판단과 2차원 상태 설계
     *
     * 🤔 문제: 무게 제한 W인 배낭에 최대 가치로 물건 담기
     *
     * ✅ DP 판단:
     * 1️⃣ 최적해? → 최대 가치 (최적화 문제)
     * 2️⃣ 부분문제? → "i번째 물건까지, 무게 w까지" 고려한 최대값
     * 3️⃣ 중복계산? → 같은 (i,w) 상태가 여러 경로에서 계산됨
     * 4️⃣ 선택? → 각 물건마다 "담을까 말까" 결정
     */
    public static int knapsackThinking() {
        int[] weights = {1, 3, 4, 5};  // 무게: [1, 3, 4, 5]
        int[] values = {1, 4, 5, 7};   // 가치: [1, 4, 5, 7]
        int capacity = 7;              // 최적해: 3번+4번 물건 = 가치9
        int n = weights.length;

        // 🧠 사고과정 1: 상태 정의 (2차원이 필요한 이유)
        // dp[i][w] = i번째 물건까지 고려하고, 무게 한도가 w일 때 최대 가치
        // 왜 2차원? → 물건 인덱스(i)와 무게(w) 두 조건이 모두 중요하기 때문

        int[][] dp = new int[n + 1][capacity + 1];

        System.out.println("=== 배낭 문제 단계별 계산 과정 ===");

        // 🧠 사고과정 4: 계산 순서 (단계별 자세한 설명)
        for (int i = 1; i <= n; i++) {
            System.out.printf("\n📦 %d번째 물건 고려 중: 무게=%d, 가치=%d\n",
                    i, weights[i-1], values[i-1]);

            for (int w = 1; w <= capacity; w++) {
                if (weights[i-1] > w) {
                    // 현재 물건이 너무 무거움 - 이전 상태 그대로 복사
                    dp[i][w] = dp[i-1][w];

                    if (w == capacity) {
                        System.out.printf("   무게%d > 한도%d: 못 담음, 이전값 유지 = %d\n",
                                weights[i-1], w, dp[i][w]);
                    }
                } else {
                    // ⚠️ 핵심 포인트: "넣었다가 빼는" 게 아니라 "두 경우 중 선택"
                    int include = dp[i-1][w - weights[i-1]] + values[i-1]; // 담는 경우
                    int exclude = dp[i-1][w];                              // 안 담는 경우
                    dp[i][w] = Math.max(include, exclude);

                    if (w == capacity) {
                        System.out.printf("   담으면: %d + %d = %d\n",
                                dp[i-1][w - weights[i-1]], values[i-1], include);
                        System.out.printf("   안담으면: %d\n", exclude);
                        System.out.printf("   → 최대값 선택: %d %s\n",
                                dp[i][w],
                                (dp[i][w] == include) ? "(담기 선택)" : "(안담기 선택)");
                    }
                }
            }
        }

        // 🔍 최적해 추적 (어떤 물건들이 선택되었는지 역추적)
        System.out.println("\n=== 최적해 추적 ===");
        boolean[] selected = new boolean[n];
        int w = capacity;

        for (int i = n; i > 0; i--) {
            // 🧠 역추적 로직: dp[i][w] != dp[i-1][w]이면 i번째 물건을 선택한 것
            if (dp[i][w] != dp[i-1][w]) {
                selected[i-1] = true;
                w -= weights[i-1]; // 무게 차감
                System.out.printf("물건 %d 선택됨: 무게=%d, 가치=%d\n",
                        i, weights[i-1], values[i-1]);
            } else {
                System.out.printf("물건 %d 선택안됨: 무게=%d, 가치=%d\n",
                        i, weights[i-1], values[i-1]);
            }
        }

        return dp[n][capacity];

        /* 흔한 오해들:
         *
         * 오해 1: "첫 번째 물건을 넣었으면 나중에 빼는 과정이 있다"
         * 실제: DP는 모든 조합을 동시에 고려하여 각 상태의 최적값만 저장
         *
         * 오해 2: "5번째 물건(무게5, 가치7)이 가장 좋으니까 무조건 들어간다"
         * 실제: 가치/무게 비율이 좋아도 전체 조합에서 최적이 아닐 수 있음
         *       5번 물건 단독(가치7) < 2번+3번 물건(가치4+5=9)
         *
         * 오해 3: "DP 테이블에서 큰 값이 나오면 그 물건이 선택된 것"
         * 실제: dp[i][w]는 "i번째까지 고려한 최적값"이지 "i번째 물건 포함"이 아님
         *       역추적을 통해야만 실제 선택된 물건들을 알 수 있음
         *
         * 핵심 깨달음:
         * DP는 "과정"이 아니라 "결과들의 집합"을 다룸
         * 각 dp[i][w]는 "그 조건에서 가능한 모든 경우 중 최적값"을 저장
         * 실제로 어떤 물건들이 선택되었는지는 별도 추적 필요
         */

        /* 🔍 왜 다른 방법은 안 될까?
         *
         * ❌ 그리디: "가치/무게 비율 높은 것부터" → 0-1배낭에서는 틀림
         *     (분할 가능하면 그리디 가능, 0-1은 전체를 담거나 안 담거나)
         * ❌ 완전탐색: 2^n가지 부분집합 모두 확인 → 지수시간
         * ✅ DP: O(n × capacity)로 해결
         */
    }

    /**
     * 예시 3: 최장증가수열(LIS) - "끝나는 위치" 상태 정의의 중요성
     *
     * 🤔 문제: 주어진 수열에서 가장 긴 증가 부분수열의 길이는?
     *
     * ✅ DP 판단:
     * 1️⃣ 최적해? → 최대 길이 (최적화)
     * 2️⃣ 부분문제? → i번째에서 끝나는 LIS = 이전 작은 수들의 LIS + 1
     * 3️⃣ 중복? → 같은 위치에서 끝나는 LIS 길이가 여러 경로에서 필요
     * 4️⃣ 선택? → 각 원소를 수열에 "포함할까 말까"
     */
    public static int lisThinking(int[] nums) {
        System.out.println("=== 최장증가수열(LIS) 문제 분석 ===");
        System.out.printf("입력 배열: %s\n", Arrays.toString(nums));

        int n = nums.length;
        if (n == 0) return 0;

        // 🧠 사고과정 1: 상태 정의 - 매우 중요!
        // dp[i] = i번째 원소에서 "끝나는" 최장증가수열의 길이
        // ⚠️ "i까지 고려한" 최대 길이가 아님!
        // → "어떤 수로 끝나는지" 정보가 다음 결정에 중요

        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 각 원소 자체로 길이 1

        // 🧠 사고과정 2: 점화식 도출
        // dp[i]를 계산하려면?
        // → 이전 모든 j (j < i)에 대해
        // → nums[j] < nums[i]이면 dp[i] = max(dp[i], dp[j] + 1)

        int maxLength = 1;

        // 🧠 사고과정 3 & 4: 초기값과 계산 순서
        for (int i = 1; i < n; i++) {
            System.out.printf("\n[%d번째 원소 %d 처리 중]\n", i, nums[i]);

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // nums[j] 다음에 nums[i]를 붙여서 수열 연장
                    int newLength = dp[j] + 1;
                    if (newLength > dp[i]) {
                        System.out.printf("  nums[%d]=%d < nums[%d]=%d: dp[%d] = max(%d, %d+1) = %d\n",
                                j, nums[j], i, nums[i], i, dp[i], dp[j], newLength);
                        dp[i] = newLength;
                    }
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
            System.out.printf("  최종 dp[%d] = %d\n", i, dp[i]);
        }

        System.out.printf("\n최종 DP 배열: %s\n", Arrays.toString(dp));
        System.out.printf("최장증가수열 길이: %d\n", maxLength);

        // 🔍 실제 LIS 찾기 (역추적)
        System.out.println("\n=== LIS 역추적 ===");
        List<Integer> lis = new ArrayList<>();
        int currentLength = maxLength;

        // 뒤에서부터 찾기
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == currentLength) {
                lis.add(0, nums[i]); // 앞쪽에 추가
                currentLength--;
                if (currentLength == 0) break;
            }
        }

        System.out.printf("실제 LIS 중 하나: %s\n", lis);

        return maxLength;

        /* 흔한 실수들:
         *
         * 1) 상태 정의 실수:
         *    dp[i] = "i까지 고려한 최대 길이" (X)
         *    → 이전 수열이 어떤 수로 끝났는지 모르면 연결 불가!
         *
         * 2) 초기값 실수:
         *    dp[i] = 0으로 초기화 (X)
         *    → 최소한 자기 자신으로 길이 1
         *
         * 3) 결과값 실수:
         *    return dp[n-1] (X)
         *    → 마지막 원소로 끝나는 LIS가 최장이 아닐 수 있음
         *
         * 4) 역추적 실수:
         *    위의 역추적 코드는 하나의 LIS만 찾음 (여러 개 있을 수 있음)
         *    → 실전에서는 길이만 구하는 경우가 많으므로 문제없음
         */
    }

    /**
     * 예시 4: 동전 교환 - 그리디가 실패하는 대표적 케이스
     *
     * 🤔 문제: 주어진 동전들로 특정 금액을 만드는 최소 개수는?
     *
     * ✅ DP 판단:
     * 1️⃣ 최적해? → 최소 개수 (최적화)
     * 2️⃣ 부분문제? → amount를 만드는 방법 = (amount-coin)을 만드는 방법 + coin
     * 3️⃣ 중복? → 같은 금액을 만드는 과정이 여러 경로에서 중복
     * 4️⃣ 선택? → 각 동전을 "쓸까 말까" (무한히 쓸 수 있음)
     */
    public static int coinChangeThinking(int[] coins, int amount) {
        System.out.println("=== 동전 교환 문제 분석 ===");
        System.out.printf("동전 종류: %s, 목표 금액: %d\n", Arrays.toString(coins), amount);

        // 🧠 사고과정 1: 상태 정의
        // dp[i] = 금액 i를 만드는 최소 동전 개수

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 불가능한 큰 값으로 초기화

        // 🧠 사고과정 2: 초기값
        dp[0] = 0; // 0원 만들기: 0개 필요

        // 🧠 사고과정 3 & 4: 점화식과 계산 순서
        // dp[i] = min(dp[i-coin] + 1) for all coins <= i

        for (int i = 1; i <= amount; i++) {
            System.out.printf("[금액 %d 만들기]\n", i);

            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != amount + 1) {
                    // coin을 사용해서 i를 만드는 경우
                    int newCount = dp[i - coin] + 1;
                    if (newCount < dp[i]) {
                        System.out.printf("  동전 %d 사용: dp[%d] = dp[%d] + 1 = %d + 1 = %d\n",
                                coin, i, i - coin, dp[i - coin], newCount);
                        dp[i] = newCount;
                    }
                }
            }

            if (dp[i] == amount + 1) {
                System.out.printf("  → 금액 %d는 만들 수 없음\n", i);
            } else {
                System.out.printf("  → 금액 %d 최소 개수: %d개\n", i, dp[i]);
            }
        }

        /* 왜 그리디가 안 되는가?
         *
         * 그리디 가정: "지금 당장 큰 동전을 쓰는 게 나중에도 좋다"
         * 반례 상황: coins=[1,3,4], amount=6
         *
         * 그리디 선택:
         * 6 -> 4 사용 -> 남은 2 -> 1+1 사용 -> 총 3개
         *
         * 최적 선택:
         * 6 -> 3 사용 -> 남은 3 -> 3 사용 -> 총 2개
         *
         * 교훈: 지역적 최적해가 전역적 최적해를 보장하지 않을 때는 DP!
         */

        System.out.println("\n=== 그리디 vs DP 비교 ===");
        System.out.println("그리디 방식 (큰 동전부터): 4 + 1 + 1 = 3개");
        System.out.println("DP 최적해: 3 + 3 = 2개");
        System.out.println("→ 그리디가 실패하는 대표적 사례!");

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 예시 5: 편집 거리 - 2차원 DP의 대표적 케이스
     *
     * 🤔 언제 2차원 DP를 써야 할까?
     * → 두 개의 독립적인 "상태 변수"가 필요할 때
     *
     * 1차원으로 불충분한 이유:
     * → 문자열 A의 진행 상태 + 문자열 B의 진행 상태 둘 다 추적 필요
     */
    public static int editDistanceThinking(String word1, String word2) {
        System.out.println("=== 편집 거리 문제 분석 ===");
        System.out.printf("문자열1: \"%s\", 문자열2: \"%s\"\n", word1, word2);

        int m = word1.length();
        int n = word2.length();

        // 🧠 상태 정의: 두 개의 인덱스가 필요한 이유
        // dp[i][j] = word1[0..i-1]을 word2[0..j-1]로 변환하는 최소 비용
        // 왜 2차원? → word1의 위치와 word2의 위치를 독립적으로 추적해야 함

        int[][] dp = new int[m + 1][n + 1];

        // 초기화: 경계 조건
        for (int i = 0; i <= m; i++) dp[i][0] = i; // 모두 삭제
        for (int j = 0; j <= n; j++) dp[0][j] = j; // 모두 삽입

        System.out.println("\n=== DP 테이블 계산 과정 ===");

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i-1);
                char c2 = word2.charAt(j-1);

                if (c1 == c2) {
                    dp[i][j] = dp[i-1][j-1]; // 문자 같으면 비용 없음
                    System.out.printf("dp[%d][%d]: '%c' == '%c' → dp[%d][%d] = %d (변환 불필요)\n",
                            i, j, c1, c2, i-1, j-1, dp[i][j]);
                } else {
                    // 3가지 연산 중 최소값
                    int replace = dp[i-1][j-1] + 1; // 교체
                    int delete = dp[i-1][j] + 1;    // 삭제
                    int insert = dp[i][j-1] + 1;    // 삽입

                    dp[i][j] = Math.min(Math.min(replace, delete), insert);

                    System.out.printf("dp[%d][%d]: '%c' != '%c'\n", i, j, c1, c2);
                    System.out.printf("  교체: dp[%d][%d] + 1 = %d + 1 = %d\n", i-1, j-1, dp[i-1][j-1], replace);
                    System.out.printf("  삭제: dp[%d][%d] + 1 = %d + 1 = %d\n", i-1, j, dp[i-1][j], delete);
                    System.out.printf("  삽입: dp[%d][%d] + 1 = %d + 1 = %d\n", i, j-1, dp[i][j-1], insert);
                    System.out.printf("  → 최소값: %d\n", dp[i][j]);
                }
            }
        }

        // DP 테이블 출력
        System.out.println("\n=== 최종 DP 테이블 ===");
        System.out.print("    ");
        for (int j = 0; j <= n; j++) {
            if (j == 0) System.out.print("ε ");
            else System.out.printf("%c ", word2.charAt(j-1));
        }
        System.out.println();

        for (int i = 0; i <= m; i++) {
            if (i == 0) System.out.print("ε ");
            else System.out.printf("%c ", word1.charAt(i-1));

            for (int j = 0; j <= n; j++) {
                System.out.printf("%d ", dp[i][j]);
            }
            System.out.println();
        }

        return dp[m][n];

        /* 💡 2차원 DP 판단 기준:
         *
         * ✅ 필요한 경우:
         * - 두 문자열/배열 비교 (LCS, 편집거리)
         * - 2차원 격자 경로 문제
         * - 배낭 문제 (아이템 × 무게)
         *
         * ❌ 불필요한 경우:
         * - 하나의 수열만 다루는 문제 (LIS, 계단오르기)
         * - 상태가 하나의 변수로 충분한 경우
         */
    }

    /**
     * 🎯 실전 팁: DP 문제 만났을 때 사고 체크리스트
     *
     * 1. "이게 정말 DP 문제인가?" 4가지 조건 확인
     * 2. "상태를 어떻게 정의할까?" → 가장 중요한 단계!
     * 3. "점화식은 어떻게 될까?" → 현재 = f(이전 상태들)
     * 4. "초기값은 뭐지?" → 가장 작은 경우 직접 계산
     * 5. "계산 순서는?" → 의존성 고려해서 순서 결정
     * 6. "시간/공간 최적화 가능한가?" → 슬라이딩 윈도우 등
     *
     * 🚨 흔한 실수들:
     * - 상태 정의 모호: "i번째까지" vs "i번째에서 끝나는" 구분
     * - 점화식 빠뜨림: 모든 경우 고려했는지 확인
     * - 초기값 실수: 경계 조건 꼼꼼히 확인
     * - 결과값 실수: dp[n-1] vs max(dp) 구분
     *
     * 🔥 DP 마스터를 위한 핵심 깨달음:
     *
     * 1️⃣ "DP는 과정이 아니라 결과의 집합이다"
     *    → 각 dp[i]는 "그 상태에서 가능한 모든 경우의 최적값"
     *    → "어떻게 도달했는지"가 아니라 "그 상태의 최적값이 뭐인지"에 집중
     *
     * 2️⃣ "상태 정의가 DP의 90%다"
     *    → 상태를 잘못 정의하면 점화식 자체가 불가능
     *    → "무엇을 저장할 것인가?"에 가장 많은 시간을 투자하라
     *
     * 3️⃣ "그리디의 반례를 찾으면 DP다"
     *    → "지금 당장 최선이 나중에도 최선인가?" 의심해보기
     *    → 반례가 하나라도 있으면 DP 고려
     *
     * 4️⃣ "메모이제이션은 DP의 핵심이다"
     *    → 같은 계산을 두 번 하지 않겠다는 철학
     *    → "이미 계산했으면 저장된 값 사용"
     *
     * 5️⃣ "차원 늘리기를 두려워하지 마라"
     *    → 1차원으로 안 되면 2차원, 2차원으로 안 되면 3차원
     *    → 메모리 vs 시간복잡도 트레이드오프 고려
     */

    /**
     * 보너스: DP 최적화 기법들
     */
    public static void dpOptimizationTechniques() {
        System.out.println("\n=== DP 최적화 기법들 ===");

        System.out.println("1️⃣ 공간 최적화 (Space Optimization)");
        System.out.println("   - 2차원을 1차원으로: 이전 행만 필요한 경우");
        System.out.println("   - 슬라이딩 윈도우: 고정된 크기만 필요한 경우");
        System.out.println("   - 예: 피보나치에서 dp 배열 대신 변수 2개만 사용");

        System.out.println("\n2️⃣ 상태 압축 (State Compression)");
        System.out.println("   - 비트마스킹: 여러 불린 상태를 하나의 정수로");
        System.out.println("   - 해시맵: 복잡한 상태를 키로 사용");

        System.out.println("\n3️⃣ 차원 축소 (Dimension Reduction)");
        System.out.println("   - 좌표 압축: 큰 좌표 공간을 작은 공간으로");
        System.out.println("   - 상태 병합: 동일한 결과를 가지는 상태들 통합");

        System.out.println("\n4️⃣ 계산 순서 최적화");
        System.out.println("   - 탑다운 vs 바텀업: 문제에 따라 선택");
        System.out.println("   - 메모이제이션: 필요한 것만 계산");

        System.out.println("\n5️⃣ 수학적 최적화");
        System.out.println("   - 행렬 거듭제곱: 선형 점화식의 빠른 계산");
        System.out.println("   - 조합론: 경우의 수 계산 시 공식 활용");
    }
}