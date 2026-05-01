package org.pratice;

public class Child extends Parent {

    @Override
    void print() {
        System.out.println("자식" + System.identityHashCode(this));
        super.print();
    }


    public static void main(String[] args) {
        Child c = new Child();
        Parent p = new Child();
        p.print();
    }
}
