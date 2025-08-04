package org.problem.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버 2 주식
public class b11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int day = Integer.parseInt(br.readLine());
            int[] days = new int[day];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < day; j++) {
                days[j] = Integer.parseInt(st.nextToken());
            }

            // 간단하게 배열중에 훗 날에 현재의 주가 보다 더 높은 주가가 있으면 사면 된다. 없으면 가만히 있는다
            // 그럼 자기 보다 더 높은 주가가 미래에 있는지 알고있어야한다.
            // 그때마다 배열을 정렬하면 시간초과일 것이다. 음 뒤에서 부터 파악했을때 자기자신보다 주가가 적은것이 나올때까지 차익을 더하면 된다.

            long result = 0;
            int max = days[days.length - 1];
            for (int j = days.length - 1; j > 0; j--) {
                if (max > days[j - 1]) {
                    result += max - days[j - 1];
                } else if (days[j] == days[j - 1]) {
                    continue;
                } else {
                    max = days[j - 1];
                }
            }

            System.out.println(result);
        }
    }
}
