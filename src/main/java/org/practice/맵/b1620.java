package org.practice.맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버 4 포켓몬 마스터
// 정규표현식 문제이자 MAP 문제
// 리스트를 값으로 탐색하는건 O(N), MAP을 키로 탐색하는건 O(1)이다
// 참고로 MAP을 값으로 탐색하는건 O(N)이고 이 상황에서는 MAP을 2개만드는 것으로 속도를 증가시킬 수 있다.
// 숫자를 판별하는 정규표현식은 Character.isDigit(str.charAt(0))으로 대체가능하다 해당 문자의 첫번째가 숫자인지 판별하는 메소드다 숫자면 true 아니면 false 반환
public class b1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(st.nextToken()); // 도감에 있는 포켓몬 수
        int m = Integer.parseInt(st.nextToken()); // 문제 수

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            map.put(name, i);
            list.add(name);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String query = br.readLine();
            if (isNumeric(query)) {
                int num = Integer.parseInt(query);
                sb.append(list.get(num - 1)).append("\n");
            } else {
                sb.append(map.get(query) + 1).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+"); // 양의 정수만 허용
    }
}
