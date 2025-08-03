package org.problem.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 5 치킨 배달
public class b15686 {
    static int[][] map;
    static List<int[]> chicken = new ArrayList<>();
    static List<int[]> house = new ArrayList<>();
    static List<List<int[]>> combinationList = new ArrayList<>();
    static int cityLength;
    static int chickenCount;
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cityLength = Integer.parseInt(st.nextToken());
        chickenCount = Integer.parseInt(st.nextToken());

        map = new int[cityLength][cityLength];
        List<Integer> countList = new ArrayList<>();
        int count = 0;


        for (int i = 0; i < cityLength; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cityLength; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) house.add(new int[]{i, j});
                if (map[i][j] == 2) chicken.add(new int[]{i, j});
            }
        }

        combination(0, new ArrayList<>());

        for (List<int[]> comb : combinationList) {
            int sum = 0;
            for (int[] h : house) {
                int min = Integer.MAX_VALUE;
                for (int[] c : comb) {
                    int dist = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    min = Math.min(min, dist);
                }
                sum += min;
            }
            minDistance = Math.min(minDistance, sum);
        }

        System.out.println(minDistance);
    }
    static void combination(int start, List<int[]> selected) {
        if (selected.size() == chickenCount) {
            combinationList.add(new ArrayList<>(selected));
            return;
        }
        for (int i = start; i < chicken.size(); i++) {
            selected.add(chicken.get(i));
            combination(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }
}
