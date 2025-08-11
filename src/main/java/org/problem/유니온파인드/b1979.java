package org.problem.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드 4 여행 가자
public class b1979 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cityCount = Integer.parseInt(br.readLine());
        int travelCityCount = Integer.parseInt(br.readLine());

        parent = new int[cityCount + 1];
        for (int i = 1; i <= cityCount; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= cityCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= cityCount; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    union(i, j);
                }
            }
        }

        int[] targetCitys = new int[travelCityCount];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < travelCityCount; i++) {
            targetCitys[i] = Integer.parseInt(st.nextToken());
        }

        int root = find(targetCitys[0]);
        boolean isYes = true;
        for (int i = 1; i < travelCityCount; i++) {
            if (find(targetCitys[i]) != root) {
                isYes = false;
                break;
            }
        }

        System.out.println(isYes ? "YES" : "NO");
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }
}
