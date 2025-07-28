package org.example;

import java.util.*;

/*
* ===== 코딩테스트 정수론 핵심 알고리즘 모음 =====
* 
* 【기본 원리】
* - 정수의 성질과 관계를 이용한 효율적 계산
* - 소수, 약수, 배수 등의 기본 개념 활용
* - 모듈러 연산과 거듭제곱의 최적화
* - 수학적 성질을 코드로 구현하는 능력
* 
* 【시간복잡도】
* - 소수 판별: O(√n) 기본, O(log log n) 에라토스테네스 체
* - 최대공약수(GCD): O(log min(a,b)) 유클리드 호제법
* - 거듭제곱: O(log n) 분할정복, O(n) 단순반복
* - 약수 구하기: O(√n)
* - 팩토리얼: O(n)
* 
* 【선수 지식】
* - 나머지 연산의 성질 ((a+b)%m = (a%m + b%m)%m)
* - 소수와 합성수의 정의
* - 유클리드 호제법의 원리
* - 분할정복의 개념
* - 비트 연산 기초 (거듭제곱 최적화용)
* 
* 【코딩테스트 출제 유형】
* 1. 소수 관련: 소수 판별, 소수 개수 세기, 소인수분해
* 2. 약수/배수: 최대공약수, 최소공배수, 약수의 개수
* 3. 모듈러 연산: 큰 수 나머지, 거듭제곱 나머지
* 4. 조합론: 팩토리얼, 조합, 순열 계산
* 5. 수열: 피보나치, 등차/등비수열
* 6. 진법 변환: 2진법, 16진법 등
* 
* 【비슷한 알고리즘과 구분점】
* ⚠️ 소수 판별 vs 소인수분해
*   - 소수 판별: 소수인지만 확인 (boolean)
*   - 소인수분해: 소수들의 곱으로 분해 (List<Integer>)
* 
* ⚠️ GCD vs LCM 계산 실수
*   - GCD: 두 수의 공통 약수 중 최대값
*   - LCM: 두 수의 공통 배수 중 최소값, LCM = a*b/GCD(a,b)
* 
* ⚠️ 일반 거듭제곱 vs 모듈러 거듭제곱
*   - 일반: 결과가 매우 커질 수 있음 (오버플로우 위험)
*   - 모듈러: 매 단계마다 나머지 연산으로 크기 제한
* 
* ⚠️ 에라토스테네스 체 vs 단순 소수 판별
*   - 단일 수 확인: 단순 판별법이 더 효율적
*   - 범위 내 모든 소수: 에라토스테네스 체가 우수
* 
* ⚠️ 재귀 vs 반복문 선택
*   - 팩토리얼, 피보나치: 반복문이 스택 오버플로우 방지
*   - GCD: 재귀가 더 직관적이지만 반복문도 가능
*/

public class 정수론 {
    
    public static void main(String[] args) {
        // 실전 코딩테스트 방식 - 직접 입력으로 테스트
        testAllMethods();
    }
    
    private static void testAllMethods() {
        System.out.println("=== 정수론 알고리즘 테스트 ===");
        System.out.println("1. 소수 판별 (17): " + isPrime(17));
        System.out.println("2. GCD (48, 18): " + gcd(48, 18));
        System.out.println("3. LCM (12, 8): " + lcm(12, 8));
        System.out.println("4. 거듭제곱 (2^10): " + power(2, 10));
        System.out.println("5. 모듈러 거듭제곱 (2^10 % 7): " + modPower(2, 10, 7));
        System.out.println("6. 약수 개수 (12): " + countDivisors(12));
        System.out.println("7. 팩토리얼 (5): " + factorial(5));
        System.out.println("8. 피보나치 (10): " + fibonacci(10));
    }
    
    /**
     * 1. 소수 판별 - 가장 기본적인 정수론 알고리즘
     * 2보다 큰 자연수 중에서 1과 자기 자신만을 약수로 가지는 수
     */
    public static boolean isPrime(int n) {
        // ★ 2보다 작은 수는 소수가 아님
        if (n < 2) return false;
        
        // ★ 2는 유일한 짝수 소수
        if (n == 2) return true;
        
        // ★ 2를 제외한 모든 짝수는 소수가 아님
        if (n % 2 == 0) return false;
        
        // ★ 핵심 최적화: √n까지만 확인하면 됨
        // 만약 n = a × b이고 a ≤ b라면, a ≤ √n ≤ b
        // 따라서 √n까지만 약수를 찾으면 모든 약수 쌍을 확인 가능
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false; // 약수 발견시 합성수
            }
        }
        
        return true; // 약수가 없으면 소수
    }
    
    /**
     * 2. 에라토스테네스의 체 - 범위 내 모든 소수 찾기
     * n 이하의 모든 소수를 효율적으로 구하는 알고리즘
     */
    public static List<Integer> sieveOfEratosthenes(int n) {
        // ★ boolean 배열로 소수 여부 표시 (true = 소수)
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // 처음에는 모든 수를 소수로 가정
        
        isPrime[0] = isPrime[1] = false; // 0과 1은 소수가 아님
        
        // ★ 2부터 √n까지만 확인하면 됨
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                // ★ i가 소수라면 i의 배수들은 모두 합성수
                // i² 부터 시작하는 이유: i×2, i×3, ... i×(i-1)은 이미 처리됨
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // ★ 소수들만 리스트로 수집
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        
        return primes;
    }
    
    /**
     * 3. 최대공약수(GCD) - 유클리드 호제법
     * 두 정수의 공통 약수 중 가장 큰 수
     */
    public static int gcd(int a, int b) {
        // ★ 유클리드 호제법의 핵심 아이디어
        // gcd(a, b) = gcd(b, a % b)
        // 나머지가 0이 될 때까지 반복하면 GCD를 구할 수 있음
        
        while (b != 0) {
            int temp = b;
            b = a % b;  // 나머지 계산
            a = temp;   // a를 이전의 b로 업데이트
        }
        
        return a; // b가 0이 되었을 때의 a가 GCD
        
        /* ★ 재귀 버전 (더 직관적이지만 스택 사용)
        if (b == 0) return a;
        return gcd(b, a % b);
        */
    }
    
    /**
     * 4. 최소공배수(LCM) - GCD를 이용한 효율적 계산
     * 두 정수의 공통 배수 중 가장 작은 수
     */
    public static long lcm(int a, int b) {
        // ★ 수학적 공식: LCM(a, b) = (a × b) / GCD(a, b)
        // 직접 계산하면 시간복잡도가 높지만 이 공식으로 O(log n) 달성
        
        // ★ 오버플로우 방지를 위해 먼저 나누기 수행
        return (long) a / gcd(a, b) * b;
        // (a * b) / gcd(a, b) 순서로 하면 중간 결과가 오버플로우 가능
    }
    
    /**
     * 5. 거듭제곱 - 분할정복을 이용한 빠른 거듭제곱
     * a^n을 효율적으로 계산 (O(log n))
     */
    public static long power(long base, int exp) {
        // ★ 기저 조건
        if (exp == 0) return 1;
        if (exp == 1) return base;
        
        // ★ 분할정복의 핵심 아이디어
        // a^n = (a^(n/2))² (n이 짝수일 때)
        // a^n = a × (a^(n/2))² (n이 홀수일 때)
        
        long half = power(base, exp / 2);
        long result = half * half;
        
        // ★ 지수가 홀수인 경우 base를 한 번 더 곱함
        if (exp % 2 == 1) {
            result *= base;
        }
        
        return result;
    }
    
    /**
     * 6. 모듈러 거듭제곱 - 큰 수의 거듭제곱을 나머지로 계산
     * (a^n) % m을 오버플로우 없이 계산
     */
    public static long modPower(long base, long exp, long mod) {
        long result = 1;
        base %= mod; // ★ 처음부터 모듈러 연산 적용
        
        // ★ 비트 단위로 지수를 처리하는 효율적 방법
        while (exp > 0) {
            // ★ 현재 비트가 1이면 결과에 현재 base를 곱함
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            
            // ★ base를 제곱하고 지수를 절반으로 줄임
            base = (base * base) % mod;
            exp /= 2;
        }
        
        return result;
    }
    
    /**
     * 7. 약수의 개수 구하기
     * 주어진 수의 모든 약수 개수를 O(√n) 시간에 계산
     */
    public static int countDivisors(int n) {
        int count = 0;
        
        // ★ √n까지만 확인하는 최적화
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                // ★ i가 약수라면
                if (i * i == n) {
                    count++; // 완전제곱수인 경우 i만 카운트
                } else {
                    count += 2; // i와 n/i 둘 다 약수
                }
            }
        }
        
        return count;
    }
    
    /**
     * 8. 모든 약수 구하기
     * 약수의 개수뿐만 아니라 실제 약수들의 리스트를 반환
     */
    public static List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        
        // ★ √n까지 확인하면서 약수 쌍을 찾음
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i); // 작은 약수 추가
                
                // ★ 완전제곱수가 아닌 경우에만 큰 약수도 추가
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        
        // ★ 정렬해서 반환 (필요에 따라 생략 가능)
        Collections.sort(divisors);
        return divisors;
    }
    
    /**
     * 9. 팩토리얼 계산 - 반복문 버전 (스택 오버플로우 방지)
     * n! = n × (n-1) × ... × 2 × 1
     */
    public static long factorial(int n) {
        // ★ 음수나 너무 큰 수는 처리하지 않음
        if (n < 0) return -1;
        if (n <= 1) return 1;
        
        long result = 1;
        
        // ★ 2부터 n까지 순차적으로 곱함
        // 재귀보다 반복문이 메모리 효율적
        for (int i = 2; i <= n; i++) {
            result *= i;
            
            // ★ 오버플로우 체크 (필요시)
            if (result < 0) {
                // 오버플로우 발생시 처리
                break;
            }
        }
        
        return result;
    }
    
    /**
     * 10. 피보나치 수 계산 - 반복문 버전 (효율적)
     * F(n) = F(n-1) + F(n-2), F(0) = 0, F(1) = 1
     */
    public static long fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        // ★ 동적 계획법 아이디어: 이전 두 값만 저장
        long prev2 = 0; // F(0)
        long prev1 = 1; // F(1)
        long current;
        
        // ★ F(2)부터 F(n)까지 순차 계산
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1; // 값 업데이트
            prev1 = current;
        }
        
        return prev1;
        
        /* ★ 메모이제이션 버전 (더 큰 수까지 가능)
        static Map<Integer, Long> memo = new HashMap<>();
        
        if (memo.containsKey(n)) return memo.get(n);
        
        long result;
        if (n <= 1) {
            result = n;
        } else {
            result = fibonacci(n-1) + fibonacci(n-2);
        }
        
        memo.put(n, result);
        return result;
        */
    }
    
    /**
     * 11. 소인수분해
     * 주어진 수를 소수들의 곱으로 나타내기
     */
    public static List<Integer> primeFactorization(int n) {
        List<Integer> factors = new ArrayList<>();
        
        // ★ 2로 나누어지는 만큼 모두 처리
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }
        
        // ★ 3부터 √n까지 홀수만 확인
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        
        // ★ 남은 n이 2보다 크면 그 자체가 소수
        if (n > 2) {
            factors.add(n);
        }
        
        return factors;
    }
    
    /**
     * 12. 조합 계산 - nCr (n개 중 r개 선택)
     * 오버플로우를 최소화하는 효율적 구현
     */
    public static long combination(int n, int r) {
        // ★ 기본 조건 검사
        if (r > n || r < 0) return 0;
        if (r == 0 || r == n) return 1;
        
        // ★ 대칭 성질 이용: nCr = nC(n-r)
        // 계산량을 줄이기 위해 작은 쪽을 선택
        r = Math.min(r, n - r);
        
        long result = 1;
        
        // ★ nCr = n×(n-1)×...×(n-r+1) / r×(r-1)×...×1
        // 오버플로우를 방지하기 위해 곱하고 나누기를 번갈아 수행
        for (int i = 0; i < r; i++) {
            result = result * (n - i) / (i + 1);
        }
        
        return result;
    }
    
    /**
     * 13. 진법 변환 - 10진수를 다른 진법으로 변환
     * 2진법, 8진법, 16진법 등으로 변환
     */
    public static String convertToBase(int number, int base) {
        if (number == 0) return "0";
        
        StringBuilder result = new StringBuilder();
        String digits = "0123456789ABCDEF"; // 16진법까지 지원
        
        // ★ 음수 처리
        boolean negative = number < 0;
        number = Math.abs(number);
        
        // ★ 나머지를 이용한 진법 변환
        while (number > 0) {
            result.append(digits.charAt(number % base));
            number /= base;
        }
        
        if (negative) result.append('-');
        
        // ★ 역순으로 만들어졌으므로 뒤집기
        return result.reverse().toString();
    }
}