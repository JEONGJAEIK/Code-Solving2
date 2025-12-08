package org.problem.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 5 암호 만들기
public class b1759 {
    static char[] arr;
    static boolean[] visited;
    static int L;
    static int C;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken()); // 3 ~ 15 암호의 길이
        C = Integer.parseInt(st.nextToken()); // 주어지는 문자 L보다 크고 15보다 작음
        arr = new char[C];
        visited = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
        recursion(L, new ArrayList<>(), 0);
        System.out.println(sb);

        // 암호는 최소 한개의 모음(a, e, i, o, u)과 두개의 자음으로 구성
        // 증가하는 문자열
        // 순열
        // 문자열을 정렬시킨다. 순열을 시행하고 모음과 자음이 각 기준에 맞는지 검사
        // 일반적인 순열이 아니라 순서를 지킨다. 백트래킹
        // 문자열이 4개있을때 하나를 고정하고 3개를 정하는거와 같다
    }

    static void recursion(int n, List<Character> list, int index) {
        if (list.size() == L) {
            if (list.contains('i') || list.contains('o') || list.contains('a') || list.contains('u') || list.contains('e')) {
                if (checkList(list)) {
                    for (Character c : list) {
                        sb.append(c);
                    }
                    sb.append("\n");
                }
            }
            return;
        }

        for (int i = 0; i < C; i++) {
            if (index <= i) {
                if (!visited[i]) {
                    list.add(arr[i]);
                    visited[i] = true;
                    recursion(n - 1, list, i + 1);
                    list.remove(list.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }

    // 리스트에 영어자음이 2개 이상인지 확인한다.
    static boolean checkList(List<Character> list) {
        int count = 0;
        for (Character c : list) {
            if (c != 'o' && c != 'a' && c != 'e' && c != 'i' && c != 'u') {
                count++;
            }
        }
        return count >= 2;
    }
}
