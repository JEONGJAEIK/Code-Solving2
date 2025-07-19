package org.practice;

import java.io.*;
import java.util.StringTokenizer;

public class b7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numberOfPeople = Integer.parseInt(br.readLine());
        int[][] people = new int[numberOfPeople][3];

        for (int i = 0; i < numberOfPeople; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());
            people[i][1] = Integer.parseInt(st.nextToken());
            people[i][2] = 1;
        }

        for (int i = 0; i < people.length; i++) {
            for (int j = 0; j < i; j++) {
                    if (people[i][0] > people[j][0] && people[i][1] > people[j][1]) {
                        people[j][2] += 1;
                    } else if (people[i][0] < people[j][0] && people[i][1] < people[j][1]) {
                        people[i][2] += 1;
                    } else {
                        continue;
                    }
                }
        }
        for (int i = 0; i < people.length; i++) {
            bw.write(people[i][2] + " ");
        }
        bw.flush();
        bw.close();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
