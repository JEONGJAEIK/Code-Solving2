package org.mission.string;

import java.util.Arrays;

public class 문자열뒤집기 {

    public static void main(String[] args) {
        String name = "123456";
        char[] charArray = name.toCharArray();
        char[] charResult = new char[name.length()];

        for (int i = charArray.length - 1; i >= 0; i--) {
            charResult[charArray.length - i - 1] = charArray[i];
        }

        String result = Arrays.toString(charResult);
        System.out.println(result);
    }
}
