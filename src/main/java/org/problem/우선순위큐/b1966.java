package org.problem.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실버 3 프린터 큐
public class b1966 {

    static class Numbers implements Comparable<Numbers> {

        int a;

        public Numbers(int a) {
            this.a = a;
        }

        @Override
        public int compareTo(Numbers numbers) {
            return Integer.compare(numbers.a, this.a);
        }
    }

    static class Numbers2 {

        int a;
        boolean target;

        public Numbers2(int a, boolean target) {
            this.a = a;
            this.target = target;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int paperTotal = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            List<Numbers> list = new ArrayList<>();
            List<Numbers2> list2 = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < paperTotal; j++) {
                int number = Integer.parseInt(st.nextToken());

                if (j == target) {
                    list2.add(new Numbers2(number, true));
                } else {
                    list2.add(new Numbers2(number, false));
                }

                list.add(new Numbers(number));
            }

            Deque<Numbers2> queue = new ArrayDeque<>(list2);
            PriorityQueue<Numbers> pq = new PriorityQueue<>(list);
            int count = 0;

            while (!queue.isEmpty()) {
                Numbers2 number = queue.poll();

                if (number.a != pq.peek().a) {
                    queue.addLast(number);
                } else {
                    pq.poll();
                    count++;
                    if (number.target) {
                        break;
                    }
                }
            }

            System.out.println(count);
        }
    }
}