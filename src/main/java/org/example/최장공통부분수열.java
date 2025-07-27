package org.example;

import java.util.*;

/*
 * ========== 최장공통부분수열(LCS: Longest Common Subsequence) 알고리즘 ==========
 *
 * 【기본 개념】
 * - 두 문자열에서 공통으로 나타나는 부분 수열 중 가장 긴 것을 찾는 알고리즘
 * - 부분수열(Subsequence): 원래 순서는 유지하되, 연속될 필요는 없는 수열
 * - 예: "ABCDE"와 "ACE"에서 LCS는 "ACE" (길이 3)
 *
 * 【핵심 아이디어】
 * - 동적 계획법(Dynamic Programming) 사용
 * - 작은 문제들의 해를 조합하여 큰 문제를 해결
 * - "문자가 같으면 이전 결과 + 1, 다르면 각각의 최댓값"
 *
 * 【시간복잡도】
 * - O(N × M) (N: 첫 번째 문자열 길이, M: 두 번째 문자열 길이)
 * - 공간복잡도: O(N × M) (최적화 시 O(min(N, M)) 가능)
 *
 * 【선행 지식】
 * - 동적 계획법(DP)의 기본 개념
 * - 2차원 배열을 이용한 메모이제이션
 * - 부분 문제의 최적 구조(Optimal Substructure) 이해
 * - 재귀적 사고와 점화식 도출 능력
 *
 * 【코딩테스트 활용 유형】
 * 1. 기본 LCS 길이 구하기
 * 2. 실제 LCS 문자열 복원하기
 * 3. 편집 거리(Edit Distance) 문제의 기반
 * 4. DNA 서열 분석, 파일 비교 등의 실생활 응용
 * 5. 문자열 유사도 측정
 * 6. 게임에서 콤보 시스템, 패턴 매칭
 *
 * 【유사 알고리즘과의 차이점】
 *
 * ★ LCS vs 최장공통부분문자열(Longest Common Substring)
 * - LCS: 연속될 필요 없음, "ABCDE"와 "AECDB"에서 "ACD" 가능
 * - Substring: 반드시 연속되어야 함, "ABCDE"와 "AECDB"에서 "A" 또는 "D"만 가능
 * - 착각하기 쉬운 점: 문제에서 "연속"이라는 단어가 나오면 Substring 문제
 *
 * ★ LCS vs LIS(Longest Increasing Subsequence)
 * - LCS: 두 수열의 공통 부분
 * - LIS: 하나의 수열에서 증가하는 부분 수열
 * - 착각하기 쉬운 점: LIS는 O(N log N)으로 최적화 가능하지만 LCS는 일반적으로 O(NM)
 *
 * ★ LCS vs 편집 거리(Edit Distance)
 * - LCS: 공통 부분의 최대 길이
 * - Edit Distance: 한 문자열을 다른 문자열로 바꾸는 최소 연산 수
 * - 관계: Edit Distance = N + M - 2 × LCS (삽입/삭제만 허용할 때)
 * - 착각하기 쉬운 점: 문제에서 "최소 변경"을 요구하면 Edit Distance
 *
 * ★ LCS vs KMP 알고리즘
 * - LCS: 부분 수열 매칭 (순서만 유지)
 * - KMP: 부분 문자열 매칭 (연속된 패턴)
 * - 착각하기 쉬운 점: 패턴 검색 문제를 LCS로 접근하면 비효율적
 *
 * 【잘못 접근하기 쉬운 경우】
 * - 문자열 유사도를 구할 때 단순 비교 대신 LCS 사용해야 할 때
 * - "공통 부분"이라는 말에 현혹되어 연속성을 고려하지 않을 때
 * - 3개 이상의 문자열 LCS를 2개씩 구해서 해결하려 할 때 (별도 DP 필요)
 * - 길이만 필요한데 실제 문자열까지 구하려고 할 때 (불필요한 복잡도 증가)
 */

public class 최장공통부분수열 {

    public static void main(String[] args) {
        System.out.println("=== 최장공통부분수열(LCS) 알고리즘 실행 ===");

        // 테스트 케이스 1: 기본적인 경우
        String str1 = "ABCDGH";
        String str2 = "AEDFHR";
        System.out.println("테스트 케이스 1:");
        System.out.println("문자열 1: " + str1);
        System.out.println("문자열 2: " + str2);
        System.out.println("예상 LCS: ADH (길이 3)");

        runLCS(str1, str2);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 테스트 케이스 2: 더 복잡한 경우
        String str3 = "PROGRAMMING";
        String str4 = "DEBUGGING";
        System.out.println("테스트 케이스 2:");
        System.out.println("문자열 1: " + str3);
        System.out.println("문자열 2: " + str4);
        System.out.println("예상 LCS: RGING 또는 다른 조합 (길이 5)");

        runLCS(str3, str4);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 테스트 케이스 3: 동일한 문자열
        String str5 = "HELLO";
        String str6 = "HELLO";
        System.out.println("테스트 케이스 3 (동일한 문자열):");
        System.out.println("문자열 1: " + str5);
        System.out.println("문자열 2: " + str6);
        System.out.println("예상 LCS: HELLO (길이 5)");

        runLCS(str5, str6);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 테스트 케이스 4: 공통 부분이 없는 경우
        String str7 = "ABC";
        String str8 = "DEF";
        System.out.println("테스트 케이스 4 (공통 부분 없음):");
        System.out.println("문자열 1: " + str7);
        System.out.println("문자열 2: " + str8);
        System.out.println("예상 LCS: (없음) (길이 0)");

        runLCS(str7, str8);
    }

    /**
     * LCS 알고리즘을 실행하고 결과를 출력하는 메서드
     */
    static void runLCS(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // 1. LCS 길이 계산
        int lcsLength = calculateLCS(str1, str2, dp);

        // 2. 실제 LCS 문자열 복원
        String lcsString = reconstructLCS(str1, str2, dp);

        // 3. 결과 출력
        System.out.println("✅ LCS 길이: " + lcsLength);
        System.out.println("✅ LCS 문자열: \"" + lcsString + "\"");

        // 4. DP 테이블 출력 (이해를 돕기 위해)
        System.out.println("\n📊 DP 테이블:");
        printDPTable(str1, str2, dp);
    }

    /**
     * LCS 길이를 계산하는 핵심 메서드
     *
     * 【핵심 아이디어 상세 설명】
     * dp[i][j] = str1의 처음 i개 문자와 str2의 처음 j개 문자의 LCS 길이
     *
     * 점화식:
     * 1) str1[i-1] == str2[j-1] 인 경우:
     *    dp[i][j] = dp[i-1][j-1] + 1
     *    → 두 문자가 같으므로 이전 결과에 1을 더함
     *
     * 2) str1[i-1] != str2[j-1] 인 경우:
     *    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *    → 두 문자가 다르므로 각각을 제외한 경우 중 최댓값 선택
     *
     * 왜 이렇게 될까?
     * - 문자가 같으면: 둘 다 LCS에 포함시키고 나머지 부분의 LCS에 1을 더함
     * - 문자가 다르면: 둘 중 하나는 LCS에 포함되지 않으므로,
     *   각각을 제외한 경우 중 더 좋은 결과를 선택
     */
    static int calculateLCS(String str1, String str2, int[][] dp) {
        int n = str1.length();
        int m = str2.length();

        System.out.println("\n--- LCS 길이 계산 과정 ---");

        // 기저 사례: 빈 문자열과의 LCS는 0
        // dp[0][j] = 0 (str1이 빈 문자열)
        // dp[i][0] = 0 (str2가 빈 문자열)
        // 자바에서는 배열이 0으로 초기화되므로 별도 처리 불필요

        // DP 테이블 채우기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c1 = str1.charAt(i - 1);  // str1의 i번째 문자 (0-based로 i-1)
                char c2 = str2.charAt(j - 1);  // str2의 j번째 문자 (0-based로 j-1)

                if (c1 == c2) {
                    // 문자가 같은 경우: 이전 대각선 값에 1을 더함
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    System.out.println("dp[" + i + "][" + j + "] = " + dp[i][j] +
                            " ('" + c1 + "' == '" + c2 + "', 대각선 + 1)");
                } else {
                    // 문자가 다른 경우: 위쪽 또는 왼쪽 값 중 최댓값
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    System.out.println("dp[" + i + "][" + j + "] = " + dp[i][j] +
                            " ('" + c1 + "' != '" + c2 + "', max(" + dp[i-1][j] + "," + dp[i][j-1] + "))");
                }
            }
        }

        return dp[n][m];  // 전체 문자열의 LCS 길이
    }

    /**
     * DP 테이블을 역추적하여 실제 LCS 문자열을 복원하는 메서드
     *
     * 【역추적 원리】
     * 1. dp[n][m]에서 시작하여 dp[0][0]까지 거슬러 올라감
     * 2. 현재 위치 dp[i][j]에서:
     *    - str1[i-1] == str2[j-1]이고 dp[i][j] == dp[i-1][j-1] + 1 이면
     *      → 이 문자가 LCS에 포함됨, 대각선으로 이동
     *    - dp[i][j] == dp[i-1][j] 이면 → 위로 이동 (str1[i-1]은 LCS에 미포함)
     *    - dp[i][j] == dp[i][j-1] 이면 → 왼쪽으로 이동 (str2[j-1]은 LCS에 미포함)
     *
     * 🤔 왜 이렇게 할 수 있을까?
     * - DP를 구성할 때 사용한 논리를 거꾸로 적용
     * - 각 단계에서 어떤 선택을 했는지 추적하여 원본 문자열 복원
     */
    static String reconstructLCS(String str1, String str2, int[][] dp) {
        System.out.println("\n--- LCS 문자열 복원 과정 ---");

        int i = str1.length();
        int j = str2.length();
        StringBuilder lcs = new StringBuilder();

        // 역추적: 오른쪽 아래에서 왼쪽 위로 이동
        while (i > 0 && j > 0) {
            char c1 = str1.charAt(i - 1);
            char c2 = str2.charAt(j - 1);

            if (c1 == c2) {
                // 문자가 같고, 실제로 이 문자 때문에 LCS 길이가 증가했다면
                // 이 문자는 LCS에 포함됨
                lcs.append(c1);
                System.out.println("'" + c1 + "' 추가 (위치: str1[" + (i-1) + "], str2[" + (j-1) + "])");
                i--;
                j--;  // 대각선으로 이동
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // 위쪽 값이 더 크면, str1[i-1]을 제외하고 위로 이동
                System.out.println("위로 이동 (str1[" + (i-1) + "]='" + c1 + "' 제외)");
                i--;
            } else {
                // 왼쪽 값이 더 크거나 같으면, str2[j-1]을 제외하고 왼쪽으로 이동
                System.out.println("왼쪽으로 이동 (str2[" + (j-1) + "]='" + c2 + "' 제외)");
                j--;
            }
        }

        // StringBuilder에는 역순으로 문자가 저장되어 있으므로 뒤집기
        return lcs.reverse().toString();
    }

    /**
     * DP 테이블을 시각적으로 출력하는 헬퍼 메서드
     * 알고리즘의 동작 과정을 이해하는 데 도움이 됨
     */
    static void printDPTable(String str1, String str2, int[][] dp) {
        int n = str1.length();
        int m = str2.length();

        // 헤더 출력 (str2의 문자들)
        System.out.print("    ε ");  // ε는 빈 문자열을 의미
        for (int j = 0; j < m; j++) {
            System.out.printf("%2c ", str2.charAt(j));
        }
        System.out.println();

        // 각 행 출력
        for (int i = 0; i <= n; i++) {
            // 행 헤더 (str1의 문자 또는 빈 문자열)
            if (i == 0) {
                System.out.print("ε ");
            } else {
                System.out.printf("%c ", str1.charAt(i - 1));
            }

            // DP 값들 출력
            for (int j = 0; j <= m; j++) {
                System.out.printf("%2d ", dp[i][j]);
            }
            System.out.println();
        }

        System.out.println("💡 해석: dp[i][j] = str1의 처음 i개 문자와 str2의 처음 j개 문자의 LCS 길이");
    }

    /*
     * ========== 공간 복잡도 최적화 버전 ==========
     *
     * LCS 길이만 필요하고 실제 문자열이 필요 없는 경우,
     * 공간 복잡도를 O(min(N, M))으로 줄일 수 있음
     *
     * 핵심 아이디어:
     * - DP 계산 시 이전 행의 정보만 필요함
     * - 2개의 1차원 배열만 사용하여 공간 절약
     */

    /**
     * 공간 최적화된 LCS 길이 계산 (참고용)
     * 메모리 제한이 까다로운 문제에서 사용
     */
    static int calculateLCSOptimized(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // 더 짧은 문자열을 기준으로 배열 크기 결정
        if (n < m) {
            return calculateLCSOptimized(str2, str1);  // 순서 바꿔서 재귀 호출
        }

        int[] prev = new int[m + 1];  // 이전 행
        int[] curr = new int[m + 1];  // 현재 행

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }

            // 배열 교체 (이전 행 ← 현재 행)
            int[] temp = prev;
            prev = curr;
            curr = temp;
            Arrays.fill(curr, 0);  // 새로운 현재 행 초기화
        }

        return prev[m];
    }

    /*
     * ========== 실전 응용 예시 ==========
     */

    /**
     * 편집 거리(Edit Distance) 계산 예시
     * LCS와 밀접한 관련이 있는 알고리즘
     *
     * 관계식: Edit Distance = N + M - 2 × LCS (삽입/삭제만 허용할 때)
     */
    static int editDistance(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];

        calculateLCS(str1, str2, dp);
        int lcsLength = dp[n][m];

        return n + m - 2 * lcsLength;
    }

    /**
     * 3개 문자열의 LCS 계산 예시 (참고용)
     * 2차원 DP를 3차원으로 확장
     */
    static int lcsThreeStrings(String str1, String str2, String str3) {
        int n1 = str1.length();
        int n2 = str2.length();
        int n3 = str3.length();

        int[][][] dp = new int[n1 + 1][n2 + 1][n3 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                for (int k = 1; k <= n3; k++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) &&
                            str2.charAt(j - 1) == str3.charAt(k - 1)) {
                        // 세 문자가 모두 같은 경우
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        // 하나라도 다른 경우: 각각을 제외한 경우 중 최댓값
                        dp[i][j][k] = Math.max(dp[i - 1][j][k],
                                Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }

        return dp[n1][n2][n3];
    }

    /*
     * ========== 코딩테스트 실전 팁 ==========
     *
     * 1. 문제 유형 판별:
     *    - "공통 부분 수열" → LCS
     *    - "공통 부분 문자열" (연속) → 다른 알고리즘
     *    - "최소 편집" → Edit Distance
     *    - "증가하는 부분 수열" → LIS
     *
     * 2. 구현 시 주의사항:
     *    - 인덱스 처리: dp[i][j]는 문자열의 처음 i, j개 문자 의미
     *    - 문자 비교 시 i-1, j-1 인덱스 사용
     *    - 기저 사례: 빈 문자열과의 LCS는 0
     *
     * 3. 최적화 고려사항:
     *    - 길이만 필요: 공간 최적화 (O(min(N,M)))
     *    - 실제 문자열 필요: 역추적 구현
     *    - 매우 긴 문자열: 해시나 다른 기법 고려
     *
     * 4. 디버깅 팁:
     *    - DP 테이블 출력하여 진행 과정 확인
     *    - 작은 예시로 손으로 직접 계산해보기
     *    - 역추적 과정을 단계별로 출력
     */
}
