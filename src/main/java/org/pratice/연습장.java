package org.pratice;

import java.util.*;

public class 연습장 {


    static class Node {
        public int next;
        public int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }


        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 5; i++) {
            Integer next = iterator.next();
            System.out.print(next);
        }
        System.out.println();

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(i);
        }

        Iterator<Integer> iterator1 = set.iterator();
        for (int i = 0; i < 5; i++) {
            Integer next = iterator1.next();
            System.out.print(next);
        }
        System.out.println();

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i + " ", i);
        }

        Iterator<String> iterator2 = map.keySet().iterator();
        for (int i = 0; i < 5; i++) {
            String next = iterator2.next();
            System.out.print(next);
        }
        System.out.println();

        // [출발인덱스] = 노드
        List<Node>[] array = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            array[i] = new ArrayList<>();
        }

        array[0].add(new Node(1, 2));
        System.out.println(array[0].get(0).cost + ", " +  array[0].get(0).next);
    }


}
