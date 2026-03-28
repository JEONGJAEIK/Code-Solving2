package org.problem.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b21608 {
    static int[][] map;
    static List<Set<Integer>> friendList = new ArrayList<>();
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int length;
    static int[][] resultMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        length = Integer.parseInt(br.readLine());
        map = new int[length][length];
        resultMap = new int[length][length];

        for (int i = 0; i < (length * length) + 1; i++) {
            friendList.add(new HashSet<>());
        }

        for (int i = 0; i < length * length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                friendList.get(student).add(Integer.parseInt(st.nextToken()));
            }
            likeFriendCheck(student);
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny >= 0 && nx >= 0 && ny < length && nx < length) {
                        if (friendList.get(map[i][j]).contains(map[ny][nx])) {
                            count++;
                        }
                    }
                }
                saveResultMap(count, i, j);
            }
        }

        int result = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result += resultMap[i][j];
            }
        }

        System.out.println(result);
    }

    private static void saveResultMap(int count, int i, int j) {
        if (count == 0) {
            resultMap[i][j] = 0;
        } else if (count == 1) {
            resultMap[i][j] = 1;
        } else if (count == 2) {
            resultMap[i][j] = 10;
        } else if (count == 3) {
            resultMap[i][j] = 100;
        } else {
            resultMap[i][j] = 1000;
        }
    }

    // 1. 좋아하는애가 주변에 많은지
    // 2. 1번이 여러 후보가 있으면 비어있는칸이 주변에 많은지
    // 3. 2번도 여러 후보가 있으면 행이 가장 작은 칸
    // 4. 3번도 여러 후보가 있으면 열이 가장 작은 칸
    // 좋아하는애가 주변에 많은지
    static void likeFriendCheck(int student) {
        int[][] likeCount = new int[length][length];
        int[][] emptyCount = new int[length][length];
        Set<Integer> set = friendList.get(student);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny >= 0 && nx >= 0 && ny < length && nx < length) {
                        if (set.contains(map[ny][nx])) {
                            likeCount[i][j]++;
                        } else if (map[ny][nx] == 0) {
                            emptyCount[i][j]++;
                        }
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (max < likeCount[i][j]) {
                    max = likeCount[i][j];
                }
            }
        }

        List<int[]> tempList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (likeCount[i][j] == max && map[i][j] == 0) {
                    tempList.add(new int[]{i, j});
                }
            }
        }

        if (tempList.size() == 1) {
            int[] space = tempList.get(0);
            int y = space[0];
            int x = space[1];
            map[y][x] = student;
        } else {
            chackEmpty(tempList, emptyCount, student);
        }
    }

    // 좋아하는애가 여러 후보가 있으면 비어있는칸이 주변에 많은지
    private static void chackEmpty(List<int[]> tempList, int[][] emptyCount, int student) {
        int maxCount = Integer.MIN_VALUE;
        for (int[] space : tempList) {
            int y = space[0];
            int x = space[1];
            if (maxCount < emptyCount[y][x]) {
                maxCount = emptyCount[y][x];
            }
        }

        List<int[]> countList = new ArrayList<>();
        for (int[] space : tempList) {
            int y = space[0];
            int x = space[1];
            if (emptyCount[y][x] == maxCount) {
                countList.add(new int[]{y, x});
            }
        }

        if (countList.size() == 1) {
            int[] space = countList.get(0);
            int y = space[0];
            int x = space[1];
            map[y][x] = student;
        } else {
            matchY(countList, student);
        }
    }

    // 비어있는 칸도 같으면 행이 작은거
    private static void matchY(List<int[]> countList, int student) {
        int minY = Integer.MAX_VALUE;
        for (int[] space : countList) {
            int y = space[0];

            if (minY > y) {
                minY = y;
            }
        }

        int countY = 0;
        List<int[]> minYList = new ArrayList<>();
        for (int[] space : countList) {
            int y = space[0];
            int x = space[1];

            if (minY == y) {
                countY++;
                minYList.add(new int[]{y, x});
            }
        }

        if (countY == 1) {
            int[] space = minYList.get(0);
            int y = space[0];
            int x = space[1];
            map[y][x] = student;
        } else {
            matchX(minYList, student);
        }
    }

    private static void matchX(List<int[]> minYList, int student) {
        int minX = Integer.MAX_VALUE;
        for (int[] space : minYList) {
            int x = space[1];

            if (minX > x) {
                minX = x;
            }
        }

        for (int[] space : minYList) {
            int y = space[0];
            int x = space[1];

            if (minX == x) {
                map[y][x] = student;
            }
        }
    }
}
