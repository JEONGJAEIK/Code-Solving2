package org.practice.트리맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드4 이중 우선순위 큐
// 명령어 I는 큐에 정수 n을 삽입함
// 명령어 D는 D 1, D -1로 구성되며 전자는 최댓값 삭제 후자는 최솟값 삭제
// 삭제할 때 동일한 숫자가 2개면 하나만 삭제됨 또한 큐가 비어있으면 무시함
// 우선순위 큐로 하면 시간초과 나서 트리맵으로 함
public class b7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스

        for (int i = 0; i < tc; i++) {
            int k = Integer.parseInt(br.readLine()); // 큐에 적용할 연산의 개수
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String commend = st.nextToken(); // 연산 명령 D 또는 I
                int n = Integer.parseInt(st.nextToken()); // 연산에 삽입할 정수

                if (commend.equals("I")) {
                    map.put(n, map.getOrDefault(n, 0) + 1);
                } else {
                    if (map.isEmpty()) continue;

                    int key = (n == 1) ? map.lastKey() : map.firstKey();
                    int count = map.get(key);

                    if (count == 1) {
                        map.remove(key);
                    } else {
                        map.put(key, count - 1);
                    }
                }
            }

            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}
