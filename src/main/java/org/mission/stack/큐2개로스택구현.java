package org.mission.stack;

public class 큐2개로스택구현 {

    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStackImpl<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }

}
