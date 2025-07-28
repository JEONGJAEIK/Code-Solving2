package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][3];
        int[][] dpMax = new int[n][3];
        int[][] dpMin = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        dpMax[0][0] = arr[0][0];
        dpMax[0][1] = arr[0][1];
        dpMax[0][2] = arr[0][2];

        dpMin[0][0] = arr[0][0];
        dpMin[0][1] = arr[0][1];
        dpMin[0][2] = arr[0][2];

        if (n == 1) {
            System.out.println(Math.max(Math.max(dpMax[0][0], dpMax[0][1]), dpMax[0][2]) + " " + Math.min(Math.min(dpMin[0][0], dpMin[0][1]), dpMin[0][2]));
            return;
        }

        dpMax[1][0] = Math.max(dpMax[0][0], dpMax[0][1]) + arr[1][0];
        dpMax[1][1] = Math.max(Math.max(dpMax[0][0], dpMax[0][1]), dpMax[0][2]) + arr[1][1];
        dpMax[1][2] = Math.max(dpMax[0][1], dpMax[0][2]) + arr[1][2];

        dpMin[1][0] = Math.min(dpMin[0][0], dpMin[0][1]) + arr[1][0];
        dpMin[1][1] = Math.min(Math.min(dpMin[0][0], dpMin[0][1]), dpMin[0][2]) + arr[1][1];
        dpMin[1][2] = Math.min(dpMin[0][1], dpMin[0][2]) + arr[1][2];

        for (int i = 2; i < n; i++) {
            dpMax[i][0] = Math.max(Math.max(dpMax[i - 1][0], dpMax[i - 1][1]), dpMax[i - 2][2] + arr[i - 1][1]) + arr[i][0];
            dpMax[i][1] = Math.max(Math.max(dpMax[i - 1][0], dpMax[i - 1][1]), dpMax[i - 1][2]) + arr[i][1];
            dpMax[i][2] = Math.max(Math.max(dpMax[i - 1][1], dpMax[i - 1][2]), dpMax[i - 2][0] + arr[i - 1][1]) + arr[i][2];

            dpMin[i][0] = Math.min(Math.min(dpMin[i - 1][0], dpMin[i - 1][1]), dpMin[i - 2][2] + arr[i - 1][1]) + arr[i][0];
            dpMin[i][1] = Math.min(Math.min(dpMin[i - 1][0], dpMin[i - 1][1]), dpMin[i - 1][2]) + arr[i][1];
            dpMin[i][2] = Math.min(Math.min(dpMin[i - 1][1], dpMin[i - 1][2]), dpMin[i - 2][0] + arr[i - 1][1]) + arr[i][2];
        }

        System.out.println(Math.max(Math.max(dpMax[dpMax.length - 1][0], dpMax[dpMax.length - 1][1]), dpMax[dpMax.length - 1][2])
                + " " + Math.min(Math.min(dpMin[dpMin.length - 1][0], dpMin[dpMin.length - 1][1]), dpMin[dpMin.length - 1][2]));
    }
}
