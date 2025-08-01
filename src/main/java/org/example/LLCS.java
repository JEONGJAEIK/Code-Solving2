package org.example;

import java.util.*;

/*
 * LLCS (Longest Lexicographic Common Subsequence) 알고리즘
 * 
 * === 알고리즘 소개 ===
 * LLCS는 두 문자열에서 공통 부분수열 중 가장 긴 것들 중에서 사전순으로 가장 앞서는 것을 찾는 알고리즘입니다.
 * 일반적인 LCS는 길이만 구하거나 아무 LCS나 구하지만, LLCS는 사전순 최소를 보장합니다.
 * 
 * === 시간복잡도 ===
 * - 시간복잡도: O(m*n*min(m,n)) - m,n은 각 문자열의 길이
 * - 공간복잡도: O(m*n*min(m,n)) - DP 테이블과 경로 저장
 * 
 * === 선수 지식 ===
 * 1. 기본 LCS 알고리즘과 동적 계획법
 * 2. 문자열 비교와 사전순 정렬 개념
 * 3. 백트래킹을 통한 경로 복원
 * 
 * === 코딩테스트 출제 유형 ===
 * 1. 문자열 처리 고급 문제
 * 2. 두 DNA 서열에서 공통 패턴 찾기 (생물정보학)
 * 3. 버전 관리 시스템에서 공통 부분 찾기
 * 4. 텍스트 비교 및 차이점 분석
 * 5. 게임에서 플레이어 행동 패턴 분석
 * 
 * === 비슷한 알고리즘과의 차이점 ===
 * 1. LCS vs LLCS: LCS는 길이나 임의의 수열, LLCS는 사전순 최소 보장
 * 2. LIS vs LLCS: LIS는 단일 수열의 증가, LLCS는 두 수열의 공통
 * 3. Edit Distance vs LLCS: Edit Distance는 변경 비용, LLCS는 공통 부분
 * 4. 주의: 단순히 두 문자열을 정렬해서 LCS 구하면 원래 순서 정보 손실!
 * 5. Palindrome LCS와 혼동 주의: 그것은 하나 문자열의 회문 부분수열
 */

public class LLCS {
    
    public static void main(String[] args) {
        LLCS solution = new LLCS();
        
        // 테스트 케이스들 - 실제 코딩테스트에서는 이 부분만 수정하면 됨
        String str1 = "ABCDGH";
        String str2 = "AEDFHR";
        
        String result = solution.findLLCS(str1, str2);
        System.out.println("LLCS: " + result);
        System.out.println("Length: " + result.length());
    }
    
    public String findLLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        // dp[i][j]는 str1[0..i-1]과 str2[0..j-1]의 모든 LCS들을 저장
        // 각 위치마다 사전순으로 정렬된 Set을 유지
        Set<String>[][] dp = new Set[m + 1][n + 1];
        
        // DP 테이블 초기화 - 빈 문자열로 시작
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = new TreeSet<>(); // TreeSet으로 자동 사전순 정렬
            }
        }
        
        // 기저 조건: 빈 문자열과의 LCS는 빈 문자열
        for (int i = 0; i <= m; i++) {
            dp[i][0].add("");
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j].add("");
        }
        
        // DP 테이블 채우기 - 핵심 로직
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 현재 문자가 일치하는 경우
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    // 대각선 위치의 모든 LCS에 현재 문자를 추가
                    for (String lcs : dp[i-1][j-1]) {
                        dp[i][j].add(lcs + str1.charAt(i-1));
                    }
                }
                
                // 현재 문자가 일치하지 않는 경우
                // 위쪽과 왼쪽에서 최대 길이인 LCS들을 가져옴
                int maxLen = 0;
                Set<String> candidates = new HashSet<>();
                
                // 위쪽에서 가져올 후보들
                for (String lcs : dp[i-1][j]) {
                    if (lcs.length() > maxLen) {
                        maxLen = lcs.length();
                        candidates.clear();
                        candidates.add(lcs);
                    } else if (lcs.length() == maxLen) {
                        candidates.add(lcs);
                    }
                }
                
                // 왼쪽에서 가져올 후보들
                for (String lcs : dp[i][j-1]) {
                    if (lcs.length() > maxLen) {
                        maxLen = lcs.length();
                        candidates.clear();
                        candidates.add(lcs);
                    } else if (lcs.length() == maxLen) {
                        candidates.add(lcs);
                    }
                }
                
                // 문자가 일치할 때 생성된 LCS와 비교
                int currentMaxLen = 0;
                if (!dp[i][j].isEmpty()) {
                    currentMaxLen = dp[i][j].iterator().next().length();
                }
                
                // 더 긴 LCS가 있다면 기존 것들을 제거하고 새로 추가
                if (maxLen > currentMaxLen) {
                    dp[i][j].clear();
                    dp[i][j].addAll(candidates);
                } else if (maxLen == currentMaxLen) {
                    // 같은 길이면 모두 추가 (TreeSet이 자동으로 중복 제거 및 정렬)
                    dp[i][j].addAll(candidates);
                }
            }
        }
        
        // 결과: 가장 긴 LCS들 중 사전순으로 첫 번째
        // TreeSet의 특성상 이미 정렬되어 있으므로 첫 번째 원소가 답
        return dp[m][n].isEmpty() ? "" : dp[m][n].iterator().next();
    }
    
    /*
     * 최적화된 버전 - 메모리 사용량을 줄이고 사전순 최소만 추적
     * 실제 코딩테스트에서는 이 버전이 더 실용적
     */
    public String findLLCSOptimized(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        // dp[i][j]는 길이 정보만 저장
        int[][] length = new int[m + 1][n + 1];
        
        // 길이 계산 - 표준 LCS 알고리즘
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    length[i][j] = length[i-1][j-1] + 1;
                } else {
                    // 위쪽과 왼쪽 중 더 큰 값 선택
                    length[i][j] = Math.max(length[i-1][j], length[i][j-1]);
                }
            }
        }
        
        // 백트래킹으로 사전순 최소 LCS 구성
        // 뒤에서부터 역추적하면서 사전순으로 가장 작은 경로 선택
        StringBuilder result = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            // 현재 문자가 일치하는 경우 - LCS에 포함
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                result.append(str1.charAt(i-1));
                i--;
                j--;
            }
            // 일치하지 않는 경우 - 어느 방향으로 갈지 결정
            else {
                // 위쪽으로 갈 때와 왼쪽으로 갈 때 길이가 같다면
                // 사전순으로 더 작은 문자가 나오는 방향 선택
                if (length[i-1][j] == length[i][j-1]) {
                    // 양쪽 경로에서 나올 수 있는 다음 문자들을 비교
                    char upChar = findNextChar(str1, str2, i-1, j, length);
                    char leftChar = findNextChar(str1, str2, i, j-1, length);
                    
                    // 사전순으로 더 작은 문자가 나오는 방향 선택
                    if (upChar <= leftChar) {
                        i--; // 위쪽으로
                    } else {
                        j--; // 왼쪽으로
                    }
                }
                // 길이가 다르면 더 긴 쪽으로
                else if (length[i-1][j] > length[i][j-1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        
        // 역순으로 구성했으므로 뒤집기
        return result.reverse().toString();
    }
    
    /*
     * 주어진 위치에서 LCS 경로상 다음에 나올 문자를 찾는 헬퍼 함수
     * 사전순 최소 경로 선택을 위해 필요
     */
    private char findNextChar(String str1, String str2, int i, int j, int[][] length) {
        // 현재 위치에서 역추적하여 다음 LCS 문자 찾기
        while (i > 0 && j > 0) {
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                return str1.charAt(i-1); // 다음 LCS 문자 발견
            }
            
            // 더 긴 경로 방향으로 계속 탐색
            if (length[i-1][j] >= length[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return Character.MAX_VALUE; // LCS가 끝난 경우
    }
}