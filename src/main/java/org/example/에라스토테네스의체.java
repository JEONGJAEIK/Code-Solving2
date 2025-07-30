package org.example;

import java.util.*;

/*
 * 에라스토테네스의 체 (Sieve of Eratosthenes) - 코딩테스트 필수 알고리즘
 *
 * 【기본 원리】
 * - 주어진 범위 내의 모든 소수를 효율적으로 찾는 알고리즘
 * - 2부터 시작하여 각 소수의 배수들을 차례로 제거하는 방식
 * - 체로 거르듯이 합성수를 걸러내고 소수만 남기는 개념
 *
 * 【시간복잡도】
 * - O(N log log N) : 일반적인 소수 판별 O(N√N)보다 훨씬 효율적
 * - 공간복잡도: O(N) - 범위만큼의 boolean 배열 필요
 *
 * 【사전 지식】
 * - 소수의 정의: 1과 자기 자신으로만 나누어지는 2 이상의 자연수
 * - 합성수: 소수가 아닌 자연수 (1 제외)
 * - 배열과 반복문에 대한 기본 이해
 *
 * 【코딩테스트 출제 유형】
 * 1. 직접 출제: "N 이하의 소수 개수 구하기", "K번째 소수 찾기"
 * 2. 응용 문제: 소수 조건이 포함된 수학 문제들
 * 3. 최적화 문제: 대용량 데이터에서 소수 관련 계산
 * 4. DP + 소수: 소수를 이용한 동적계획법 문제
 * 5. 그래프 + 소수: 정점이나 간선이 소수 조건을 만족하는 문제
 *
 * 【혼동하기 쉬운 유사 알고리즘들】
 *
 * 1. 단순 소수 판별 (isPrime 함수)
 *    - 착각 포인트: 하나의 수만 판별할 때는 이것이 더 효율적
 *    - 에라스토테네스 체는 "범위 내 모든 소수"를 구할 때 사용
 *    - 실수: 단일 소수 판별 문제에 체를 사용하면 오히려 비효율
 *
 * 2. 유클리드 호제법 (최대공약수)
 *    - 착각 포인트: 둘 다 수학적 알고리즘이라 혼동 가능
 *    - 용도가 완전히 다름: 소수 찾기 vs 최대공약수 찾기
 *
 * 3. 소인수분해
 *    - 착각 포인트: 소수를 다룬다는 공통점
 *    - 체는 소수 "목록"을 만들고, 소인수분해는 수를 소수들의 "곱"으로 표현
 *    - 실수: 소인수분해 문제에 체만 사용하면 부족함
 *
 * 4. 동적계획법 (DP)
 *    - 착각 포인트: 이전 결과를 활용한다는 점에서 유사해 보임
 *    - 체는 단순히 배수를 제거하는 것, DP는 최적 부분 구조 활용
 */

public class 에라스토테네스의체 {

    public static void main(String[] args) {
        // 실제 코딩테스트에서는 이 부분을 문제에 맞게 수정
        int n = 100; // 100까지의 소수를 구하는 예시

        // 에라스토테네스의 체 실행
        boolean[] isPrime = sieve(n);

        // 결과 활용 예시 (실제 테스트에서는 문제 요구사항에 맞게 처리)
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        System.out.println("소수 개수: " + primes.size());
        System.out.println("소수 목록: " + primes);
    }

    /**
     * 에라스토테네스의 체 구현
     * @param n 소수를 찾을 범위 (2부터 n까지)
     * @return isPrime[i] = i가 소수인지 여부를 나타내는 boolean 배열
     */
    public static boolean[] sieve(int n) {
        // Step 1: 초기화 - 모든 수를 소수로 가정 (0, 1은 소수가 아님)
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // 모든 값을 true로 초기화
        isPrime[0] = isPrime[1] = false; // 0과 1은 소수가 아님

        // Step 2: 2부터 √n까지만 확인하면 됨 (핵심 최적화!)
        // 이유: n의 약수는 반드시 √n 이하에 하나, √n 이상에 하나씩 쌍을 이룸
        // √n 이하의 수들로 이미 모든 합성수의 작은 약수를 찾을 수 있음
        for (int i = 2; i * i <= n; i++) {

            // Step 3: i가 아직 소수로 남아있다면 (이전에 제거되지 않았다면)
            if (isPrime[i]) {

                // Step 4: i의 배수들을 모두 합성수로 표시
                // 중요: i*i부터 시작하는 이유!
                // i*2, i*3, ..., i*(i-1)은 이미 이전 단계에서 제거됨
                // 예: i=5일 때, 5*2=10, 5*3=15, 5*4=20은 2나 3의 배수로 이미 제거됨
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false; // j는 i의 배수이므로 합성수
                }
            }
        }

        return isPrime;
    }

    /**
     * 소수 개수만 필요한 경우의 최적화된 버전
     * 메모리를 아껴야 하는 문제에서 유용
     */
    public static int countPrimes(int n) {
        if (n <= 2) return 0;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        int count = 0;

        // 2는 유일한 짝수 소수이므로 별도 처리
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++; // 소수 발견시 카운트 증가

                // 오버플로우 방지: i * i > n 조건 체크
                if ((long)i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }

        return count;
    }

    /**
     * K번째 소수를 구하는 함수 (자주 출제되는 변형 문제)
     * @param k 몇 번째 소수인지
     * @return k번째 소수값
     */
    public static int kthPrime(int k) {
        // 대략적인 상한선 추정 (소수 정리 활용)
        // k번째 소수는 대략 k * ln(k) 정도의 크기
        int limit = Math.max(100, (int)(k * Math.log(k) * 1.5));

        boolean[] isPrime = sieve(limit);

        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                count++;
                if (count == k) {
                    return i; // k번째 소수 발견
                }
            }
        }

        // 범위가 부족한 경우 더 큰 범위로 재시도
        return kthPrime(k); // 실제로는 limit을 늘려서 재귀 호출
    }
}