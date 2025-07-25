package org.practice.bd우선탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드4 테트로미노
// 특정한 모양의 칸의 합을 출력하는데 모양이 정해져있다는 것은 방향을 정해준 것이라고 생각한다.
// for문으로 (0, 0) 부터 돌면서 해당 방향의 칸의 합을 더한다. 방향벡터를 설정하려하는데 회전하거나 대칭의 방향벡터도 하드코딩 해야하나..?
// dy와 dx를 (+, +), (+, -), (-, +), (-, -) 시키면 4방향 된다
public class b14500 {
    static int[][][] shapes = {
            // 기본 5개 블럭
            {{0,0},{0,1},{0,2},{0,3}},  // ㅡ
            {{0,0},{1,0},{2,0},{2,1}},  // ㄴ
            {{0,0},{1,0},{2,0},{1,1}},  // ㅗ
            {{0,0},{1,0},{1,1},{2,1}},  // ㄹ
            {{0,0},{0,1},{1,0},{1,1}}   // ㅁ
    };

    static int n, m;
    static int[][] arr;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int[][] shape : shapes) {
                    for (int r = 0; r < 4; r++) { // 회전 0도, 90도, 180도, 270도
                        for (int sy : new int[]{1, -1}) {
                            for (int sx : new int[]{1, -1}) {
                                int sum = 0;
                                boolean isValid = true;
                                for (int[] d : shape) {
                                    int dy = d[0], dx = d[1];

                                    // 회전
                                    for (int k = 0; k < r; k++) {
                                        int temp = dy;
                                        dy = dx;
                                        dx = -temp;
                                    }

                                    int ny = i + dy * sy;
                                    int nx = j + dx * sx;

                                    if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                                        isValid = false;
                                        break;
                                    }
                                    sum += arr[ny][nx];
                                }
                                if (isValid) max = Math.max(max, sum);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}