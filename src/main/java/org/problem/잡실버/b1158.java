package org.problem.잡실버;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class b1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        LinkedList<Integer> list = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            list.add(i);
        }

        int index = 0;
        while (!list.isEmpty()) {
            index = (index + n - 1) % list.size();
            result.add(list.remove(index));
        }
        wr.write("<");
        for (int i = 0; i < result.size(); i++) {
            wr.write(String.valueOf(result.get(i)));
            if (i != result.size() - 1) {
                wr.write(", ");
            }
        }
        wr.write(">");
        wr.flush();
        wr.close();
    }
}
