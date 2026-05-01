package org.mission.queue;

public class 스택2개로큐구현 {

    public static void main(String[] args) {
        MyQueue<Integer> mq = new MyQueueImpl<>();
        mq.offer(1);
        mq.offer(2);
        mq.offer(3);
        mq.offer(4);
        System.out.println(mq.poll());
        System.out.println(mq.poll());
        System.out.println(mq.poll());
        System.out.println(mq.poll());
    }
}
