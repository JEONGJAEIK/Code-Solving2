package org.problem.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 더하기만 있을 땐 괄호로 할 수 있는 것이 없다.
// 즉 -로 분리하고 더하기는 한 묶음으로 보면 된다.
// 최소 값을 구하기 위해서는 -(A + B)가 최우선 일테니
public class b1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        // 1. '-' 기준으로 나누기
        String[] splitByMinus = input.split("-");

        int result = 0;

        // 2. 첫 덩어리는 무조건 더함 (괄호 안에 들어가지 않음)
        result += sum(splitByMinus[0]);

        // 3. 나머지 덩어리는 전부 괄호로 묶는다고 생각하고 빼줌
        for (int i = 1; i < splitByMinus.length; i++) {
            result -= sum(splitByMinus[i]);
        }

        System.out.println(result);
    }

    // '+' 로 나뉜 숫자들을 모두 더하는 함수
    private static int sum(String expression) {
        String[] nums = expression.split("\\+");  // '+'는 정규식 특수문자라 \\+ 필요
        int total = 0;
        for (String num : nums) {
            total += Integer.parseInt(num);
        }
        return total;
    }
}