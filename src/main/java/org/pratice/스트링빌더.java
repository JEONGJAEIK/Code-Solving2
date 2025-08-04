package org.pratice;

public class 스트링빌더 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("ABC");

        while (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }

        StringBuilder bb = new StringBuilder("ABC");
        bb.deleteCharAt(bb.length() - 1).reverse();
        System.out.println(bb);


    }
}
