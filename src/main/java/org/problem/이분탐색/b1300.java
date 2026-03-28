package org.problem.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 1 K번째 수
public class b1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // n은 최대 10만
        long k = Integer.parseInt(br.readLine()); // k는 최소 10억 최대 n제곱 (100억)
        // 1차열 배열로 만들었을 때 B[k]의 값을 구하라
        // 인덱스 1부터 시작
        //   1 2 3
        // 1 1 2 3
        // 2 2 4 6
        // 3 3 6 9
        //
        // 1 2 3 4 5 6 7 8 9
        // 1 2 2 3 3 4 6 6 9
        // B[8] = 6, 6보다 작거나 같은 원소의 개수는 최소 8개
        // B[k] = ? ?보다 작거나 같은 원소의 개수는 최소 k개
        // ?를 조정해가면서 작거나 같은 원소의 개수가 k개 일때를 구하면 됨
        // A[i][j]에서 A[i]에서 k보다 작거나 같은 원소의 개수는 k / i 개
        //   1 2 3 - 3을 찾을 때
        // 1 1 2 3 : 3 / 1 = 3개
        // 2 2 4 6 : 3 / 2 = 1개
        // 3 3 6 9 : 3 / 3 = 1개
        // 답 : 5 B[5] = 3;
        // x를 잡고 이분탐색으로 k까지 값을 좁히자 그때의 X가 정답
        // k = 1 2 3 4 5 6 7 8 9
        // x = 1 2 2 3 3 4 6 6 9
        // x는 k보다 반드시 작다
        // 1 1 2 3 : 9 / 1 = 9개
        // 2 2 4 6 : 9 / 2 = 4개
        // 3 3 6 9 : 9 / 3 = 3개
        // 답 : 16 B[16] = 9;
        // 오류가 난다 최대값을 n으로 잡아야 함
        // 1 1 2 3 : 9 / 1 = 9개 - 3
        // 2 2 4 6 : 9 / 2 = 4개 - 3
        // 3 3 6 9 : 9 / 3 = 3개 - 3
        // 답 : 9 B[9] = 9;
        long start = 1;
        long end = k;

        while (start < end) {
            long mid = (start + end) / 2;
            long temp = 0;
            for (int i = 1; i <= n; i++) {
                temp += Math.min(mid / i, n);
            }

            if (k <= temp) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }
}
