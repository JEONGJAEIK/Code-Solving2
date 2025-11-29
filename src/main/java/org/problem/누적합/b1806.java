package org.problem.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드 4 부분합
public class b1806 {
    static int[] arr;
    static int result;
    static int temporaryResult = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int length = Integer.parseInt(st.nextToken()); // 수열 길이 최대 10만
        int target = Integer.parseInt(st.nextToken()); // 부분합 목표 최대 1억

        st = new StringTokenizer(br.readLine());

        arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        result = arr.length + 1;

        // 시간제한 1초니 1억번 연산안으로 끝내야함
        // 길이가 고정되어있지 않으니 슬라이딩 윈도우 말고 투 포인터를 사용하자.
        int start = 0;
        int end = 0;

        // 헷갈리지 말자 target의 이상이다.
        // 내부의 포문 때문에 시간초과가 난다. 결과를 임시저장 처리하자.
        while (end < arr.length) {
            // end를 이동시키며 부분합을 target 이상으로 만든다.
            temporaryResult += arr[end];
            end++;

            // 결과가 목표보다 같거나 클 경우
            while (temporaryResult >= target) {
                // 현재 길이보다 적을 경우 저장
                result = Math.min(result, end - start);
                // start를 오른쪽 이동
                temporaryResult -= arr[start];
                start++;
            }
        }
        if (result > arr.length) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}

