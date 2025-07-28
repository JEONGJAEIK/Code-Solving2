package org.problem.우선순위큐;

import java.io.*;
import java.util.*;

// silver 3
public class b1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        while (tc > 0) {
            int count = 0;
            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int paperCount = Integer.parseInt(st.nextToken());
            int targetIndex = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < paperCount; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.offer(new int[]{i, priority});
                pq.offer(priority);
            }

            while (!queue.isEmpty()) {
                int[] now = queue.peek();
                if (now[1] < pq.peek()) {
                    queue.poll();
                    queue.offer(now);
                } else {
                    queue.poll();
                    pq.poll();
                    count++;
                    if (now[0] == targetIndex) {
                        break;
                    }
                }
            }
            System.out.println(count);
            tc --;
        }
    }
}
