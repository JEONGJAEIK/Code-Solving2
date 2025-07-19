package org.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class b30802 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        int totalCount = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] shirt = new int[6];
        int[] bundle = new int[2];
        int shirtResult = 0;
        int penResult = 0;
        int penResult2 = 0;
        for (int i = 0; i < shirt.length; i++) {
            shirt[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bundle.length; i++) {
            bundle[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < shirt.length; i++) {
            if (shirt[i] <= bundle[0] && shirt[i] > 0) {
                shirtResult += 1;
            } else {
                if (shirt[i] % bundle[0] == 0) {
                    shirtResult += (shirt[i] / bundle[0]);
                } else {
                    shirtResult += (shirt[i] / bundle[0]) + 1;
                }
            }
        }
        penResult = totalCount / bundle[1];
        penResult2 = totalCount % bundle[1];

        bw.write(shirtResult + "\n");
        bw.write(penResult + " " + penResult2);
        bw.flush();
        bw.close();
    }
}
