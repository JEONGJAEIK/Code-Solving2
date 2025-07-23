package org.practice.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버 2 마인크래프트
public class b18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로
        int b = Integer.parseInt(st.nextToken()); // 인벤토리 블록 수

        int[][] map = new int[n][m];
        int min = 256;
        int max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int answerTime = Integer.MAX_VALUE;
        int answerHeight = 0;

        for (int h = min; h <= max; h++) {
            int remove = 0;
            int add = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int diff = map[i][j] - h;
                    if (diff > 0) {
                        remove += diff; // 제거할 블록 수
                    } else if (diff < 0) {
                        add -= diff; // 추가할 블록 수
                    }
                }
            }

            if (remove + b >= add) {
                int time = (remove * 2) + (add * 1);
                if (time < answerTime || (time == answerTime && h > answerHeight)) {
                    answerTime = time;
                    answerHeight = h;
                }
            }
        }

        System.out.println(answerTime + " " + answerHeight);
    }
}
