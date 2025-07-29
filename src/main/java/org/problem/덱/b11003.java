package org.problem.덱;

import java.io.*;
import java.util.*;

// 플래티넘 5 최솟값 찾기
public class b11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Map.Entry<Integer, Integer>> queue = new ArrayDeque<>();

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] result = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (queue.isEmpty()) {
                minSort(queue, num, result, i);
            } else if (queue.peekLast().getKey() - queue.peekFirst().getKey() < l - 1) {
                minSort(queue, num, result, i);
            } else {
                queue.pollFirst();
                minSort(queue, num, result, i);
            }
        }

        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                bw.write(String.valueOf(result[i]));
            } else {
                bw.write(result[i] + " ");
            }
        }
        bw.flush();
        bw.close();
    }
        public static void minSort (Deque < Map.Entry < Integer, Integer >> queue,int num, int[] result, int i){
            if (!queue.isEmpty()) {
                while (queue.peekLast().getValue() > num) {
                    queue.pollLast();
                    if (queue.isEmpty()) {
                        break;
                    }
                }
            }
            queue.offer(Map.entry(i, num));
            result[i] = queue.peekFirst().getValue();
        }
    }

