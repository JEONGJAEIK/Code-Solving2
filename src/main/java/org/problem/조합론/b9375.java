package org.problem.조합론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버 3 패션왕 신해빈
public class b9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int count = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < count; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String category = st.nextToken();

                // 맵의 값에는 카테고리의 개수를 센다. 맵은 중복키를 받지못한다.
                // for문을 도는 동안 해당카테고리를 +1을 하는 원리
                map.put(category, map.getOrDefault(category, 0) + 1);
            }

            int answer = 1;
            for (int val : map.values()) {
                answer *= (val + 1); // 입지 않는 경우까지 포함
            }

            System.out.println(answer - 1); // 아무것도 안 입는 경우 제외
        }
    }
}
