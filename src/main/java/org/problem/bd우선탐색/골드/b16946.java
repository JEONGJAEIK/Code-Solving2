package org.problem.bd우선탐색.골드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 2 벽 부수고 이동하기 4
public class b16946 {
    static int[][] result;
    static int[][] map;
    static int n;
    static int m;
    static int[][] group_id;    // 각 빈 공간이 속한 그룹의 ID 저장 (0이면 아직 그룹 미지정)
    static Map<Integer, Integer> group_size = new HashMap<>(); // 그룹 ID별 크기 저장 (key: 그룹ID, value: 크기)
    static int next_group_id = 2; // 그룹 ID는 2부터 시작 (0: 미방문/벽, 1: 벽)
    static int[] dy = new int[]{0, 0, 1, -1};
    static int[] dx = new int[]{-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행 개수
        m = Integer.parseInt(st.nextToken()); // 열 개수
        map = new int[n][m];
        result = new int[n][m];
        group_id = new int[n][m]; // 그룹 ID 배열 초기화

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 빈 공간이고, 아직 그룹 ID가 할당되지 않았다면 새로운 그룹 DFS 시작
                if (map[i][j] == 0 && group_id[i][j] == 0) {
                    int size = dfs(i, j, next_group_id);
                    group_size.put(next_group_id, size);
                    next_group_id++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) { // 현재 위치가 벽인 경우
                    // 인접한 빈 공간 그룹의 ID를 저장할 Set (중복 방지)
                    Set<Integer> neighborGroups = getIntegers(i, j);

                    // 벽을 부수고 이동할 수 있는 총 칸의 수 계산 (자기 자신 1 + 인접 그룹 크기 합)
                    int totalCount = 1;
                    for (int id : neighborGroups) {
                        // 맵에서 해당 그룹 ID의 크기를 가져와 더함
                        totalCount += group_size.getOrDefault(id, 0);
                    }

                    // 결과는 10으로 나눈 나머지
                    result[i][j] = totalCount % 10;
                } else {
                    // 빈 공간(0)인 경우 결과는 0
                    result[i][j] = 0;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(result[i][j]);
            }
            if (i < n - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static Set<Integer> getIntegers(int i, int j) {
        Set<Integer> neighborGroups = new HashSet<>();

        // 4방향 탐색
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];

            // 경계 체크
            if (ny >= 0 && nx >= 0 && ny < n && nx < m) {
                // 인접한 셀이 빈 공간(0)이고 그룹 ID가 할당된 경우
                if (map[ny][nx] == 0 && group_id[ny][nx] != 0) {
                    neighborGroups.add(group_id[ny][nx]);
                }
            }
        }
        return neighborGroups;
    }

    static int dfs(int y, int x, int id) {
        // 경계 조건 검사
        if (y < 0 || x < 0 || y >= n || x >= m) {
            return 0;
        }

        // 이미 벽(1)이거나, 이미 그룹 ID가 할당된 경우 (방문한 경우)
        if (map[y][x] == 1 || group_id[y][x] != 0) {
            return 0;
        }

        // 현재 위치에 그룹 ID 할당
        group_id[y][x] = id;
        int count = 1; // 자기 자신 카운트

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            count += dfs(y + dy[i], x + dx[i], id);
        }

        return count;
    }
}
