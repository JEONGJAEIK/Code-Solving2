package org.pratice;

public class Parent {


    void print() {
        System.out.println("부모" + System.identityHashCode(this));
    }
}
