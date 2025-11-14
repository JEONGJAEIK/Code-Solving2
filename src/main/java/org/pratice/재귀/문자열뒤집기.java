package org.pratice.재귀;

public class 문자열뒤집기 {
    public static void main(String[] args) {
        String a = "abcd";
        reverse(a);
    }

    public static void reverse(String a) {
        if (a.isEmpty()) { // 베이스 케이스
            return;
        }

        reverse(a.substring(1)); // 리컬시브 케이스
        System.out.println(a.charAt(0));
    }
}
