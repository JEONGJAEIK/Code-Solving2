package org.problem.bd우선탐색;

public class p43165 {
    static int count = 0;

    static public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        int answer = count;
        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        solution(numbers, target);
    }

    static void dfs(int[] numbers, int target, int index, int sum) {
        if (index == numbers.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
            dfs(numbers, target, index + 1, sum + numbers[index]);
            dfs(numbers, target, index + 1, sum - numbers[index]);

    }
}
