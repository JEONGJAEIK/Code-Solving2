package org.pratice.재귀;

public class 이진탐색 {
    // 이진탐색은 데이터가 정렬되어있을때 가능함
    public static void main(String[] args) {
        String[] items = new String[]{"a","b","c"};
        String target = "b";
        int begin = 0;
        int end = 2;
        System.out.println(binarysearch(items,target,begin,end));
    }
    public static int binarysearch(String[] items, String target, int begin, int end) {
        if (begin > end) {
            return -1;
        } else {
            int middle = (begin + end) / 2;
            int compResult = target.compareTo(items[middle]); // compareTo 동일하면 0 작으면 -1 크면 1
            if (compResult == 0) {
                return middle;
            } else if (compResult < 0) {
                return binarysearch(items, target, begin, middle - 1);
            } else {
                return binarysearch(items, target, middle + 1, end);
            }
        }
    }
}
