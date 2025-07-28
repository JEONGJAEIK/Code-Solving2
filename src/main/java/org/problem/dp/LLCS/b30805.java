package org.problem.dp.LLCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 골드 4 사전 순 최대 공통 부분 수열
public class b30805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] b = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer>[][] dp = new ArrayList[n + 1][m + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int aVal = a[i]; // 변수명 변경: a1 대신 aVal
                int bVal = b[j]; // **수정: b[i] -> b[j]**

                if (aVal == bVal) {
                    dp[i][j] = new ArrayList<>(dp[i - 1][j - 1]);
                    dp[i][j].add(aVal); // a[i] 대신 aVal
                } else {
                    List<Integer> up = dp[i - 1][j];
                    List<Integer> left = dp[i][j - 1];

                    if (up.size() > left.size()) {
                        dp[i][j] = new ArrayList<>(up);
                    } else if (left.size() > up.size()) {
                        dp[i][j] = new ArrayList<>(left);
                    } else { // 길이가 같은 경우
                        // isLexicographicallyGreater 함수를 사용하여 사전적으로 더 큰 리스트 선택
                        if (isLexicographicallyGreater(up, left)) {
                            dp[i][j] = new ArrayList<>(up);
                        } else {
                            dp[i][j] = new ArrayList<>(left);
                        }
                    }
                }
            }
        }

        List<Integer> result = dp[n][m];
        System.out.println(result.size());
        if (!result.isEmpty()) {
            // 출력 형식에 맞게 공백으로 구분하여 출력
            StringBuilder sb = new StringBuilder();
            for (int val : result) {
                sb.append(val).append(" ");
            }
            // 마지막 공백 제거
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            System.out.println(sb.toString());
        }
    }

    // 두 리스트가 길이가 같을 때, a가 b보다 사전적으로 큰지 (더 뒤에 오는지) 판단
    static boolean isLexicographicallyGreater(List<Integer> a, List<Integer> b) {
        // 이 함수는 길이가 같은 경우에만 호출된다고 가정
        // 길이가 다른 경우를 대비한 방어 로직을 추가할 수도 있지만,
        // 현재 호출부에서는 이미 길이가 같은 경우에만 오도록 되어있음.
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > b.get(i)) {
                return true; // a의 현재 원소가 더 크므로 a가 사전적으로 더 큼
            } else if (a.get(i) < b.get(i)) {
                return false; // b의 현재 원소가 더 크므로 b가 사전적으로 더 큼
            }
        }
        return false; // 모든 원소가 동일하면 두 리스트는 같으므로 a가 b보다 크다고 할 수 없음
    }
}
