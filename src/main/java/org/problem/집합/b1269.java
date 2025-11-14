package org.problem.집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 실버4 대칭차집합
public class b1269 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int aSize = Integer.parseInt(st.nextToken());
        int bSize = Integer.parseInt(st.nextToken());
        int totalSize = aSize + bSize;
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            setA.add(Integer.valueOf(st.nextToken()));
        }


        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            setB.add(Integer.valueOf(st.nextToken()));
        }

        for (Integer i : setA) {
            if (setB.contains(i)) {
                totalSize -= 2;
            }
        }
        System.out.println(totalSize);
    }
}
