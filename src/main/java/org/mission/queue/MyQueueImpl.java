package org.mission.queue;

import java.util.Stack;

public class MyQueueImpl<E> implements MyQueue<E> {

    private final Stack<E> input = new Stack<>();
    private final Stack<E> output = new Stack<>();

    @Override
    public void offer(E e) {
        input.push(e);
    }

    @Override
    public E poll() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                E e = input.pop();
                output.push(e);
            }
        }
        return output.pop();
    }
}
