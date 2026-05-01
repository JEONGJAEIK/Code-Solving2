package org.mission.queue;

public interface MyQueue<E> {

    void offer(E e);

    E poll();
}
