package org.problem.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버 3 다음 순열
// 중복을 허용하지 않는 순열 문제다 다만 굳이 중복을 찾는 로직을 만들필요는 없을듯
// 근데 순열어케 만드는지 까먹었다 클났다
public class b10932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (nextPermutation(arr)) {
            StringBuilder sb = new StringBuilder();
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;

        // 뒤에서부터 i-1 < i 찾기
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        if (i <= 0) return false;  // 마지막 순열

        // i-1보다 큰 수 중 가장 끝에 있는 수와 교환
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) {
            j--;
        }

        // swap(i-1, j)
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        // i부터 끝까지 오름차순 정렬 (reverse)
        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return true;
    }
}
