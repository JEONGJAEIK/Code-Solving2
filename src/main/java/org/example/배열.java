package org.example;

import java.util.Arrays;

public class 배열 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        int[] arr2 = Arrays.copyOfRange(arr, 2, 5);
        System.out.println(Arrays.toString(arr2));
    }
}
