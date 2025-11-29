package org.problem.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2580 {
    static int[][] sudoku;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs 함수 호출 및 결과 확인
        dfs(0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 8) {
                    System.out.print(sudoku[i][j]);
                } else {
                    System.out.print(sudoku[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // dfs 함수의 반환 타입을 boolean으로 변경
    static boolean dfs(int y, int x) {
        if (x == 9) {
            x = 0;
            y++;
        }

        if (y == 9) {
            return true; // 정답을 찾았으므로 true 반환
        }

        if (sudoku[y][x] != 0) {
            // 이미 채워진 칸: 다음 칸으로 이동하여 탐색을 계속하고,
            // 하위 호출에서 true가 반환되면 그대로 true를 반환하여 종료
            return dfs(y, x + 1);
        }

        for (int i = 1; i <= 9; i++) {
            if (checkCol(x, i) && checkRow(y, i) && checkBox(y, x, i)) {
                sudoku[y][x] = i;

                // 하위 호출(dfs)이 true를 반환하면 (정답을 찾으면)
                if (dfs(y, x + 1)) {
                    // for 루프를 멈추고 (break 대신 return을 사용)
                    return true;
                }

                // 정답을 찾지 못했다면 백트래킹 (false가 반환된 경우)
                sudoku[y][x] = 0;
            }
        }

        // 이 칸에서 1~9까지 어떤 숫자도 스도쿠를 완성시키지 못하면
        return false;
    }

    // checkRow, checkCol, checkBox 함수는 동일합니다.
    static boolean checkRow(int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (value == sudoku[y][i]) {
                return false;
            }
        }
        return true;
    }

    static boolean checkCol(int x, int value) {
        for (int i = 0; i < 9; i++) {
            if (value == sudoku[i][x]) {
                return false;
            }
        }
        return true;
    }

    static boolean checkBox(int y, int x, int value) {
        int startY = (y / 3) * 3;
        int startX = (x / 3) * 3;

        for (int i = startY; i < startY + 3; i++) {
            for (int j = startX; j < startX + 3; j++) {
                if (value == sudoku[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}