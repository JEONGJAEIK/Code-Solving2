package org.practice;

import java.io.*;
import java.util.StringTokenizer;

public class b11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int addCount = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][n];
        int[][] sumArr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                sumArr[0][i] = arr[0][i];
            } else {
                sumArr[0][i] = sumArr[0][i - 1] + arr[0][i];
                sumArr[i][0] = sumArr[i - 1][0] + arr[i][0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                sumArr[i][j] = arr[i][j] + sumArr[i][j - 1] + sumArr[i - 1][j] - sumArr[i - 1][j - 1];
            }
        }

        for (int i = 0; i < addCount; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            if (x1 != 0 && y1 != 0) {
                int result = sumArr[x2][y2] - sumArr[x1 - 1][y2] - sumArr[x2][y1 -1] + sumArr[x1 - 1][y1 - 1];
                bw.write(result + "\n");
            } else if (x1 == x2 && y1 == y2) {
                bw.write(arr[x1][y1] + "\n");
            }
            else if (x1 == 0 && y1 != 0) {
                int result = sumArr[x2][y2]- sumArr[x2][y1 -1];
                bw.write(result + "\n");
            } else if (x1 != 0 && y1 == 0) {
                int result = sumArr[x2][y2] - sumArr[x1 - 1][y2];
                bw.write(result + "\n");
            } else {
                int result = sumArr[x2][y2];
                bw.write(result + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
