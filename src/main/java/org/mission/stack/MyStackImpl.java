package org.mission.stack;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class MyStackImpl<E> implements MyStack<E> {

    private final Queue<E> main = new LinkedList<>();
    private final Queue<E> sub = new LinkedList<>();

    @Override
    public void push(E e) {
        main.offer(e);
    }

    @Override
    public E pop() {
        E result;

        while (main.size() > 1) {
            E poll = main.poll();
            sub.offer(poll);
        }

        result = main.poll();

        while (!sub.isEmpty()) {
            E poll = sub.poll();
            main.offer(poll);
        }

        return result;
    }
}
