package org.problem.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 4 백트래킹
public class b2239 {
    static int[][] sudoku;
    static boolean[][][] used = new boolean[3][9][10];
    static boolean end = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 3가지 제한
        // 1. sudoku[i][0 ~ 8]에서 같은 숫자가 있으면 안된다.
        // 2. sudoku[0 ~ 8][i]에서 같은 숫자가 있으면 안된다.
        // 3. sudoku[0 ~ 2][0 ~ 2], 3 ~ 5, 6 ~ 8도 동일하게 같은 숫자가 있으면 안된다.
        // 한마디로 sudoku[i][j]에서 같은 숫자는 i도 j도 겹쳐서는 안된다.
        // 어떠한 숫자가 [i][j]에 있으면 그 숫자는 [!i][!j]에만 넣을 수 있으며 9개의 지역중 자신에 지역에 동일 숫자가 있는지 체크해야함.

        // 백트래킹의 조건
        // 어떠한 숫자를 채울 때 그 숫자가 i나 j중 하나라도 겹치거나 자신의 지역내에 존재한다면 백트래킹한다.

        // 정답이 여러개 일때 사전순으로 제출하라는 것
        // 가장 위의 행이 가장 작은 숫자여야한다. 그렇다면 여러번 정답을 찾을 필요가 없다. 따라서 탐색은 행부터 시작한다.

        sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = input.charAt(j) - '0';
                if (sudoku[i][j] != 0) {
                    used[0][i][sudoku[i][j]] = true; // 행 i번째 줄에는 sudoku[i][j] 라는 숫자가 사용되었다.
                    used[1][j][sudoku[i][j]] = true; // 열 j번째 줄에는 sudoku[i][j] 라는 숫자가 사용되었다.
                    used[2][(i / 3) * 3 + j / 3][sudoku[i][j]] = true; // 3x3 박스의 (i / 3) * 3 + j / 3 칸에는 sudoku[i][j] 라는 숫자가 사용되었다.
                }
            }
        }

        dfs(0, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }

    static void dfs(int y, int x) {
        if (y == 8 && x == 8) {
            for (int i = 1; i <= 9; i++) {
                if(!used[0][8][i]) {
                    sudoku[8][8] = i;
                    break;
                }
            }
            end = true;
            return;
        }

        if (sudoku[y][x] != 0) {
            if (x + 1 == 9) {
                dfs(y + 1, 0);
            } else {
                dfs(y, x + 1);
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                if (!used[0][y][i] && !used[1][x][i] && !used[2][(y / 3) * 3 + x / 3][i]) {
                    used[0][y][i] = true;
                    used[1][x][i] = true;
                    used[2][(y / 3) * 3 + x / 3][i] = true;
                    sudoku[y][x] = i;
                    if (x + 1 == 9) {
                        dfs(y + 1, 0);
                    } else {
                        dfs(y, x + 1);
                    }
                    if (end) {
                        return;
                    }
                    sudoku[y][x] = 0;
                    used[0][y][i] = false;
                    used[1][x][i] = false;
                    used[2][(y / 3) * 3 + x / 3][i] = false;
                }
            }
        }
    }
}
