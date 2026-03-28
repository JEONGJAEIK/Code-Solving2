package org.problem.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 골드2 가장 긴 증가하는 부분 수열 2
public class b12015 {
    static int[] arr;
    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 10 10 20 20 30 50
        // 여기서 이분탐색을 어케함??
        // a[0] = 10은 10보다 같거나 작은게 1개 라는 뜻
        // a[1] = 10보다 같거나 작은게 2개 라는 뜻
        // 2를 중간값으로 잡는다
        // 20이다
        // 스타트가 엔드까지가도록 실행
        // 확정
        // 처음의 중간값과 끝ㅌ의 중간값을 잡는다

        result.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (result.get(result.size() - 1) < arr[i]) {
                result.add(arr[i]);
            } else {
                int i1 = binarySearch(result.size() - 1, arr[i]);
                result.set(i1, arr[i]);
            }
        }
        System.out.println(result.size());
    }

    static int binarySearch(int count, int target) {
        int start = 0;
        int end = count;

        while (start < end) {
            int mid = (start + end) / 2;

            if (result.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
