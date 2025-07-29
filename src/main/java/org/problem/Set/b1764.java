package org.problem.Set;

import java.io.*;
import java.util.*;

// 실버 4 듣보잡
public class b1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Set을 이용해서 빠르게 비교
        Set<String> nGroup = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nGroup.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            if (nGroup.contains(name)) {
                result.add(name);
            }
        }

        // 사전순 정렬
        Collections.sort(result);

        // 출력
        wr.write(result.size() + "\n");
        for (String name : result) {
            wr.write(name + "\n");
        }

        wr.flush();
        wr.close();
        br.close();
    }
}
