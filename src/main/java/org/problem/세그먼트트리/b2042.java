package org.problem.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드 1 구간 합 구하기
public class b2042 {
    static long[] arr;
    static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 2^l >= n을 만족하는 최소의 l
        for (int i = 1; i < n; i++) {
            if (Math.pow(2, i) >= n) {
                l = i;
                break;
            }
        }
        arr = new long[(int) (Math.pow(2, l) * 2)]; // 세그먼트트리의 길이는 (2^l) * l

        for (int i = 0; i < n; i++) {
            arr[(int) (Math.pow(2, l) + i)] = Long.parseLong(br.readLine()); // 원본배열은 2 ^ l부터 삽입
        }

        // 세그먼트 트리 채우기
        for (int i = (int) (Math.pow(2, l) + (n - 1)); i > 1; i--) { // 원본배열이 담긴 마지막 부터 계산
            int index = i / 2;
            arr[index] += arr[i];
        }

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // b를 c로 교체
                update(b, c);
            } else {  // b부터 c까지 합을 구하여 출력
                long result = query(b, (int) c);
                System.out.println(result);
            }
        }
    }

    private static void update(int index, long newValue) {
        // 질의 인덱스를 세그먼트 트리의 리프 노드 인덱스로 변환
        int treeIndex = index + (int) Math.pow(2, l) - 1;

        // 변경될 값과 기존 값의 차이 계산
        long difference = newValue - arr[treeIndex];

        // 리프 노드의 값을 업데이트
        while (treeIndex > 0) {
            arr[treeIndex] += difference;
            treeIndex = treeIndex / 2;
        }
    }

    private static long query(int start, int end) {
        // 질의 인덱스를 세그먼트 트리의 리프 노드 인덱스로 변환
        int startNode = start + (int) Math.pow(2, l) - 1;
        int endNode = end + (int) Math.pow(2, l) - 1;

        long result = 0;
        while (startNode <= endNode) {
            // startNode가 오른쪽 자식(홀수 인덱스)인 경우:
            // 현재 노드의 값(arr[startNode])을 결과에 더하고, 부모의 다음(오른쪽) 자식으로 이동 (startNode + 1) / 2
            if (startNode % 2 == 1) {
                result += arr[startNode];
                startNode = (startNode + 1) / 2;
            } else {
                // startNode가 왼쪽 자식(짝수 인덱스)인 경우:
                // 값은 더하지 않고, 부모로 이동 startNode / 2
                startNode = startNode / 2;
            }

            // endNode가 왼쪽 자식(짝수 인덱스)인 경우:
            // 현재 노드의 값(arr[endNode])을 결과에 더하고, 부모의 이전(왼쪽) 자식으로 이동 (endNode - 1) / 2
            if (endNode % 2 == 0) {
                result += arr[endNode];
                endNode = (endNode - 1) / 2;
            } else {
                // endNode가 오른쪽 자식(홀수 인덱스)인 경우:
                // 값은 더하지 않고, 부모로 이동 endNode / 2
                endNode = endNode / 2;
            }
        }

        return result;
    }
}
