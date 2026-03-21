package org.example;

import java.util.Arrays;

public class 비교 {
    public static void main(String[] args) {
        String[] a = new String[]{"1", "2", "3"};
        String[] b = new String[]{"4", "5", "6"};

        for (int i = 0; i < a.length; i++) {
            if (a[i].compareTo(b[i]) < 0) {
                System.out.println(a[i]);
                System.out.println(b[i]);
            }
        }

        StringBuilder sb = new StringBuilder();


        if (sb.charAt(0) == '0') {
        }

        System.out.println(a[0].compareTo(b[0]));
    }
}
