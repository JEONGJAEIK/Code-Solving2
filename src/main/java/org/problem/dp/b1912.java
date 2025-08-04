package org.problem.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실버 2 연속합
// LIS와 관련해서 풀 수 있던거 같은데.. 그게 뭔지 잘 기억이 안난다.. 일단 생각으로 해보자
public class b1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] arr = new int[count];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 일단 간단하게 양수를 더하면서 가다가 음수가 나오면 중지하면 된다. 음수가 나오면 그 이전이 그 시작 인덱스의 수열의 연속 부분집합중 최댓값 일테니
        // 그리고 음수가 나오면 넘기다가 양수가 나오면 반복해서 값을 비교하면 될거같은데.. 시간초과뜨려나
        // 아니 그렇게 생각해서는 안되다 1 2 3 4 -1 1000 이 있으면 -1을 버리더라도 전체의 집합이 최댓값이다.
        // 음수가 나오면 중지하는 것이 아니고 합이 0이 될 때 중지하는 것이다. 그럼 뒤에 어떠한 큰 수가 나오더라도 음수를 포함한 부분집합이 정답이 될 수 없다.
        // 다만 부분집합의 시작값이 음수면 건너뛰어도 된다. 어떠한 음수가 나와도 DP의 합이 0이 되지않는다면 음수이전의 부분을 버리지않는것이 최대합이라는 것을 보장한다.

        // dp가 0이 되지 않는 선에서 dp[n] = dp[n - 1] + arr[n] 이다.
        // 만약 dp[n]이 0이나 음수가 된다면 그대로 두고 새로 다음부터 새로 시작한다. dp[n] = arr[n]


        int[] dp = new int[count];
        dp[0] = arr[0];

        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = arr[i];
            } else {
                dp[i] = dp[i - 1] + arr[i];
            }
        }

        Arrays.sort(dp);
        System.out.println(dp[dp.length - 1]);
    }
}
