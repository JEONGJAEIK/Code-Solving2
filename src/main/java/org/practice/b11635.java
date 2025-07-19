package org.practice;

import java.io.*;

public class b11635 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = Integer.parseInt(br.readLine());
        int i = 2;

        while (a > 1) {
            if (a % i == 0) {
                a = a / i;
                bw.write(i + "\n");
            } else {
                i++;
            }
        }
        bw.flush();
        bw.close();
    }
}
