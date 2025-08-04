package org.problem.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 5 A와 B
public class b12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder before = new StringBuilder(br.readLine());
        StringBuilder after = new StringBuilder(br.readLine());


        // before를 after로 만들 수 있는지 구분은 어떻게 할까?
        // after의 마지막 문자가 B인 경우 B를 제거하고 뒤집은 것이 이전 상태와 같아야한다.
        // after의 마지막 문자가 A인 경우 A를 제거한것이 이전 상태와 같아야한다.
        // after를 before로 만들어가면서 before와 동일한지 체크하면 된다.

        while (after.length() > before.length()) {
            if (after.charAt(after.length() - 1) == 'A') {
                deleteA(after);
            } else {
                deleteB(after);
            }
        }

        if (after.compareTo(before) == 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static void deleteA(StringBuilder after) {
        after.deleteCharAt(after.length() - 1);
    }

    static void deleteB(StringBuilder after) {
        after.deleteCharAt(after.length() - 1).reverse();

    }
}
