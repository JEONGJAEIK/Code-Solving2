package org.practice;

import java.io.*;
import java.util.StringTokenizer;

public class b12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int length = Integer.parseInt(st.nextToken());
        int passwordLength = Integer.parseInt(st.nextToken());
        String dna = br.readLine();

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int countA = 0;
        int countC = 0;
        int countG = 0;
        int countT = 0;
        int answer = 0;

        for (int i = 0; i < passwordLength; i++) {
            if (dna.charAt(i) == 'A') {
                countA++;
            } else if (dna.charAt(i) == 'C') {
                countC++;
            } else if (dna.charAt(i) == 'G') {
                countG++;
            } else if (dna.charAt(i) == 'T') {
                countT++;
            }
        }

        if (countA >= a && countC >= c && countG >= g && countT >= t) {
            answer++;
        }

        for (int i = passwordLength; i < length; i++) {
            if (dna.charAt(i) == 'A') {
                countA++;
            } else if (dna.charAt(i) == 'C') {
                countC++;
            } else if (dna.charAt(i) == 'G') {
                countG++;
            } else if (dna.charAt(i) == 'T') {
                countT++;
            }

            if (dna.charAt(i - passwordLength) == 'A') {
                countA--;
            } else if (dna.charAt(i - passwordLength) == 'C') {
                countC--;
            } else if (dna.charAt(i - passwordLength) == 'G') {
                countG--;
            } else if (dna.charAt(i - passwordLength) == 'T') {
                countT--;
            }

            if (countA >= a && countC >= c && countG >= g && countT >= t) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
