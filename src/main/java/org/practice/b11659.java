package org.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class b11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int arrCount = Integer.parseInt(st.nextToken());
        int addCount = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[arrCount];
        int[] sumArr = new int[arrCount];
        for (int i = 0; i < arrCount; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                sumArr[i] = arr[i];
            } else {
                sumArr[i] = sumArr[i - 1] + arr[i];
            }
        }


        for (int i = 0; i < addCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start == end) {
                bw.write(arr[start - 1] + "\n");
            } else if (start == 1) {
                bw.write(sumArr[end - 1] + "\n");
            } else {
                bw.write(sumArr[end - 1] - sumArr[start - 2] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
