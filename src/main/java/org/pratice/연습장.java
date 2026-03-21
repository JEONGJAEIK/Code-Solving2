package org.pratice;

import java.util.*;

public class 연습장 {
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
    }
}
