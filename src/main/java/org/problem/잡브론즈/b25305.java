package org.problem.잡브론즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class b25305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int total = Integer.parseInt(st.nextToken());
        int winner = Integer.parseInt(st.nextToken());
        int[] scores = new int[total];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < total; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        Integer[] WrapperScores = Arrays.stream(scores).boxed().toArray(Integer[]::new);
        Arrays.sort(WrapperScores, Comparator.reverseOrder());
        System.out.println(WrapperScores[winner - 1]);
    }
}
