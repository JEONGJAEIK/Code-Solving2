package org.problem.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());

        System.out.println(pow(a, b, c));
    }

    static long pow(long a, long b, long c) {
        if (b == 0) return 1;
        long half = pow(a, b / 2, c);
        long result = (half * half) % c;
        if (b % 2 == 1) {
            result = (result * a) % c;
        }
        return result;
    }
}
