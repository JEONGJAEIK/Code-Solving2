package org.problem;

import java.io.*;
import java.util.*;

public class b1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Boolean> answer = new ArrayList<>();

        Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> nSet = new HashSet<>();

        while (st.hasMoreTokens()) {
            nSet.add(Integer.valueOf(st.nextToken()));
        }

        Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int result = Integer.parseInt(st.nextToken());
            answer.add(nSet.contains(result));
        }

        for (boolean result : answer) {
            wr.write(result ? "1\n" : "0\n");
        }
        wr.flush();
        wr.close();
    }
}
