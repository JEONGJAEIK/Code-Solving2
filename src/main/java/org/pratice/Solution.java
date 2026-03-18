package org.pratice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Solution {
    public void solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] challengers = new int[N + 2];

        for (int stage : stages) {
            challengers[stage] += 1;
        }

        Map<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        for(int i = 1; i < challengers.length; i++) {
            if (challengers[i] == 0) {
                fails.put(i, 0.0);
            } else {
                fails.put(i, challengers[i] / total);
                total -= challengers[i];
            }
        }

        int[] array = fails.entrySet().stream().sorted((o1, o2) ->
                o1.getValue().equals(o2.getValue())
                        ? Integer.compare(o1.getKey(), o2.getKey())
                        : Double.compare(o2.getValue(), o1.getValue())).mapToInt(Map.Entry::getKey).toArray();


    }
}