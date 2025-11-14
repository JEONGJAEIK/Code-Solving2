package org.pratice.재귀;

public class Code2 {
    public static void main(String[] args) {
        int result = func(4);
        System.out.println(result);
    }

    private static int func(int n) {
        if (n == 0) { // n이 0일 경우 0이다
            return 0;
        } else {
            return n + func(n-1); // n이 0보다 크다면 n과 0부터 n-1까지의 합을 더한것 고로 이 함수는 0~n까지의 합을 구하는것임을 증명한다
        }
    }

    // 수학적 귀납법

}
