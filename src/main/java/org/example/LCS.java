package org.example;

/*
 * ===== LCS (Longest Common Subsequence) - 최장 공통 부분수열 =====
 *
 * 알고리즘 소개:
 * - 두 문자열에서 공통으로 나타나는 부분수열 중 가장 긴 것을 찾는 알고리즘
 * - 부분수열(Subsequence): 원래 순서는 유지하되 연속될 필요 없는 문자들의 나열
 * - 예: "ABCDGH"와 "AEDFHR"의 LCS는 "ADH" (길이 3)
 * - 동적계획법(DP)을 사용하여 효율적으로 해결
 *
 * 시간복잡도: O(N×M) - N, M은 각 문자열의 길이
 * 공간복잡도: O(N×M) - 2차원 DP 테이블 사용
 *
 * 선행 지식:
 * 1. 동적계획법(Dynamic Programming)의 기본 개념과 점화식
 * 2. 2차원 배열 처리와 인덱싱
 * 3. 문자열 처리 기본 (charAt, length 등)
 * 4. 부분수열 vs 부분문자열의 차이점 이해
 *
 * ===== 코딩테스트 출제 유형 =====
 *
 * 1. 기본 LCS 길이 구하기 (가장 빈출)
 *    - 두 문자열의 최장 공통 부분수열 길이만 구하는 문제
 *    - 보통 골드4~골드3 난이도
 *
 * 2. LCS 복원하기 (실제 문자열 구하기)
 *    - 길이뿐만 아니라 실제 LCS 문자열도 출력
 *    - 역추적(backtracking) 기법 필요
 *
 * 3. 3개 이상 문자열의 LCS
 *    - 3차원 DP로 확장 (매우 어려움)
 *    - 대학원 수준이므로 코테에서는 거의 안 나옴
 *
 * 4. LCS 변형 문제들
 *    - 편집 거리(Edit Distance)의 부분 문제
 *    - DNA 서열 분석, 파일 비교 등 실제 응용
 *
 * 5. LIS와 LCS 결합 문제
 *    - 복잡한 조건이 추가된 고난도 문제
 *
 * ===== 비슷한 알고리즘과의 구분 및 주의사항 =====
 *
 * vs LIS (Longest Increasing Subsequence - 최장 증가 부분수열):
 * - LIS: 하나의 수열에서 증가하는 가장 긴 부분수열
 * - LCS: 두 수열에서 공통인 가장 긴 부분수열
 * - 착각 포인트: 문제에서 "증가"라는 단어가 없으면 LCS일 가능성 높음
 *
 * vs 편집 거리(Edit Distance, Levenshtein Distance):
 * - 편집 거리: 한 문자열을 다른 문자열로 바꾸는 최소 연산 횟수
 * - LCS: 공통 부분의 최대 길이 (변환이 아닌 공통점 찾기)
 * - 관계: 편집 거리 = len1 + len2 - 2×LCS
 * - 착각 포인트: "최소 변경"이면 편집거리, "공통 최대"면 LCS
 *
 * vs 부분 문자열(Substring) 문제:
 * - 부분 문자열: 연속된 문자들만 가능 (KMP, 라빈카프 등 사용)
 * - 부분 수열: 연속되지 않아도 됨 (LCS 사용)
 * - 착각 포인트: 문제에서 "연속"이라는 단어 확인 필수
 *
 * vs 최장 공통 접두사/접미사:
 * - 접두사/접미사: 문자열의 처음/끝부터 연속으로 일치하는 부분
 * - LCS: 중간에 빠져도 되는 공통 부분
 * - 구분: 문제에서 "prefix", "suffix" 단어 확인
 *
 * 올바른 알고리즘 선택 기준:
 * - 두 문자열 + 공통 + 순서 유지 + 연속 불필요 → LCS
 * - 한 문자열 + 증가 순서 → LIS  
 * - 문자열 변환 + 최소 비용 → 편집 거리
 * - 연속된 공통 부분 → KMP나 문자열 매칭
 */

public class LCS {
    
    public static void main(String[] args) {
        // 테스트 케이스들 - 실제 코딩테스트에서는 이 부분만 수정
        
        // 기본 예시
        String str1 = "ABCDGH";
        String str2 = "AEDFHR";
        System.out.println("문자열1: " + str1);
        System.out.println("문자열2: " + str2);
        System.out.println("LCS 길이: " + getLCSLength(str1, str2));
        System.out.println("LCS 문자열: " + getLCSString(str1, str2));
        System.out.println();
        
        // 코딩테스트 빈출 예시
        str1 = "ACAYKP";
        str2 = "CAPCAK";
        System.out.println("문자열1: " + str1);
        System.out.println("문자열2: " + str2);
        System.out.println("LCS 길이: " + getLCSLength(str1, str2));
        System.out.println("LCS 문자열: " + getLCSString(str1, str2));
        System.out.println();
        
        // 완전히 다른 문자열
        str1 = "ABC";
        str2 = "DEF";
        System.out.println("문자열1: " + str1);
        System.out.println("문자열2: " + str2);
        System.out.println("LCS 길이: " + getLCSLength(str1, str2));
        System.out.println("LCS 문자열: " + getLCSString(str1, str2));
    }
    
    /**
     * LCS 길이만 구하는 메서드 (코딩테스트 기본 유형)
     * 대부분의 문제에서는 길이만 요구함
     */
    public static int getLCSLength(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        // ★ DP 테이블 생성: dp[i][j] = str1의 i번째까지와 str2의 j번째까지의 LCS 길이
        // 인덱스 혼동 방지를 위해 1부터 시작 (0번 인덱스는 빈 문자열)
        int[][] dp = new int[n + 1][m + 1];
        
        // ★ 기저 조건: 빈 문자열과의 LCS는 0
        // dp[0][j] = 0, dp[i][0] = 0 (자동으로 0으로 初期化됨)
        
        // ★ DP 테이블 채우기 - 이 부분이 LCS의 핵심 로직
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 현재 비교하는 문자들 (1-based 인덱스를 0-based로 변환)
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                
                if (c1 == c2) {
                    // ★ 핵심 아이디어 1: 문자가 같으면 이전 LCS에 1을 추가
                    // 왜 dp[i-1][j-1] + 1인가?
                    // - 현재 문자를 포함하지 않은 상태에서의 최대 길이에
                    // - 현재 일치하는 문자 1개를 추가하는 것
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // ★ 핵심 아이디어 2: 문자가 다르면 두 가지 선택지 중 최대값
                    // dp[i-1][j]: str1의 현재 문자를 제외하고 계산한 LCS
                    // dp[i][j-1]: str2의 현재 문자를 제외하고 계산한 LCS
                    // 
                    // 직관적 설명:
                    // "현재 문자가 다르니까, 둘 중 하나는 포기해야 해.
                    //  str1의 현재 문자를 포기하거나, str2의 현재 문자를 포기하거나.
                    //  더 좋은 결과를 주는 쪽을 선택하자!"
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // 최종 답: 두 문자열 전체에 대한 LCS 길이
        return dp[n][m];
    }
    
    /**
     * LCS 실제 문자열을 구하는 메서드 (역추적 사용)
     * 고급 문제에서 실제 LCS를 출력하라고 할 때 사용
     */
    public static String getLCSString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        // 먼저 DP 테이블을 구성 (위와 동일)
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // ★ 역추적(Backtracking)으로 실제 LCS 문자열 구성
        // DP 테이블을 거꾸로 따라가면서 어떤 문자가 LCS에 포함되었는지 찾기
        StringBuilder lcs = new StringBuilder();
        int i = n, j = m;
        
        while (i > 0 && j > 0) {
            char c1 = str1.charAt(i - 1);
            char c2 = str2.charAt(j - 1);
            
            if (c1 == c2) {
                // ★ 문자가 같다 = 이 문자가 LCS에 포함되었다!
                lcs.append(c1);
                i--; j--; // 대각선으로 이동 (둘 다 하나씩 뒤로)
            } else {
                // ★ 문자가 다르다 = 더 큰 값을 가진 방향으로 이동
                // 이 부분이 직관적으로 이해하기 어려운 부분:
                // "DP를 구성할 때 선택했던 방향의 반대로 되돌아간다"
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--; // str1의 문자를 제외했었으므로 str1 쪽으로 이동
                } else {
                    j--; // str2의 문자를 제외했었으므로 str2 쪽으로 이동
                }
            }
        }
        
        // StringBuilder에는 역순으로 들어가므로 뒤집어서 반환
        return lcs.reverse().toString();
    }
    
    /**
     * 공간 최적화된 LCS 길이 구하기
     * 실제 문자열이 필요 없고 길이만 필요할 때 메모리 절약 가능
     * 2차원 배열 대신 1차원 배열 2개만 사용: O(min(N,M)) 공간복잡도
     */
    public static int getLCSLengthOptimized(String str1, String str2) {
        // 더 짧은 문자열을 두 번째로 설정하여 메모리 최적화
        if (str1.length() < str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }
        
        int n = str1.length();
        int m = str2.length();
        
        // ★ 공간 최적화: 이전 행과 현재 행만 저장
        // 어차피 DP에서 현재 위치는 바로 위, 왼쪽, 대각선 위만 참조하므로
        int[] prev = new int[m + 1]; // 이전 행 (dp[i-1][...])
        int[] curr = new int[m + 1]; // 현재 행 (dp[i][...])
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                
                if (c1 == c2) {
                    curr[j] = prev[j - 1] + 1; // 대각선 위 + 1
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]); // 위쪽과 왼쪽 중 최대
                }
            }
            // 현재 행을 이전 행으로 이동 (다음 iteration을 위해)
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[m];
    }
}

/*
 * ===== LCS 알고리즘의 핵심 이해하기 =====
 *
 * 1. 왜 dp[i-1][j-1] + 1인가?
 *    예: str1="AB", str2="AC"에서 A가 일치할 때
 *    - dp[0][0] = 0 (빈 문자열들의 LCS)
 *    - A가 일치하므로 dp[1][1] = dp[0][0] + 1 = 1
 *    → "빈 문자열에서 A 하나 추가"의 의미
 *
 * 2. 왜 Max(dp[i-1][j], dp[i][j-1])인가?
 *    예: str1="AB", str2="CB"에서 A≠C일 때
 *    - str1의 A를 제외: dp[0][1] (빈 문자열 vs "C")
 *    - str2의 C를 제외: dp[1][0] ("A" vs 빈 문자열)  
 *    - 둘 중 더 좋은 선택을 함
 *
 * 3. 역추적은 왜 거꾸로 가는가?
 *    - DP 테이블은 "앞에서부터 최적해"를 구성
 *    - 실제 문자열은 "뒤에서부터 어떤 선택을 했는지" 역추적
 *    - 마치 미로에서 출구에서 시작해서 입구로 되돌아가는 것과 같음
 *
 * ===== 실전 팁 =====
 *
 * 1. 인덱스 실수 주의: 1-based DP vs 0-based 문자열
 * 2. 문자열이 매우 길면 공간 최적화 버전 사용
 * 3. 길이만 필요하면 복잡한 역추적 코드 생략
 * 4. 테스트 케이스: 빈 문자열, 완전히 다른 문자열, 완전히 같은 문자열
 */