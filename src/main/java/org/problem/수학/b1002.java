package org.problem.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버 3 터렛
public class b1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < count; i++) {
            int[] ints = new int[6];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                ints[j] = Integer.parseInt(st.nextToken());
            }

            int x1 = ints[0];
            int y1 = ints[1];
            int r1 = ints[2];
            int x2 = ints[3];
            int y2 = ints[4];
            int r2 = ints[5];

            // 두 원의 중심의 거리
            int distSq = (int) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

            if (x1 == x2 && y1 == y2 && r1 == r2) {
                System.out.println(-1);
            }
            // 2. 두 원의 중심이 같은데 반지름만 다른 경우 (만나지 않음)
            else if (x1 == x2 && y1 == y2) {
                System.out.println(0);
            }
            // 3. 외접하거나 내접하는 경우 (접점 1개)
            else if (distSq == Math.pow(r1 + r2, 2) || distSq == Math.pow(r1 - r2, 2)) {
                System.out.println(1);
            }
            // 4. 만나지 않는 경우 (외부에 있거나 한 원이 다른 원 내부에 있음)
            else if (distSq > Math.pow(r1 + r2, 2) || distSq < Math.pow(r1 - r2, 2)) {
                System.out.println(0);
            }
            // 5. 그 외 (두 점에서 만나는 경우)
            else {
                System.out.println(2);
            }
        }
    }
}
