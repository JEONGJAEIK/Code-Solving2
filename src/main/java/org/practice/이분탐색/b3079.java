package org.practice.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        long right = (long) arr[arr.length - 1] * m;// 가능한 최대 시간 즉 가장느린심사관혼자 전부 다 볼경우
        long left = 1;
        long answer = right; // 가능한 최소시간을 저장할 변수

        while (left <= right) {
            long mid = (left + right) / 2; // 이 시간안에 심사가 가능한가? ㅇㅅㅇ

            long people = 0;
            for (int time : arr) {
                people += mid / time;
                if (people >= m) break; // 누적 인원이 m을 넘으면 탈출
            }

            if (people >= m) { // mid 시간동안 볼수있는 사람은 people수 보다 많아서 더 줄여야함
                answer = mid; // 현재시간을 정답 후보 등록
                right = mid - 1; // 시간 줄이기
            } else {
                left = mid + 1; // 시간 늘려야함
            }
        }
        System.out.println(answer);
    }
}
