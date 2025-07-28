package org.problem.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드 5 용액
// 시간 제한 1초 수는 10만 n(logN)까지 허용
public class b2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 0에 가장 가까운걸 만들어내려면? 양 끝에서부터 탐색한다고하자
        // 만약 양 끝해서 했을때 0을 초과할때가 있을 것 그럼 끝에것을 한 칸 내려야한다
        // 0보다 안되면? 그럼 덜빼야하니까 앞에 것을 늘려야한다
        // 그리고 딱 0이면 break하고 출력하면되는데 그게 아니면 중간결과가 최선의 결과일수도 있으니 값은 저장해둔다
        // 투포인터만 써도 풀릴 것 같음 근데 이분탐색으로 한번해보자
        // 근데 이분탐색으론 잘모르겠다 만약 -1억 -1 1 1억2만 1억2만1 1억2만3 1억2만4가 있을 때
        // 왼쪽 덩어리가 2만의 차이 오른쪽 덩어리가 4의 차이니까 오른쪽 덩어리부터 탐색을 시작하면
        // 왼쪽 덩어리안에는 -1 1이 있으니 정답을 놓칠수도있을거같다

        int[] result = new int[2];

        int start = 0;
        int end = arr.length - 1;
        result[0] = arr[start];
        result[1] = arr[end];

        while (start < end) {
            if (arr[end] + arr[start] == 0) {
                result[0] = arr[start];
                result[1] = arr[end];
                break;
            }

            if (arr[end] + arr[start] > 0) {
                if (Math.abs(arr[end] + arr[start]) < Math.abs(result[0] + result[1])) {
                    result[0] = arr[start];
                    result[1] = arr[end];
                }
                end -= 1;
            } else {
                if (Math.abs(arr[end] + arr[start]) < Math.abs(result[0] + result[1])) {
                    result[0] = arr[start];
                    result[1] = arr[end];
                }
                start += 1;
            }
        }
        System.out.println(result[0] + " " + result[1]);
    }
}
