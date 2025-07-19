package org.practice;

import java.io.*;

public class b2879 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 5kg 봉지를 최대한 많이 사용하는 것이 유리 (그리디 접근)
        int result = -1;

        // 5kg 봉지를 i개 사용했을 때
        for (int i = N / 5; i >= 0; i--) {
            int remaining = N - (i * 5);

            // 남은 무게가 3kg 봉지로 정확히 나누어떨어지는지 확인
            if (remaining % 3 == 0) {
                int j = remaining / 3;
                result = i + j;
                break;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}