package org.example;

import java.util.*;

/*
=== 코딩테스트 필수 기본기 모음 ===
- 알고리즘은 아니지만 코딩테스트에서 자주 필요한 핵심 기법들
- 간단해 보이지만 실제로는 실수하기 쉬운 것들
- 시간복잡도: 대부분 O(1) ~ O(logN), 일부 O(N)
- 외우면 시간 단축에 도움되는 공식과 패턴들

=== 선수 지식 ===
1. 기본 자료형과 배열 조작
2. 문자열 처리 기본
3. 수학 기본 개념 (소수, 최대공약수 등)
4. 비트 연산 기초

=== 코딩테스트에서 이런 기본기가 필요한 문제 유형 ===
1. 구현 문제 - 정확한 조건 처리와 예외 상황 핸들링
2. 수학 문제 - 빠른 거듭제곱, GCD/LCM, 소수 판정
3. 문자열 처리 - 파싱, 변환, 패턴 매칭
4. 시뮬레이션 - 좌표 이동, 회전, 범위 체크
5. 그리디 - 정렬과 우선순위 기반 선택
6. 완전탐색 - 모든 경우의 수 체크할 때 효율적 구현

=== 자주 헷갈리는 포인트들 ===
1. 나머지 연산 vs 나눗셈: % 연산자의 음수 처리
2. 문자열 비교: == vs equals() 차이점
3. 배열 vs 리스트: 고정 크기 vs 가변 크기 선택 기준
4. 비트연산 vs 일반연산: 성능상 이점이 있는 경우와 없는 경우
5. Integer vs int: 박싱/언박싱으로 인한 성능 차이

=== 실수하기 쉬운 접근법 ===
- int 범위 초과하는데 long 안쓰는 경우
- 소수점 계산에서 double 정밀도 문제
- 문자열 연결에서 StringBuilder 안쓰고 + 연산 남발
- 정렬 기준 잘못 설정해서 원하는 결과 못 얻는 경우
- 예외상황 (빈 배열, null, 경계값) 처리 누락
*/

public class 알면좋은기본기 {
    
    public static void main(String[] args) {
        // 실전 시나리오들을 모아서 한 번에 테스트
        
        System.out.println("=== 수학 관련 기본기 ===");
        mathBasics();
        
        System.out.println("\n=== 문자열 처리 기본기 ===");
        stringBasics();
        
        System.out.println("\n=== 배열/리스트 조작 기본기 ===");
        arrayListBasics();
        
        System.out.println("\n=== 좌표/방향 처리 기본기 ===");
        coordinateBasics();
        
        System.out.println("\n=== 비트 연산 기본기 ===");
        bitBasics();
        
        System.out.println("\n=== 정렬/비교 기본기 ===");
        sortBasics();
    }
    
    /**
     * 수학 관련 필수 기본기들
     * 코딩테스트에서 자주 나오지만 실수하기 쉬운 것들
     */
    private static void mathBasics() {
        // 1. 빠른 거듭제곱 (지수가 클 때 필수)
        long base = 2, exp = 10, mod = 1000000007;
        long result = fastPower(base, exp, mod);
        System.out.println("2^10 mod 1000000007 = " + result);
        
        // 2. 최대공약수(GCD)와 최소공배수(LCM)
        int a = 48, b = 18;
        int gcd = gcd(a, b);
        long lcm = lcm(a, b); // int 범위 초과 가능성 있어서 long 사용
        System.out.println("GCD(48,18) = " + gcd + ", LCM(48,18) = " + lcm);
        
        // 3. 소수 판정 (에라토스테네스의 체)
        int n = 100;
        boolean[] isPrime = sieve(n);
        System.out.print("100 이하 소수: ");
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) System.out.print(i + " ");
        }
        System.out.println();
        
        // 4. 팩토리얼과 조합 계산
        int fact5 = factorial(5);
        long comb = combination(5, 2);
        System.out.println("5! = " + fact5 + ", C(5,2) = " + comb);
        
        // 5. 음수 나머지 처리 (자주 실수하는 부분!)
        int negMod = (-7) % 3; // Java에서는 -1이 나옴
        int correctMod = ((-7) % 3 + 3) % 3; // 올바른 양수 나머지는 2
        System.out.println("잘못된 음수 나머지: " + negMod + ", 올바른 처리: " + correctMod);
    }
    
    /**
     * 빠른 거듭제곱 (분할정복 이용)
     * 시간복잡도: O(log exp)
     * base^exp % mod 를 효율적으로 계산
     */
    private static long fastPower(long base, long exp, long mod) {
        long result = 1;
        base %= mod; // 처음부터 모듈러 적용
        
        while (exp > 0) {
            // 지수가 홀수면 현재 base를 결과에 곱함
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            
            // base를 제곱하고 지수를 반으로 줄임
            base = (base * base) % mod;
            exp /= 2;
        }
        
        return result;
    }
    
    /**
     * 유클리드 호제법으로 최대공약수 구하기
     * 시간복잡도: O(log min(a,b))
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * 최소공배수 = (a * b) / gcd(a, b)
     * 주의: a*b가 int 범위를 초과할 수 있어서 long 사용
     */
    private static long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
    }
    
    /**
     * 에라토스테네스의 체로 소수 판정
     * 시간복잡도: O(n log log n)
     * n 이하의 모든 소수를 한 번에 구할 때 효율적
     */
    private static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0과 1은 소수가 아님
        
        // 2부터 √n까지만 확인하면 됨
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                // i의 배수들을 모두 합성수로 표시
                // i*i부터 시작하는 이유: i*2, i*3, ... i*(i-1)은 이미 처리됨
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        return isPrime;
    }
    
    /**
     * 팩토리얼 계산 (재귀 버전)
     * 큰 수에서는 BigInteger 사용 고려
     */
    private static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
    
    /**
     * 조합 nCr 계산
     * 오버플로우 방지를 위해 약분하면서 계산
     */
    private static long combination(int n, int r) {
        if (r > n - r) r = n - r; // nCr = nC(n-r) 이용
        
        long result = 1;
        for (int i = 0; i < r; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }
    
    /**
     * 문자열 처리 필수 기본기들
     */
    private static void stringBasics() {
        String str = "Hello World 123";
        
        // 1. 문자열 뒤집기 (StringBuilder 사용)
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println("뒤집기: " + reversed);
        
        // 2. 문자 빈도 카운팅 (배열 vs HashMap)
        int[] charCount = new int[256]; // ASCII 전체 범위
        for (char c : str.toCharArray()) {
            charCount[c]++;
        }
        System.out.println("'l' 개수: " + charCount['l']);
        
        // 3. 숫자만 추출하기
        String numbersOnly = str.replaceAll("[^0-9]", "");
        System.out.println("숫자만: " + numbersOnly);
        
        // 4. 문자열을 숫자로 안전하게 변환
        String numStr = "123";
        try {
            int num = Integer.parseInt(numStr);
            System.out.println("변환된 숫자: " + num);
        } catch (NumberFormatException e) {
            System.out.println("숫자 변환 실패");
        }
        
        // 5. 문자열 비교 (== vs equals 차이점 주의!)
        String s1 = new String("test");
        String s2 = new String("test");
        System.out.println("== 비교: " + (s1 == s2)); // false
        System.out.println("equals 비교: " + s1.equals(s2)); // true
        
        // 6. StringBuilder로 효율적 문자열 조합
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i).append(" ");
        }
        System.out.println("StringBuilder 결과: " + sb.toString().trim());
    }
    
    /**
     * 배열과 리스트 조작 기본기들
     */
    private static void arrayListBasics() {
        // 1. 배열 초기화 방법들
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[5]; // 모두 0으로 초기화
        Arrays.fill(arr2, -1); // 특정 값으로 초기화
        
        // 2. 2차원 배열 초기화
        int[][] matrix = new int[3][4];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(matrix[i], -1); // 각 행을 -1로 초기화
        }
        
        // 3. 배열 복사 (얕은 복사 vs 깊은 복사)
        int[] original = {1, 2, 3};
        int[] copied = Arrays.copyOf(original, original.length);
        int[] sliced = Arrays.copyOfRange(original, 1, 3); // 인덱스 1~2 복사
        
        // 4. 리스트 초기화와 조작
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        
        // 특정 조건의 원소 제거 (Java 8 Stream 사용)
        list.removeIf(x -> x % 2 == 0); // 짝수 제거
        System.out.println("홀수만 남은 리스트: " + list);
        
        // 5. 배열에서 최대/최소값 찾기
        int[] numbers = {3, 1, 4, 1, 5, 9, 2, 6};
        int max = Arrays.stream(numbers).max().orElse(Integer.MIN_VALUE);
        int min = Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
        System.out.println("최대: " + max + ", 최소: " + min);
        
        // 6. 배열 회전 (오른쪽으로 k칸)
        int[] toRotate = {1, 2, 3, 4, 5};
        int k = 2;
        rotateArray(toRotate, k);
        System.out.println("회전 후: " + Arrays.toString(toRotate));
    }
    
    /**
     * 배열을 오른쪽으로 k칸 회전시키는 함수
     * 시간복잡도: O(n), 공간복잡도: O(1)
     */
    private static void rotateArray(int[] arr, int k) {
        int n = arr.length;
        k %= n; // k가 배열 길이보다 클 수 있음
        
        // 전체 뒤집기 → 앞쪽 k개 뒤집기 → 뒤쪽 n-k개 뒤집기
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }
    
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    /**
     * 좌표와 방향 처리 기본기들
     * 2D 격자에서의 이동, 회전 등
     */
    private static void coordinateBasics() {
        // 1. 4방향 이동 (상하좌우)
        int[] dx = {-1, 1, 0, 0}; // 행 변화량
        int[] dy = {0, 0, -1, 1}; // 열 변화량
        String[] directions = {"위", "아래", "왼쪽", "오른쪽"};
        
        int x = 5, y = 5; // 현재 위치
        System.out.println("현재 위치: (" + x + ", " + y + ")");
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            System.out.println(directions[i] + " 이동: (" + nx + ", " + ny + ")");
        }
        
        // 2. 8방향 이동 (대각선 포함)
        int[] dx8 = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy8 = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        // 3. 범위 체크 함수
        int rows = 10, cols = 10;
        boolean inBounds = isInBounds(3, 7, rows, cols);
        System.out.println("(3,7)이 10x10 격자 안에 있는가: " + inBounds);
        
        // 4. 맨하탄 거리 계산
        int dist = manhattanDistance(0, 0, 3, 4);
        System.out.println("(0,0)에서 (3,4)까지 맨하탄 거리: " + dist);
        
        // 5. 시계방향 90도 회전
        int[] rotated = rotateClockwise(3, 4);
        System.out.println("(3,4)를 시계방향 90도 회전: (" + rotated[0] + ", " + rotated[1] + ")");
    }
    
    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    
    private static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    /**
     * 좌표를 시계방향 90도 회전
     * (x, y) → (y, -x) 변환 공식
     */
    private static int[] rotateClockwise(int x, int y) {
        return new int[]{y, -x};
    }
    
    /**
     * 비트 연산 기본기들
     * 빠른 연산과 메모리 절약에 유용
     */
    private static void bitBasics() {
        int n = 12; // 1100(2진수)
        
        // 1. 특정 비트 확인
        boolean bit2Set = (n & (1 << 2)) != 0; // 2번째 비트 확인
        System.out.println("12의 2번째 비트가 설정되어 있는가: " + bit2Set);
        
        // 2. 특정 비트 설정/해제
        int setBit = n | (1 << 1); // 1번째 비트 설정
        int clearBit = n & ~(1 << 3); // 3번째 비트 해제
        System.out.println("1번째 비트 설정: " + setBit + ", 3번째 비트 해제: " + clearBit);
        
        // 3. 2의 거듭제곱 판정
        boolean isPowerOf2 = (n & (n - 1)) == 0 && n > 0;
        System.out.println("12가 2의 거듭제곱인가: " + isPowerOf2);
        
        // 4. 설정된 비트 개수 (Population Count)
        int bitCount = Integer.bitCount(n);
        System.out.println("12에서 설정된 비트 개수: " + bitCount);
        
        // 5. 빠른 곱셈/나눗셈 (2의 거듭제곱인 경우)
        int multiplyBy4 = n << 2; // n * 4
        int divideBy2 = n >> 1;   // n / 2
        System.out.println("12 * 4 = " + multiplyBy4 + ", 12 / 2 = " + divideBy2);
    }
    
    /**
     * 정렬과 비교 관련 기본기들
     */
    private static void sortBasics() {
        // 1. 기본 정렬
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
        Arrays.sort(arr);
        System.out.println("기본 정렬: " + Arrays.toString(arr));
        
        // 2. 객체 정렬 (Comparator 사용)
        List<int[]> points = Arrays.asList(
            new int[]{1, 3}, new int[]{2, 1}, new int[]{0, 5}
        );
        
        // x좌표 기준 정렬, 같으면 y좌표 기준
        points.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        System.out.print("좌표 정렬: ");
        for (int[] point : points) {
            System.out.print("(" + point[0] + "," + point[1] + ") ");
        }
        System.out.println();
        
        // 3. 내림차순 정렬
        Integer[] numbers = {3, 1, 4, 1, 5, 9, 2, 6};
        Arrays.sort(numbers, Collections.reverseOrder());
        System.out.println("내림차순: " + Arrays.toString(numbers));
        
        // 4. 부분 정렬 (특정 구간만)
        int[] partialSort = {5, 2, 8, 1, 9, 3};
        Arrays.sort(partialSort, 1, 4); // 인덱스 1~3만 정렬
        System.out.println("부분 정렬: " + Arrays.toString(partialSort));
        
        // 5. 이진 탐색 (정렬된 배열에서)
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = Arrays.binarySearch(sorted, 5);
        System.out.println("5의 위치: " + index);
        
        // 6. Upper Bound, Lower Bound (직접 구현)
        int target = 3;
        int[] withDuplicates = {1, 2, 3, 3, 3, 4, 5};
        int lowerBound = lowerBound(withDuplicates, target);
        int upperBound = upperBound(withDuplicates, target);
        System.out.println("3의 Lower Bound: " + lowerBound + ", Upper Bound: " + upperBound);
    }
    
    /**
     * Lower Bound: target 이상인 첫 번째 위치
     */
    private static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Upper Bound: target보다 큰 첫 번째 위치
     */
    private static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}