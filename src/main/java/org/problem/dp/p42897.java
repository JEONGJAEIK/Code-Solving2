package org.problem.dp;

import java.util.Arrays;

// 레벨 4 도둑질
public class p42897 {
    public static void main(String[] args) {
        int[] money = new int[]{1, 2, 99, 1, 2, 10000};
        System.out.println(solution(money));
    }

    public static int solution(int[] money) {
        int result = 0;
        // (1, 3), (2, 4) 경우의 수
        // 다만 5개 집이 있을때는 (1, 4) 이렇게 2칸 띄운것도 가능하다
        // 한마디로 dp[0]이란 한 끝 점의 최대의 수
        // 원모양이기 때문에 한 끝 점을 잡으면 마지막 끝점을 없애는 효과가 있다
        // 그래서 두번째 점을 잡는 dp배열이 하나 더 필요하다
        // 예시가 1, 2, 99, 1, 2 경우에는 답이 (3, 5)다 1번에서 시작하거나 2번에서 시작하면 이 답에 도달할수 없다
        // 따라서 세번째 점을 잡는 dp배열이 필요하다.
        int[] dp = new int[money.length];
        int[] dp2 = new int[money.length];
        int[] dp3 = new int[money.length];

        // 집이 3개인 경우 0을 리턴하면된다 따라서 작성안해도됨

        // 집이 4개인 경우다
        if (money.length == 4) {
            result = Math.max(money[0] + money[2], money[1] + money[3]);
        }

        // 집 5개
        if (money.length == 5) {
            int a1 = money[0] + money[2];
            int a2 = money[0] + money[3];
            int a3 = money[1] + money[3];
            int a4 = money[1] + money[4];
            int a5 = money[2] + money[4];
            int[] temp = new int[5];
            temp[0] = a1;
            temp[1] = a2;
            temp[2] = a3;
            temp[3] = a4;
            temp[4] = a5;

            Arrays.sort(temp);
            result = temp[temp.length - 1];
        }

        // 집이 6개인 경우
        // 0의 집을 선택한 경우 2를 선택하지 않고 0,3 으로 하는 방법이 있다.
        // dp[1]에 그것을 정의하게 될경우 dp[2]에서 비교가 불가능해진다. 따라서 dp의 각 칸은 money에 맞추는게 맞다.
        if (money.length >= 6) {

            dp[0] = money[0];
            dp[1] = dp[0];
            dp[2] = dp[0] + money[2];
            // dp[3] = Math.max(dp[2], dp[1] + money[3]); 한마디로 집이 6개이상이며 dp[3]부터는 아래의 dp 2개를 비교하면 된다
            // dp[4] = Math.max(dp[3], dp[2] + money[4]);
            // 점화식은 집이 5개이상이며 i가 3이상일때 dp[i]는 Math.max(dp[i - 1], dp[i - 2] + money[i] 다
            // 대신 dp[4]일때는 money[4]를 더하면 안 됨

            // dp2의 경우엔 dp0이 없다
            // 1의 집을 선택한 경우 1,3을 선택하지않고 1,4로 가는 방법이 있다.
            dp2[1] = money[1];
            dp2[2] = dp2[1];
            dp2[3] = dp2[1] + money[3];
            // dp2[4] = Math.max(dp2[3], dp2[2] + money[4]);
            // 점화식은 집이 5개이상이며 i가 4이상일때 dp2[i]는 Math.max(dp2[i - 1], dp2[i - 2] + money[i]) 다

            // dp3의 경우 dp 0,1이 없다.
            dp3[2] = money[2];
            dp3[3] = dp3[2];
            dp3[4] = dp3[2] + money[4];
            // dp3의 경우는 점화식이 i가 5이상일때부터 발생한다. 점화식은 이전과 같다

            for (int i = 3; i < dp.length - 1; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
            }

            for (int i = 4; i < dp2.length; i++) {
                dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
            }

            for (int i = 5; i < dp3.length; i++) {
                dp3[i] = Math.max(dp3[i - 1], dp3[i - 2] + money[i]);
            }

            int TempMax = Math.max(dp[dp.length - 2], dp2[dp2.length - 1]);
            result = Math.max(TempMax, dp3[dp3.length - 1]);
        }

        return result;
    }
}
