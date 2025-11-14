package org.pratice.재귀;

public class Code1 {
    public static void main(String[] args) {
        int n = 4;
        func(n);
    }

    public static void func(int n) {
        if (n <= 0) { // Base Case : 적어도 하나의 재귀에 빠지지 않는 경우가 존재해야한다.
            return;
        } else {
            System.out.println("hi");
            func(n-1); // Recursive Case : 재귀를 반복하다보면 결국 Base Case로 수렴해야한다.
        }
    }
}
