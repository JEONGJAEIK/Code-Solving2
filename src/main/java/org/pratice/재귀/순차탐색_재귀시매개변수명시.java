package org.pratice.재귀;

public class 순차탐색_재귀시매개변수명시 {

    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,4,5};
        int n = 4;
        int target = 3;
        System.out.println(search(data,n,target));
        System.out.println(search2(data,0,n,target));
        System.out.println(search3(data,0,n,target));
        System.out.println(search4(data,0,n,target));
    }
    // 반복돌리는 순차탐색 여기서 0은 암시적
    public static int search(int[] data, int n, int target) {
        for (int i=0; i<n; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 재귀시에는 명시적이 좋음
    public static int search2(int[] data, int begin, int end, int target) {
        if (begin>end) {
            return -1;
        } else if (target == data[begin]) {
            return begin;
        } else {
            return search2(data, begin+1, end, target);
        }
    }

    // 뒤에서 부터 줄이는 방식
    public static int search3(int[] data, int begin, int end, int target) {
        if (begin>end) {
            return -1;
        } else if (target == data[end]) {
            return end;
        } else {
            return search3(data, begin, end-1, target);
        }
    }

    // 반씩쪼개는 방법 이진탐색과는 다름
    public static int search4(int[] data, int begin, int end, int target) {
        if (begin>end) {
            return -1;
        } else {
            int middle = (begin+end) /2;
            if (data[middle] == target) {
                return middle;
            }
            int index = search4(data,begin,middle-1,target);
            if (index != -1) {
                return index;
            } else {
                return search4(data,middle+1,end,target);
            }
        }
    }
}
