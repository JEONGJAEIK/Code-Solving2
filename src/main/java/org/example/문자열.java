package org.example;

import java.util.*;

/*
* ===== 코딩테스트 문자열 문제 핵심 구현법 모음 =====
* 
* 【기본 원리】
* - 문자열 조작, 검색, 변환 등의 다양한 패턴을 효율적으로 처리
* - StringBuilder vs String 선택의 중요성
* - 문자열 비교와 해싱 기법 활용
* - 정규표현식보다는 직접 구현이 코딩테스트에서 유리
* 
* 【시간복잡도】
* - 문자열 연결: String += O(n²), StringBuilder O(n)
* - 부분문자열 검색: KMP O(n+m), 단순검색 O(nm)
* - 문자열 정렬: O(n log n)
* - 해시맵 활용 문자 카운팅: O(n)
* 
* 【선수 지식】
* - StringBuilder와 String의 차이점 (가변 vs 불변)
* - char 배열과 문자열 변환
* - 아스키 코드와 문자 연산
* - HashMap/HashSet 활용법
* - 정렬 알고리즘 기본 지식
* 
* 【코딩테스트 출제 유형】
* 1. 문자열 변환/조작 (뒤집기, 회전, 치환)
* 2. 패턴 매칭 (부분문자열 찾기, KMP)
* 3. 문자 빈도수 계산 (아나그램, 그룹화)
* 4. 문자열 파싱 (구분자로 분리, 숫자 추출)
* 5. 문자열 압축/암호화
* 6. 최장공통부분수열(LCS), 편집거리
* 
* 【비슷한 알고리즘과 구분점】
* ⚠️ 배열 vs 문자열 조작 혼동
*   - 문자열: 불변객체, StringBuilder 필요
*   - 배열: 직접 인덱스 수정 가능
* 
* ⚠️ 해시맵 vs 배열 카운팅 선택 실수
*   - 영문자만: int[26] 배열이 더 빠름
*   - 유니코드: HashMap<Character, Integer> 사용
* 
* ⚠️ 정규표현식 vs 직접구현
*   - 코딩테스트: 직접구현이 더 명확하고 빠름
*   - 정규표현식: 실무에서는 유용하지만 면접에서 지양
* 
* ⚠️ KMP vs 단순 문자열 검색
*   - 짧은 패턴: 단순검색이 구현 쉽고 실제로 빠를 수 있음
*   - 긴 패턴: KMP가 이론적으로 우수
*/

public class 문자열 {
    
    public static void main(String[] args) {
        // 실전 코딩테스트 방식 - 직접 입력으로 테스트
        testAllMethods();
    }
    
    private static void testAllMethods() {
        String str1 = "listen";
        String str2 = "silent";
        String text = "abcabcabcabc";
        String pattern = "abcab";
        String mixed = "a1b2c3d4";
        
        System.out.println("=== 문자열 구현법 테스트 ===");
        System.out.println("1. 아나그램 검사: " + isAnagram(str1, str2));
        System.out.println("2. 문자열 뒤집기: " + reverseString("hello"));
        System.out.println("3. 패턴 검색: " + findPattern(text, pattern));
        System.out.println("4. 문자 빈도수: " + getCharFrequency("programming"));
        System.out.println("5. 숫자 추출: " + extractNumbers(mixed));
    }
    
    /**
     * 1. 아나그램 검사 - 문자 빈도수 비교의 핵심 패턴
     * 두 문자열이 같은 문자들로 구성되어 있는지 확인
     */
    public static boolean isAnagram(String s1, String s2) {
        // 길이가 다르면 아나그램이 될 수 없음
        if (s1.length() != s2.length()) return false;
        
        // ★ 영문 소문자만 가정할 때는 배열이 HashMap보다 빠름
        // 알파벳 26개에 대한 카운팅 배열
        int[] count = new int[26];
        
        // ★ 한 번의 반복으로 두 문자열 처리하는 효율적 방법
        for (int i = 0; i < s1.length(); i++) {
            // 첫 번째 문자열의 문자는 증가
            count[s1.charAt(i) - 'a']++;
            // 두 번째 문자열의 문자는 감소
            count[s2.charAt(i) - 'a']--;
        }
        
        // ★ 모든 카운트가 0이면 아나그램
        // 증가와 감소가 정확히 상쇄되었다는 의미
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
    
    /**
     * 2. 문자열 뒤집기 - StringBuilder의 핵심 활용
     * String += 방식보다 월등히 빠른 성능
     */
    public static String reverseString(String s) {
        // ★ StringBuilder 사용 이유: String은 불변객체
        // String += 방식은 매번 새 객체 생성으로 O(n²) 시간복잡도
        StringBuilder sb = new StringBuilder();
        
        // ★ 뒤에서부터 앞으로 순회하며 문자 추가
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        
        return sb.toString();
        
        // ★ 더 간단한 방법: StringBuilder의 내장 reverse() 메소드
        // return new StringBuilder(s).reverse().toString();
    }
    
    /**
     * 3. 문자열 패턴 검색 - 단순 검색 방식
     * KMP보다 구현이 쉽고 짧은 패턴에서는 실제로 더 빠를 수 있음
     */
    public static int findPattern(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();
        
        // ★ 검색 범위 최적화: 불필요한 비교 제거
        // text 끝 부분에서 pattern 길이만큼은 시작점이 될 수 없음
        for (int i = 0; i <= textLen - patternLen; i++) {
            int j;
            
            // ★ 현재 위치에서 패턴과 일치하는지 확인
            for (j = 0; j < patternLen; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break; // 불일치 발견시 즉시 중단
                }
            }
            
            // ★ 모든 문자가 일치했다면 패턴 발견
            if (j == patternLen) {
                return i; // 첫 번째 발견 위치 반환
            }
        }
        
        return -1; // 찾지 못함
    }
    
    /**
     * 4. 문자 빈도수 계산 - HashMap 활용의 전형적 패턴
     * 다양한 문자(유니코드)를 다룰 때 필수적인 기법
     */
    public static Map<Character, Integer> getCharFrequency(String s) {
        Map<Character, Integer> frequency = new HashMap<>();
        
        // ★ getOrDefault() 활용으로 코드 간소화
        // 기존 값이 있으면 가져오고, 없으면 0을 기본값으로 사용
        for (char c : s.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        
        return frequency;
        
        /* ★ 전통적인 방식 (참고용)
        for (char c : s.toCharArray()) {
            if (frequency.containsKey(c)) {
                frequency.put(c, frequency.get(c) + 1);
            } else {
                frequency.put(c, 1);
            }
        }
        */
    }
    
    /**
     * 5. 문자열에서 숫자 추출 - 파싱의 기본 패턴
     * 정규표현식보다 직관적이고 코딩테스트에서 선호되는 방식
     */
    public static List<Integer> extractNumbers(String s) {
        List<Integer> numbers = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();
        
        // ★ 문자 하나씩 순회하며 숫자 문자 구분
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 숫자 문자면 현재 숫자에 추가
                currentNumber.append(c);
            } else {
                // 숫자가 아닌 문자를 만나면 지금까지의 숫자를 완성
                if (currentNumber.length() > 0) {
                    numbers.add(Integer.parseInt(currentNumber.toString()));
                    currentNumber.setLength(0); // StringBuilder 초기화
                }
            }
        }
        
        // ★ 마지막 숫자 처리 (문자열 끝이 숫자인 경우)
        if (currentNumber.length() > 0) {
            numbers.add(Integer.parseInt(currentNumber.toString()));
        }
        
        return numbers;
    }
    
    /**
     * 6. 문자열 압축 - 연속된 문자 개수 세기
     * "aaabbcc" → "a3b2c2" 형태로 변환
     */
    public static String compressString(String s) {
        if (s == null || s.length() <= 1) return s;
        
        StringBuilder compressed = new StringBuilder();
        char currentChar = s.charAt(0);
        int count = 1;
        
        // ★ 인덱스 1부터 시작하여 이전 문자와 비교
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == currentChar) {
                count++; // 같은 문자면 카운트 증가
            } else {
                // 다른 문자를 만나면 지금까지의 결과를 압축 문자열에 추가
                compressed.append(currentChar).append(count);
                currentChar = s.charAt(i); // 새로운 문자로 변경
                count = 1; // 카운트 초기화
            }
        }
        
        // ★ 마지막 문자 그룹 처리
        compressed.append(currentChar).append(count);
        
        // ★ 압축된 문자열이 원본보다 짧을 때만 반환
        return compressed.length() < s.length() ? compressed.toString() : s;
    }
    
    /**
     * 7. 부분 문자열의 모든 순열 생성 - 백트래킹 응용
     * 문자열 조합 문제에서 자주 사용되는 패턴
     */
    public static List<String> getPermutations(String s) {
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[s.length()];
        StringBuilder current = new StringBuilder();
        
        backtrack(s, used, current, result);
        return result;
    }
    
    private static void backtrack(String s, boolean[] used, 
                                 StringBuilder current, List<String> result) {
        // ★ 기저 조건: 모든 문자를 사용했으면 결과에 추가
        if (current.length() == s.length()) {
            result.add(current.toString());
            return;
        }
        
        // ★ 각 위치의 문자를 선택하는 백트래킹
        for (int i = 0; i < s.length(); i++) {
            if (!used[i]) {
                // 선택
                used[i] = true;
                current.append(s.charAt(i));
                
                // 재귀 호출
                backtrack(s, used, current, result);
                
                // ★ 백트래킹: 선택 취소
                used[i] = false;
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
    
    /**
     * 8. 최장 공통 부분 문자열(LCS) - 동적 계획법 활용
     * 두 문자열의 가장 긴 공통 부분 문자열 길이 구하기
     */
    public static int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // ★ DP 테이블: dp[i][j] = s1[0..i-1]과 s2[0..j-1]의 LCS 길이
        int[][] dp = new int[m + 1][n + 1];
        
        // ★ 바텀업 방식으로 DP 테이블 채우기
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // 문자가 같으면 대각선 값 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 다르면 위쪽 또는 왼쪽 중 큰 값
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 9. 문자열 회전 검사 - 연결 문자열 활용한 트릭
     * s1이 s2를 회전시킨 결과인지 확인
     * 예: "waterbottle"과 "erbottlewat"
     */
    public static boolean isRotation(String s1, String s2) {
        // 길이가 다르면 회전 불가능
        if (s1.length() != s2.length() || s1.length() == 0) {
            return false;
        }
        
        // ★ 핵심 아이디어: s1 + s1 문자열에는 s1의 모든 회전 형태가 포함됨
        // "waterbottle" + "waterbottle" = "waterbottlewaterbottle"
        // 이 안에 "erbottlewat"가 부분 문자열로 존재하면 회전된 것
        String doubled = s1 + s1;
        return doubled.contains(s2);
    }
    
    /**
     * 10. 유니크 문자 확인 - 비트마스킹 또는 배열 활용
     * 문자열의 모든 문자가 고유한지 확인
     */
    public static boolean hasUniqueChars(String s) {
        // ★ ASCII 문자 가정 시 배열 활용 (가장 빠름)
        if (s.length() > 128) return false; // 비둘기집 원리
        
        boolean[] charSet = new boolean[128];
        
        for (char c : s.toCharArray()) {
            if (charSet[c]) {
                return false; // 이미 등장한 문자
            }
            charSet[c] = true;
        }
        
        return true;
        
        /* ★ HashSet 활용 방식 (더 범용적)
        Set<Character> seen = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!seen.add(c)) return false;
        }
        return true;
        */
    }
}