package org.practice;

import java.io.*;
import java.util.StringTokenizer;

public class b11441 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n + 1];
        int[] sumNums = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                sumNums[i] = sumNums[i - 1] + nums[i];
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                int result = sumNums[b] - sumNums[a - 1];
                sb.append(result).append("\n");

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
