package org.problem.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 골드 1 최솟값과 최댓값
public class b2357 {
    static int[] minSegmentTree;
    static int[] maxSegmentTree;
    static int mid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 주어지는 정수 개수 1~10만
        int m = Integer.parseInt(st.nextToken()); // 주어지는 질의 개수 1~10만

        // 세그먼트트리 깊이 구하기
        int depth = 0;
        for (int i = 1; i < n; i++) {
            if (Math.pow(2, i) >= n) {
                depth = i;
                break;
            }
        }

        // 중간점 찾기
        mid = (int) Math.pow(2, depth);

        // 길이 할당
        minSegmentTree = new int[mid * 2];
        maxSegmentTree = new int[mid * 2];

        // 세그먼트 트리 초기 값
        Arrays.fill(minSegmentTree, 1000000001);
        Arrays.fill(maxSegmentTree, 0);

        // 입력값 주입
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine()); // 1 ~ 10억
            minSegmentTree[mid + i] = input;
            maxSegmentTree[mid + i] = input;
        }

        // 세그먼트 트리 초기화
        for (int i = maxSegmentTree.length - 2; i > 0; i--) {
            int index = i / 2;
            minSegmentTree[index] = Math.min(minSegmentTree[index * 2], minSegmentTree[index * 2 + 1]);
            maxSegmentTree[index] = Math.max(maxSegmentTree[index * 2], maxSegmentTree[index * 2 + 1]);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            query(a, b);
        }
    }

    private static void query(int start, int end) {
        int startTreeIndex = start + mid - 1;
        int endTreeIndex = end + mid - 1;
        int minResult = 1000000001;
        int maxResult = 0;

        // 모두가 % 2 예외 상황에 안걸린다고해도 최종 부모가 result가 되는게 보장이된다.
        // 1,2,3,4가 주어지면 최종적으로 인덱스 2,3을 거쳐 1로 수렴하게 됨
        // 1에서 start는 결과에 저장을하고 다시 start는 1로 end는 1에서 저장을하지 않고 0으로가면서 while이 종료된다,
        while (startTreeIndex <= endTreeIndex) {
            if (startTreeIndex % 2 == 1) {
                minResult = Math.min(minResult, minSegmentTree[startTreeIndex]);
                maxResult = Math.max(maxResult, maxSegmentTree[startTreeIndex]);
                startTreeIndex = (startTreeIndex + 1) / 2;
            } else {
                startTreeIndex = startTreeIndex / 2;
            }

            if (endTreeIndex % 2 == 0) {
                minResult = Math.min(minResult, minSegmentTree[endTreeIndex]);
                maxResult = Math.max(maxResult, maxSegmentTree[endTreeIndex]);
                endTreeIndex = (endTreeIndex - 1) / 2;
            } else {
                endTreeIndex = endTreeIndex / 2;
            }
        }
        System.out.println(minResult + " " + maxResult);
    }
}