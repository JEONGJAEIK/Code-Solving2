package org.problem.dp;

// 레벨 3 정수 삼각형
public class p43105 {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle));


    }

    public static int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];

        // 잘 생각해보자 숫자가 제일 적은 구린길에 갑자기 9999라는 대박이 터졌다. 근데 그 길을 계산할 수 있는가?
        // 못하지 그러니까 이건 2차원 dp배열로 가야함

        dp[0][0] = triangle[0][0];
        // dp[1][0] = dp[0][0] + triangle[1][0];
        // dp[1][1] = dp[0][0] + triangle[1][1];
        // dp[2][0] = dp[1][0] + triangle[2][0];
        // dp[2][1] = Math.max(dp[1][0], dp[1][1]) + triange[2][1];
        // dp[2][2] = dp[1][1] + triangle[2][2];
        // dp[3][0] = dp[2][0] + triangle[3][0];
        // dp[3][1] = Math.max(dp[2][0], dp[2][1]) + triange[3][1];
        // dp[3][2] = Math.max(dp[2][1], dp[2][2]) + triange[3][1];
        // dp[3][3] = dp[2][2] + triangle[3][3];

        // 이제 보인다
        // 점화식은 i가 1이상일때
        // j가 0이라면 dp[i][j] = dp[i-1][j] + triangle[i][j];
        // j가 i와 같다면 dp[i][j] = dp[i-1][j-1] + triangle[i][j];
        // 그게아니라면 Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]; 다

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < dp[dp.length - 1].length; i++) {
            if (dp[dp.length - 1][i] > answer) {
                answer = dp[dp.length - 1][i];
            }
        }
        return answer;
    }
}
