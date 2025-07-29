package org.problem.누적합;

import java.io.*;
import java.util.StringTokenizer;

// 골드 3 나머지 합
public class b10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());
        int divisor = Integer.parseInt(st.nextToken());

        long[] prefixSumModCount = new long[divisor];
        long sum = 0;
        prefixSumModCount[0] = 1; // 누적합 자체가 나눠떨어지는 경우 포함

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            int mod = (int)((sum % divisor + divisor) % divisor);
            prefixSumModCount[mod]++;
        }

        long result = 0;
        for (long c : prefixSumModCount) {
            result += c * (c - 1) / 2;
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}

