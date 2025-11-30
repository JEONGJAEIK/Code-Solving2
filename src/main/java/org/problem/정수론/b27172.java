package org.problem.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 골드 4 수 나누기 게임
public class b27172 {
    static int[] arr;
    static int[] pos;
    static int[] scores;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 플레이어 최대 10만
        arr = new int[n];
        pos = new int[1000001];
        scores = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int card = Integer.parseInt(st.nextToken()); // 숫자 각 숫자는 최대 100만
            arr[i] = card;
            pos[card] = i + 1;
        }

        int[] sortedArr = Arrays.copyOf(arr, n);
        Arrays.sort(sortedArr);

        for (int i = 0; i < n; i++) {
            int a = sortedArr[i];
            int indexA = pos[a] - 1;

            for (int j = a * 2; j <= 1000000; j += a) {
                if (pos[j] != 0) {
                    int indexB = pos[j] - 1;
                    scores[indexA]++;
                    scores[indexB]--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int score : scores) {
            sb.append(score).append(" ");
        }
        System.out.println(sb);
    }
}
