package org.example;

public class 조합 {
    public static void combination(int[] arr, boolean[] visited, int start, int depth, int r){
        if(depth == r){
            for(int i=0; i<arr.length; i++){
                if(visited[i]) System.out.print(arr[i]);
            }
            System.out.println();
            return;
        }
        for(int i=start; i<arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                combination(arr, visited, i+1, depth+1, r);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3};
        int r = 2;
        combination(arr, new boolean[arr.length], 0, 0, r);
    }
}
