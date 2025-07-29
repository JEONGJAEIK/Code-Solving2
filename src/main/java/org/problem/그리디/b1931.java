package org.problem.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 골드 5 - 회의실 배정
// 종료시간으로 정렬한다 종료시간이 가장 빠른걸 고르고 count를 증가시킨다
// 탐색을 계속하면서 시작시간이 종료시간과 가장 차이가 적은 것을 고르고 count를 증가시킨다 이때 회의의 종료시간이 적은것이 1순위고 시작시간과 종료시간의 차이는 2순위이다.
public class b1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i][0] = start;
            meetings[i][1] = end;
        }

        // 회의들을 종료시간을 기준으로 정렬
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int count = 0;
        int lastEndTime = 0;
        for (int i = 0; i < meetings.length; i++) {
            if (meetings[i][0] >= lastEndTime) {
                count++;
                lastEndTime = meetings[i][1];
            }
        }
        System.out.println(count);
    }
}
