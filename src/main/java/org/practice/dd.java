package org.practice;

import java.io.*;

public class dd {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            int testCases = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < testCases; i++) {
                int max = 0;
                String max_school = "";
                int schools = Integer.parseInt(br.readLine().trim());
                for (int j = 0; j < schools; j++) {
                    String[] input = br.readLine().trim().split(" ");
                    if (input.length < 2) {
                        throw new IllegalArgumentException("Invalid input format.");
                    }
                    int score = Integer.parseInt(input[1].trim());
                    if (score > max) {
                        max = score;
                        max_school = input[0];
                    }
                }
                bw.write(max_school + "\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            bw.flush();
            bw.close();
        }
    }
}
