package org.pratice.재귀;

public class 배열합 {
    public static void main(String[] args) {
        // data[0] 부터 data[n-1]의 합
        int n = 3;
        int[] data = new int[]{1,2,3,4,5};
        System.out.println(sum(n, data));
    }

    static int sum(int n, int[] data) {
        if (n <= 0) {
            return 0; // 베이스 케이스
        }

        return sum(n-1, data) + data[n-1];
    }

    //sum 3
    //sum 2
    //sum 1
    //sum 0
    //sum 0 0 리턴
    //sum 1 1 리턴
    //sum 2 3 리턴
    //sum 3 6 리턴
}
