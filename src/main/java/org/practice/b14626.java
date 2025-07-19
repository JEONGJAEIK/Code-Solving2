package org.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b14626 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int isbnAdd = 0;
        boolean check = false;
        String isbn = br.readLine();
        char[] isbnArr = isbn.toCharArray();
        int mod = 10;
        for (int i = 0; i < isbnArr.length; i++) {
            if ('*' == isbnArr[i]) {
                check = i % 2 == 0;
            } else {
                if (i % 2 == 0) {
                    isbnAdd += (isbnArr[i] - '0');
                } else {
                    isbnAdd += (isbnArr[i] - '0') * 3;
                }
            }
        }
        int result = 0;
        for (int x = 0; x <= 9; x++) {
            int sum = isbnAdd;
            if (check) sum += x;
            else sum += x * 3;

            if (sum % 10 == 0) {
                result = x;
                break;
            }
        }

        System.out.println(result);
    }
}
