package org.pratice.재귀;

public class 최대공약수_유클리드호제법 {
    public static void main(String[] args) {
        System.out.println(gcd(3, 2));
    }

    // 유클리드 호제법
    // m >= n인 두 양의 중수에 대해서 m이 n의 배수면 gcd(m,n) =n이고 그렇지않으면 gcd(m,n) = gcd(n,m%n)이다
    // m 10 n 6
    // m 6 n 4
    // m 4 n 2
    // m 2 n 0
    // 최대공약수 2
    public static double gcd(int m, int n) {
        if (n == 0)
            return m;
        return gcd(n, m % n);
    }
}
