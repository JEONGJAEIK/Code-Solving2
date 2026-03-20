package org.problem.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b30892 {
    static int existedFish;
    static int eatingFist;
    static long size;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        existedFish = Integer.parseInt(st.nextToken());
        eatingFist = Integer.parseInt(st.nextToken());
        size = Integer.parseInt(st.nextToken());
        arr = new int[existedFish];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < existedFish; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 전체 상어 5 먹을 수 있는 상어 3 최초 크기 10
        // 3번으로 최대 크기가 되어라
        // 15 24 10 1 5
        // 1라운드 : 1, 5중에 골라라 더 큰 것 5 = 15
        // 15 24 10 1
        // 2라운드 : 1, 10중에 골라라 더 큰 것 10 = 25
        // 15 24 1
        // 3라운드 : 1, 15, 24중에 골라라 더 큰 것 24 = 49

        // 그리디 이긴한데 매번 포문은 좀...
        // 정렬을 하고 특정 인덱스를 찾아
        // 자신보다 바로 작은 것 중 제일 큰 것을 찾아서 더 해야 한다.

        Deque<Integer> stack = new ArrayDeque<>();
        int idx = 0;
        for (int i = 0; i < eatingFist; i++) {
            while (idx < existedFish && arr[idx] < size) {
                stack.push(arr[idx]);
                idx++;
            }

            if (stack.isEmpty()) {
                break;
            }

            size += stack.pop();
        }
        System.out.println(size);
    }
}
