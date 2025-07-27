package org.example;

import java.util.*;

/*
=== 백트래킹(Backtracking) 알고리즘 ===

【알고리즘 개념】
백트래킹은 "모든 가능한 경우의 수를 체계적으로 탐색"하는 알고리즘입니다.
하지만 단순한 완전탐색과 달리, "조건에 맞지 않는 경우는 즉시 포기(가지치기)"하여 효율성을 높입니다.

★ 핵심 아이디어: "시도 → 검증 → 실패하면 되돌아가기(백트래킹)"

【동작 원리】
1. 선택: 가능한 선택지 중 하나를 선택
2. 검증: 현재까지의 선택이 조건을 만족하는지 확인
3. 완성: 목표에 도달했으면 답을 저장/출력
4. 백트래킹: 조건을 만족하지 않거나 더 이상 진행할 수 없으면 이전 단계로 되돌아감
5. 반복: 모든 가능성을 탐색할 때까지 1-4를 반복

★ 왜 "백트래킹"이라고 부를까?
영어로 "back"(뒤로) + "track"(길/경로) = "뒤로 되돌아가는 길"
막다른 길에 도달하면 이전 갈림길로 돌아가서 다른 길을 시도하는 것과 같습니다!

【시간복잡도】
- 최악의 경우: O(N!) 또는 O(2^N) - 모든 경우를 다 확인해야 하는 경우
- 평균적으로: 가지치기 효과로 실제로는 훨씬 적은 경우만 탐색
- 예시: N-Queen 문제에서 8x8 체스판의 경우, 이론적으로는 64C8 = 약 43억 가지지만
         실제로는 가지치기로 인해 수만 가지 정도만 확인

【사전 지식】
- 재귀함수의 개념과 동작 원리
- 배열과 리스트의 기본 사용법
- 조건문과 반복문
- DFS(깊이우선탐색)의 기본 개념

【코딩테스트에서 백트래킹이 사용되는 문제 유형】
1. 순열/조합 문제: "N개 중에서 R개를 선택하는 모든 경우"
2. N-Queen 문제: "N×N 체스판에 N개의 퀸을 서로 공격하지 않게 배치"
3. 스도쿠: "9×9 격자에 1-9 숫자를 규칙에 맞게 채우기"
4. 미로 탐색: "출발점에서 도착점까지의 모든 경로 찾기"
5. 부분집합: "주어진 조건을 만족하는 부분집합 찾기"
6. 그래프 착색: "인접한 노드가 같은 색이 되지 않게 칠하기"
7. 단어 퍼즐: "격자에서 특정 단어들을 찾기"

【백트래킹 vs 완전탐색(브루트포스)】
- 완전탐색: 무조건 모든 경우의 수를 다 확인
- 백트래킹: 조건에 맞지 않으면 즉시 포기하고 되돌아감 (가지치기)
- 결과: 백트래킹이 훨씬 효율적!
*/

public class 백트래킹 {

    // 결과를 저장하기 위한 리스트들
    private List<List<Integer>> permutationResults = new ArrayList<>();
    private List<List<Integer>> combinationResults = new ArrayList<>();
    private List<String> sudokuResult = new ArrayList<>();
    private int nQueenCount = 0;

    public static void main(String[] args) {
        백트래킹 bt = new 백트래킹();

        System.out.println("=== 백트래킹 알고리즘 - 코딩테스트 필수 유형들 ===\n");

        // 다양한 백트래킹 문제 예시 실행
        bt.runAllExamples();
    }

    // ============== 1. 순열(Permutation) 생성 ==============
    /*
    순열: n개의 서로 다른 원소에서 r개를 선택하여 순서를 고려해 나열
    예시: [1,2,3]에서 2개를 선택하는 순열 → (1,2), (1,3), (2,1), (2,3), (3,1), (3,2)
    
    ★ 순열에서 백트래킹이 필요한 이유:
    - 같은 원소를 중복 선택하면 안 됨
    - 이미 선택한 원소는 다시 선택할 수 없음
    - 조건에 맞지 않으면 즉시 되돌아가야 함
    */
    public void generatePermutations(int[] nums, List<Integer> current, boolean[] used) {
        /* 
        ★ 매개변수 설명 (헷갈리기 쉬운 부분!)
        - nums: 원본 배열 (변하지 않음)
        - current: 현재까지 선택한 원소들을 담는 리스트 (계속 변함)
        - used: 각 원소의 사용 여부를 표시하는 배열 (true: 사용됨, false: 사용 안됨)
        
        왜 used 배열이 필요할까?
        → 순열에서는 같은 원소를 두 번 사용할 수 없으므로, 
          어떤 원소를 이미 사용했는지 기억해야 합니다!
        */

        // 기저 조건: 원하는 길이의 순열이 완성되었을 때
        if (current.size() == 2) {  // 이 예시에서는 2개짜리 순열을 생성
            // 현재 순열을 결과에 추가 (새로운 리스트를 만들어서 저장해야 함!)
            permutationResults.add(new ArrayList<>(current));
            
            /*
            ★ 왜 new ArrayList<>(current)로 복사해야 할까? (매우 중요!)
            
            만약 permutationResults.add(current)로 하면:
            - current 리스트의 "참조"가 저장됨
            - 나중에 current가 변경되면 이미 저장된 결과도 함께 변경됨!
            - 결과적으로 모든 순열이 같아져 버림
            
            예시:
            current = [1, 2] → 결과에 저장
            current = [1, 3] → 이때 이전에 저장한 [1, 2]도 [1, 3]으로 변경됨!
            
            따라서 반드시 새로운 ArrayList를 만들어서 값을 복사해야 합니다.
            */

            return;  // 순열이 완성되었으므로 재귀 종료
        }

        // 모든 원소에 대해 시도해보기
        for (int i = 0; i < nums.length; i++) {
            // 이미 사용된 원소는 건너뛰기 (가지치기!)
            if (used[i]) {
                continue;  // 이미 사용된 원소는 다시 사용할 수 없음
            }

            // 선택: 현재 원소를 순열에 추가
            current.add(nums[i]);
            used[i] = true;  // 사용 표시
            
            /*
            ★ 백트래킹의 핵심 3단계:
            1. 선택(Choose): current.add(), used[i] = true
            2. 탐색(Explore): 재귀 호출
            3. 되돌리기(Unchoose): current.remove(), used[i] = false
            */

            // 재귀 호출: 다음 위치에 올 원소 찾기
            generatePermutations(nums, current, used);

            // 백트래킹: 현재 선택을 취소하고 다른 가능성 시도
            current.remove(current.size() - 1);  // 마지막에 추가한 원소 제거
            used[i] = false;  // 사용 표시 해제
            
            /*
            ★ 왜 되돌리기가 필요할까? (직관적으로 이해하기 어려운 부분)
            
            예시로 설명:
            nums = [1, 2, 3], 2개짜리 순열 생성
            
            1. current = [1] 선택
               - generatePermutations 재귀 호출
               - current = [1, 2] 완성 → 결과 저장
               - 돌아와서 current.remove() → current = [1]이 됨
               - 이제 [1, 3]을 시도할 수 있음!
            
            만약 되돌리기를 안 하면:
            - current = [1, 2] 상태로 계속 남아있음
            - 다음에 3을 추가하면 current = [1, 2, 3]이 되어버림 (길이 초과!)
            
            즉, 되돌리기를 해야 다른 경우의 수를 올바르게 탐색할 수 있습니다.
            */
        }
    }

    // ============== 2. 조합(Combination) 생성 ==============
    /*
    조합: n개의 서로 다른 원소에서 r개를 선택 (순서 상관없음)
    예시: [1,2,3]에서 2개를 선택하는 조합 → (1,2), (1,3), (2,3)
    
    순열과의 차이점:
    - 순열: (1,2)와 (2,1)을 다른 것으로 봄
    - 조합: (1,2)와 (2,1)을 같은 것으로 봄
    */
    public void generateCombinations(int[] nums, int start, List<Integer> current) {
        /*
        ★ start 매개변수의 의미 (조합에서만 사용하는 특별한 기법!)
        
        start: 다음에 선택할 수 있는 원소의 시작 인덱스
        
        왜 start가 필요할까?
        → 조합에서는 순서가 중요하지 않으므로, 이미 고려한 원소들을 다시 고려할 필요가 없습니다.
        
        예시: nums = [1, 2, 3]
        - (1, 2)를 만들었다면, 다음에는 3만 고려하면 됨
        - (2, 1)은 (1, 2)와 같으므로 고려하지 않음
        - 이를 위해 start 인덱스를 사용하여 "이미 고려한 원소들"을 건너뜀
        */

        // 기저 조건: 원하는 길이의 조합이 완성되었을 때
        if (current.size() == 2) {  // 2개짜리 조합 생성
            combinationResults.add(new ArrayList<>(current));
            return;
        }

        // start부터 끝까지의 원소들을 시도 (이전 원소들은 이미 고려했으므로 제외)
        for (int i = start; i < nums.length; i++) {
            // 선택: 현재 원소를 조합에 추가
            current.add(nums[i]);

            // 재귀 호출: 다음 원소는 현재 인덱스+1부터 고려
            generateCombinations(nums, i + 1, current);
            
            /*
            ★ 왜 i + 1을 넘겨줄까?
            
            현재 nums[i]를 선택했으므로, 다음 재귀에서는 nums[i+1]부터 고려해야 합니다.
            이렇게 하면 중복된 조합을 방지할 수 있습니다.
            
            예시:
            - i=0 (nums[0]=1)을 선택 → 다음에는 i=1부터 고려
            - 이렇게 하면 (2,1) 같은 중복 조합이 만들어지지 않음
            */

            // 백트래킹: 현재 선택을 취소
            current.remove(current.size() - 1);
        }
    }

    // ============== 3. N-Queen 문제 ==============
    /*
    N-Queen 문제: N×N 체스판에 N개의 퀸을 서로 공격하지 않게 배치하는 문제
    
    퀸의 공격 범위:
    - 같은 행: 가로로 모든 칸
    - 같은 열: 세로로 모든 칸  
    - 대각선: 두 대각선 방향으로 모든 칸
    
    백트래킹 적용:
    - 각 행에 퀸을 하나씩 배치
    - 현재 위치에 퀸을 놓을 수 있는지 검사
    - 불가능하면 이전 행으로 백트래킹
    */
    public void solveNQueens(int n, int row, int[] queens) {
        /*
        ★ 1차원 배열로 2차원 체스판을 표현하는 기법 (공간 최적화!)
        
        queens[i] = j의 의미: i번째 행의 j번째 열에 퀸이 있음
        
        왜 이렇게 할까?
        - 각 행에는 반드시 퀸이 하나씩만 들어감 (행 제약 조건)
        - 따라서 "몇 번째 열에 있는지"만 기억하면 됨
        - 2차원 배열 대신 1차원 배열 사용으로 메모리 절약 + 코드 간소화
        
        예시: 4×4 체스판에서 queens = [1, 3, 0, 2]
        → (0,1), (1,3), (2,0), (3,2)에 퀸 배치
        */

        // 기저 조건: 모든 행에 퀸을 배치했을 때
        if (row == n) {
            nQueenCount++;  // 해답 개수 증가

            // 디버깅용: 첫 번째 해답만 출력
            if (nQueenCount == 1) {
                System.out.println("N-Queen 해답 예시 (4×4 체스판):");
                printQueens(queens);
            }
            return;
        }

        // 현재 행의 모든 열에 퀸을 놓아보기
        for (int col = 0; col < n; col++) {
            // 현재 위치 (row, col)에 퀸을 놓을 수 있는지 검사
            if (isSafe(queens, row, col)) {
                // 선택: 현재 위치에 퀸 배치
                queens[row] = col;

                // 재귀 호출: 다음 행에 퀸 배치
                solveNQueens(n, row + 1, queens);

                // 백트래킹: 현재 퀸 제거 (사실 다음 반복에서 덮어쓰여지므로 생략 가능)
                // queens[row] = -1;  // 선택사항
            }
        }
    }

    // 현재 위치에 퀸을 놓을 수 있는지 검사하는 메서드
    private boolean isSafe(int[] queens, int row, int col) {
        /*
        ★ 퀸의 공격 가능성 검사 (복잡한 수학적 관계!)
        
        이미 배치된 퀸들 (0행부터 row-1행까지)과 충돌하는지 확인:
        1. 같은 열: queens[i] == col
        2. 대각선: |queens[i] - col| == |i - row|
        
        대각선 공식이 왜 저럴까?
        → 대각선상에 있는 두 점의 x좌표 차이와 y좌표 차이의 절댓값이 같습니다!
        
        예시: (1,2)와 (3,4)는 대각선상에 있음
        - x 차이: |3-1| = 2
        - y 차이: |4-2| = 2
        - 2 == 2이므로 대각선상에 있음!
        */

        for (int i = 0; i < row; i++) {
            // 같은 열에 있는지 검사
            if (queens[i] == col) {
                return false;
            }

            // 대각선에 있는지 검사
            if (Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }

        return true;  // 안전한 위치
    }

    // N-Queen 해답을 체스판 형태로 출력하는 메서드
    private void printQueens(int[] queens) {
        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < queens.length; j++) {
                if (queens[i] == j) {
                    System.out.print("Q ");  // 퀸이 있는 위치
                } else {
                    System.out.print(". ");  // 빈 칸
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // ============== 4. 스도쿠 해결 ==============
    /*
    스도쿠 규칙:
    1. 각 행에 1-9가 하나씩만 나타나야 함
    2. 각 열에 1-9가 하나씩만 나타나야 함  
    3. 각 3×3 박스에 1-9가 하나씩만 나타나야 함
    
    백트래킹 적용:
    - 빈 칸을 찾아서 1-9를 시도
    - 규칙에 맞지 않으면 백트래킹
    - 모든 칸을 채우면 완성
    */
    public boolean solveSudoku(int[][] board) {
        // 빈 칸 찾기 (0으로 표시된 칸)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {  // 빈 칸 발견

                    // 1부터 9까지 시도해보기
                    for (int num = 1; num <= 9; num++) {
                        // 현재 위치에 num을 놓을 수 있는지 검사
                        if (isValidSudoku(board, row, col, num)) {
                            // 선택: 현재 위치에 숫자 배치
                            board[row][col] = num;

                            // 재귀 호출: 다음 빈 칸 해결
                            if (solveSudoku(board)) {
                                return true;  // 해답 찾음!
                            }

                            // 백트래킹: 현재 숫자 제거
                            board[row][col] = 0;
                        }
                    }

                    // 1-9 모두 시도했지만 해답이 없음
                    return false;
                }
            }
        }

        // 모든 칸이 채워짐 → 스도쿠 완성!
        return true;
    }

    // 스도쿠 규칙 검사 메서드
    private boolean isValidSudoku(int[][] board, int row, int col, int num) {
        // 1. 같은 행에 num이 있는지 검사
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        // 2. 같은 열에 num이 있는지 검사
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // 3. 같은 3×3 박스에 num이 있는지 검사
        /*
        ★ 3×3 박스의 시작 좌표 계산 (헷갈리기 쉬운 부분!)
        
        9×9 스도쿠는 3×3 박스 9개로 구성됨:
        박스 시작 좌표 = (행/3 * 3, 열/3 * 3)
        
        예시: (4, 7) 위치는 어느 박스에 속할까?
        - 행: 4/3 = 1, 1*3 = 3 → 박스의 시작 행은 3
        - 열: 7/3 = 2, 2*3 = 6 → 박스의 시작 열은 6
        - 따라서 (3,6)부터 (5,8)까지의 3×3 박스에 속함
        */
        int boxStartRow = (row / 3) * 3;
        int boxStartCol = (col / 3) * 3;

        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartCol; j < boxStartCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;  // 모든 규칙을 만족함
    }

    // ============== 5. 부분집합의 합 문제 ==============
    /*
    부분집합의 합 문제: 주어진 집합에서 합이 특정 값이 되는 부분집합 찾기
    
    예시: [1, 2, 3, 4]에서 합이 5가 되는 부분집합 찾기
    → {1, 4}, {2, 3} 등
    
    백트래킹 적용:
    - 각 원소를 포함할지 말지 결정
    - 현재 합이 목표값을 초과하면 가지치기
    - 목표값과 같으면 해답 저장
    */
    public void findSubsetSum(int[] nums, int target, int index, List<Integer> current, int currentSum) {
        /*
        ★ 매개변수 설명:
        - nums: 원본 배열
        - target: 목표 합계
        - index: 현재 고려 중인 원소의 인덱스
        - current: 현재까지 선택한 원소들
        - currentSum: 현재까지의 합계
        
        왜 currentSum을 따로 관리할까?
        → 매번 current 리스트를 순회해서 합을 계산하는 것보다
          변수로 관리하는 것이 훨씬 효율적이기 때문입니다!
        */

        // 가지치기: 현재 합이 이미 목표값을 초과한 경우
        if (currentSum > target) {
            return;  // 더 이상 진행해봐야 의미없음 (모든 원소가 양수라고 가정)
        }

        // 기저 조건: 목표 합에 도달한 경우
        if (currentSum == target) {
            System.out.println("부분집합 발견: " + current + " (합: " + currentSum + ")");
            return;
        }

        // 기저 조건: 모든 원소를 고려했을 때
        if (index >= nums.length) {
            return;
        }
        
        /*
        ★ 부분집합에서의 선택 (이진 트리 형태의 탐색!)
        
        각 원소에 대해 두 가지 선택이 있습니다:
        1. 현재 원소를 부분집합에 포함시키기
        2. 현재 원소를 부분집합에 포함시키지 않기
        
        이것이 바로 "백트래킹"의 핵심입니다!
        모든 가능성을 체계적으로 탐색하되, 조건에 맞지 않으면 즉시 포기합니다.
        */

        // 선택 1: 현재 원소를 포함시키기
        current.add(nums[index]);
        findSubsetSum(nums, target, index + 1, current, currentSum + nums[index]);
        current.remove(current.size() - 1);  // 백트래킹

        // 선택 2: 현재 원소를 포함시키지 않기
        findSubsetSum(nums, target, index + 1, current, currentSum);
    }

    // ============== 모든 예시 실행 메서드 ==============
    public void runAllExamples() {
        // 1. 순열 생성 예시
        System.out.println("=== 1. 순열(Permutation) 생성 ===");
        System.out.println("문제: [1, 2, 3]에서 2개를 선택하는 모든 순열");
        System.out.println("코딩테스트 출제 유형: '주어진 숫자들로 만들 수 있는 모든 경우의 수'");

        int[] nums1 = {1, 2, 3};
        permutationResults.clear();
        generatePermutations(nums1, new ArrayList<>(), new boolean[nums1.length]);

        System.out.println("결과 (" + permutationResults.size() + "개):");
        for (List<Integer> perm : permutationResults) {
            System.out.println(perm);
        }
        System.out.println("시간복잡도: O(n × n!) - n개 원소의 순열은 n!개, 각각 복사하는데 O(n)\n");

        // 2. 조합 생성 예시  
        System.out.println("=== 2. 조합(Combination) 생성 ===");
        System.out.println("문제: [1, 2, 3]에서 2개를 선택하는 모든 조합");
        System.out.println("코딩테스트 출제 유형: '팀 구성', '아이템 선택' 등");

        int[] nums2 = {1, 2, 3};
        combinationResults.clear();
        generateCombinations(nums2, 0, new ArrayList<>());

        System.out.println("결과 (" + combinationResults.size() + "개):");
        for (List<Integer> comb : combinationResults) {
            System.out.println(comb);
        }
        System.out.println("시간복잡도: O(C(n,r)) - nCr = n!/(r!(n-r)!)\n");

        // 3. N-Queen 문제 예시
        System.out.println("=== 3. N-Queen 문제 ===");
        System.out.println("문제: 4×4 체스판에 4개의 퀸을 서로 공격하지 않게 배치");
        System.out.println("코딩테스트 출제 유형: '조건 만족 문제', '최적 배치 문제'");

        nQueenCount = 0;
        int[] queens = new int[4];  // 4×4 체스판
        solveNQueens(4, 0, queens);

        System.out.println("총 해답 개수: " + nQueenCount + "개");
        System.out.println("시간복잡도: O(N!) - 최악의 경우이지만 가지치기로 실제로는 훨씬 적음\n");

        // 4. 스도쿠 예시
        System.out.println("=== 4. 스도쿠 해결 ===");
        System.out.println("문제: 9×9 스도쿠 퍼즐 해결 (0은 빈 칸)");
        System.out.println("코딩테스트 출제 유형: '제약 조건 만족 문제', '퍼즐 해결'");

        // 간단한 스도쿠 예시 (실제로는 더 복잡함)
        int[][] sudoku = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("스도쿠 해결 전:");
        printSudoku(sudoku);

        if (solveSudoku(sudoku)) {
            System.out.println("스도쿠 해결 후:");
            printSudoku(sudoku);
        } else {
            System.out.println("해답이 없습니다.");
        }
        System.out.println("시간복잡도: O(9^k) - k는 빈 칸의 개수, 가지치기로 실제로는 훨씬 빠름\n");

        // 5. 부분집합의 합 문제
        System.out.println("=== 5. 부분집합의 합 문제 ===");
        System.out.println("문제: [1, 2, 3, 4, 5]에서 합이 5가 되는 부분집합 찾기");
        System.out.println("코딩테스트 출제 유형: '동전 문제', '배낭 문제', '타겟 넘버'");

        int[] nums5 = {1, 2, 3, 4, 5};
        int target = 5;
        System.out.println("발견된 부분집합들:");
        findSubsetSum(nums5, target, 0, new ArrayList<>(), 0);
        System.out.println("시간복잡도: O(2^n) - 각 원소를 포함/미포함 2가지 선택\n");

        // 6. 백트래킹 최적화 기법 설명
        System.out.println("=== 6. 백트래킹 최적화 기법 ===");
        demonstrateOptimization();

        // 7. 코딩테스트 팁
        System.out.println("=== 7. 코딩테스트에서 백트래킹 문제 해결 팁 ===");
        showCodingTestTips();
    }

    // 스도쿠 출력 메서드
    private void printSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 백트래킹 최적화 기법 시연
    private void demonstrateOptimization() {
        System.out.println("【가지치기(Pruning) 최적화 기법들】");
        System.out.println();

        System.out.println("1. 조건 검사 순서 최적화:");
        System.out.println("   - 실패 확률이 높은 조건을 먼저 검사");
        System.out.println("   - 예: N-Queen에서 열 체크 → 대각선 체크 순서");
        System.out.println();

        System.out.println("2. 중복 계산 방지:");
        System.out.println("   - 이미 계산한 결과는 저장해서 재사용");
        System.out.println("   - 예: 스도쿠에서 행/열/박스별 사용된 숫자 미리 저장");
        System.out.println();

        System.out.println("3. 해답 개수 제한:");
        System.out.println("   - 첫 번째 해답만 필요한 경우 찾으면 즉시 중단");
        System.out.println("   - 최대 K개의 해답만 찾기");
        System.out.println();

        System.out.println("4. 대칭성 활용:");
        System.out.println("   - N-Queen에서 대칭된 해답 제거");
        System.out.println("   - 회전/반사 대칭을 고려한 중복 제거");
        System.out.println();

        // 최적화 예시: 개선된 부분집합 합 문제
        System.out.println("【최적화 예시: 부분집합 합 문제 개선】");
        int[] nums = {1, 2, 3, 4, 5, 6};
        int target = 10;

        System.out.println("배열을 정렬하여 가지치기 효과 극대화:");
        Arrays.sort(nums);  // 정렬하면 더 효율적인 가지치기 가능
        System.out.println("정렬된 배열: " + Arrays.toString(nums));

        System.out.println("최적화된 탐색 결과:");
        findOptimizedSubsetSum(nums, target, 0, new ArrayList<>(), 0);
        System.out.println();
    }

    // 최적화된 부분집합 합 찾기 (정렬을 활용한 가지치기)
    private void findOptimizedSubsetSum(int[] nums, int target, int index, List<Integer> current, int currentSum) {
        // 기존과 동일한 기저 조건들
        if (currentSum == target) {
            System.out.println("최적화된 탐색으로 발견: " + current);
            return;
        }

        if (index >= nums.length || currentSum > target) {
            return;
        }
        
        /*
        ★ 최적화 기법: 남은 원소들의 최솟값으로도 목표에 도달할 수 없으면 포기
        
        배열이 정렬되어 있으므로:
        - nums[index]는 남은 원소들 중 가장 작은 값
        - 현재 합 + 가장 작은 값 > 목표값이면 더 이상 진행 불가능
        - 이런 가지치기로 탐색 시간을 대폭 단축!
        */

        // 현재 원소를 포함하는 경우
        current.add(nums[index]);
        findOptimizedSubsetSum(nums, target, index + 1, current, currentSum + nums[index]);
        current.remove(current.size() - 1);

        // 현재 원소를 포함하지 않는 경우
        findOptimizedSubsetSum(nums, target, index + 1, current, currentSum);
    }

    // 코딩테스트 해결 팁
    private void showCodingTestTips() {
        System.out.println("【백트래킹 문제 식별 방법】");
        System.out.println("✓ '모든 경우의 수'를 구하라고 하는 문제");
        System.out.println("✓ '가능한 방법의 개수'를 묻는 문제");
        System.out.println("✓ '조건을 만족하는 해가 존재하는가?' 문제");
        System.out.println("✓ 'N개 중에서 R개를 선택' 관련 문제");
        System.out.println("✓ '순열, 조합, 부분집합' 관련 문제");
        System.out.println();

        System.out.println("【백트래킹 구현 체크리스트】");
        System.out.println("1. □ 상태 공간 정의 (어떤 변수들로 상태를 나타낼까?)");
        System.out.println("2. □ 기저 조건 설정 (언제 재귀를 종료할까?)");
        System.out.println("3. □ 선택지 정의 (다음에 어떤 선택들이 가능할까?)");
        System.out.println("4. □ 유효성 검사 (현재 선택이 조건에 맞는가?)");
        System.out.println("5. □ 선택-탐색-되돌리기 구현");
        System.out.println("6. □ 가지치기 최적화 (불필요한 탐색 제거)");
        System.out.println();

        System.out.println("【자주 하는 실수와 해결책】");
        System.out.println();

        System.out.println("❌ 실수 1: 백트래킹(되돌리기)을 빠뜨림");
        System.out.println("✅ 해결책: 선택 후 재귀 호출, 그 다음에 반드시 되돌리기");
        System.out.println("   예: list.add() → 재귀호출 → list.remove()");
        System.out.println();

        System.out.println("❌ 실수 2: 결과 저장 시 참조 문제");
        System.out.println("✅ 해결책: new ArrayList<>(current)로 복사해서 저장");
        System.out.println("   잘못된 예: results.add(current)");
        System.out.println("   올바른 예: results.add(new ArrayList<>(current))");
        System.out.println();

        System.out.println("❌ 실수 3: 중복 제거를 놓침");
        System.out.println("✅ 해결책: 조합에서는 start 인덱스 활용, 순열에서는 visited 배열 활용");
        System.out.println();

        System.out.println("❌ 실수 4: 시간 초과 (최적화 부족)");
        System.out.println("✅ 해결책: 조건 검사 순서 최적화, 불가능한 경우 조기 종료");
        System.out.println();

        System.out.println("【코딩테스트 문제 유형별 템플릿】");
        System.out.println();

        System.out.println("🔹 순열 생성 템플릿:");
        System.out.println("   void permutation(배열, 현재결과, visited배열) {");
        System.out.println("       if (완성조건) { 결과저장; return; }");
        System.out.println("       for (각 원소에 대해) {");
        System.out.println("           if (!visited[i]) {");
        System.out.println("               선택; 재귀호출; 되돌리기;");
        System.out.println("           }");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println();

        System.out.println("🔹 조합 생성 템플릿:");
        System.out.println("   void combination(배열, start, 현재결과) {");
        System.out.println("       if (완성조건) { 결과저장; return; }");
        System.out.println("       for (int i = start; i < 배열길이; i++) {");
        System.out.println("           선택; 재귀호출(i+1); 되돌리기;");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println();

        System.out.println("🔹 조건 만족 문제 템플릿:");
        System.out.println("   boolean solve(상태) {");
        System.out.println("       if (목표달성) return true;");
        System.out.println("       if (불가능조건) return false;");
        System.out.println("       for (각 선택지에 대해) {");
        System.out.println("           if (유효한선택) {");
        System.out.println("               선택; if(재귀호출) return true; 되돌리기;");
        System.out.println("           }");
        System.out.println("       }");
        System.out.println("       return false;");
        System.out.println("   }");
        System.out.println();

        System.out.println("【시간복잡도 개선 전략】");
        System.out.println("1. 정렬 활용: 배열을 정렬하여 조기 종료 조건 만들기");
        System.out.println("2. 메모이제이션: 동일한 상태의 중복 계산 방지");
        System.out.println("3. 비트마스킹: visited 배열 대신 비트 연산 활용");
        System.out.println("4. 대칭성 활용: 회전/반사 대칭인 경우들 제거");
        System.out.println("5. 휴리스틱: 가능성 높은 선택지부터 시도");
        System.out.println();

        System.out.println("=== 백트래킹 마스터하기 위한 추천 연습 문제 ===");
        System.out.println("🟢 초급: 순열, 조합, 부분집합 기본 문제");
        System.out.println("🟡 중급: N-Queen, 스도쿠, 단어 검색");
        System.out.println("🔴 고급: 복잡한 조건의 최적화 문제, 게임 트리 탐색");
        System.out.println();
        System.out.println("💡 핵심은 '체계적인 탐색 + 똑똑한 가지치기' 입니다!");
        System.out.println("많은 연습을 통해 다양한 패턴을 익혀보세요! 🚀");
    }
}