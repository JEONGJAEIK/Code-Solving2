package org.problem.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 골드4 공유기 설치
// 이분 탐색인데 거리를 이분 탐색하는 것이 아닌
// 특정 거리의 설치가능한 공유기에 개수의 따라 거리를 줄였다 늘였다하면서 공유기를 조절
// 특정 공유기를 설치가능한곳에서 최대거리를 찾아야하니 upper bound임
public class b2110 {
    static int[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int houseCount = Integer.parseInt(st.nextToken());
        int switchCount = Integer.parseInt(st.nextToken());
        map = new int[houseCount];
        for (int i = 0; i < houseCount; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(map);

        int start = 1;
        int end = map[houseCount - 1] - map[0] + 1;
        while (start < end) {

            int mid = (start + end) / 2;

            if (canInstall(mid) < switchCount) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start - 1);
    }

    static int canInstall(int distance) {

        int count = 1;
        int lastLocate = map[0];

        for (int i = 1; i < map.length; i++) {
            int locate = map[i];

            if (locate - lastLocate >= distance) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}
