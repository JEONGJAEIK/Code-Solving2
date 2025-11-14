package org.pratice.재귀;

public class 문자열길이계산 {
    public static void main(String[] args) {
        System.out.println(length("abcd"));
    }

    private static int length(String n) {
        if (n.isEmpty()) {
            return 0;
        } else {
            System.out.println(n.substring(1));
            return 1 + length(n.substring(1));
        }
    }
}
