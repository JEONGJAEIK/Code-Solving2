package org.pratice;


import java.util.PriorityQueue;

public class 우선순위큐 {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>() ;

        pq.add(9999);
        pq.add(4000);
        pq.add(10000);
        pq.add(12);
        pq.add(-2);

        for (int i = 0; i < 5; i++) {
            System.out.println(pq.poll());
        }
    }

}
