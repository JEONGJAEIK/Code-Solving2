package org.pratice.재귀;

public class 이진수변환 {
    public static void main(String[] args) {
        int a = 10;
        binary(a);
    }

    public static void binary(int a) {
        if (a < 2) {
            System.out.print(a); // 베이스 케이스
            return;
        }

        binary(a/2); // 마지막 비트를 제외한 나머지는 그 십진수를 2로 나눈 몫과 같다
        System.out.print(a%2); // 어떤 십진수에서 마지막 비트의 이진수값은 그 십진수를 2로 나눈것의 나머지
    }
    // binary(10)
    // binary(5)
    // binary(2)
    // binary(1)
    // binary(1) 에서 1 리턴
    // binary(2) 에서 0 리턴
    // binary(5) 에서 1 리턴
    // binary(10) 에서 0 리턴
}
