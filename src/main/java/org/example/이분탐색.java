package org.example;

import java.util.*;

/*
=== 이분탐색(Binary Search) 알고리즘 ===

【알고리즘 개념】
 이분탐색은 "정렬된 배열"에서 특정 값을 찾는 효율적인 검색 알고리즘입니다.
 매번 탐색 범위를 절반으로 줄여나가면서 목표 값을 찾아갑니다.

【핵심 아이디어 - 왜 절반씩 줄일 수 있을까?】
 배열이 정렬되어 있다는 것이 핵심입니다!
- 중간값이 찾는 값보다 크면 → 오른쪽 절반은 모두 더 큰 값들이므로 버림
- 중간값이 찾는 값보다 작으면 → 왼쪽 절반은 모두 더 작은 값들이므로 버림
- 이렇게 매번 절반씩 제거할 수 있어서 매우 빠름!

【동작 원리】
1. 배열의 시작점(left)과 끝점(right)을 설정
2. 중간점(mid)을 계산: mid = (left + right) / 2
3. 중간값과 찾는 값을 비교:
   - 중간값 == 찾는 값 → 찾았음! 인덱스 반환
   - 중간값 > 찾는 값 → 오른쪽 절반 버리고 왼쪽에서 계속 탐색 (right = mid - 1)
   - 중간값 < 찾는 값 → 왼쪽 절반 버리고 오른쪽에서 계속 탐색 (left = mid + 1)
4. left > right가 될 때까지 2-3 반복 (더 이상 탐색할 구간이 없으면 종료)

【시간복잡도】
- 최선의 경우: O(1) - 첫 번째 중간값이 바로 답인 경우
- 평균/최악의 경우: O(log N) - 매번 절반씩 줄여나가므로 log₂N번 비교
- 예시: 1,000,000개 배열에서도 최대 20번 정도만 비교하면 찾을 수 있음!

【사전 지식】
- 배열의 개념과 인덱스 사용법
- 정렬된 배열의 특성 (오름차순/내림차순)
- 반복문과 조건문의 기본 사용법
- 로그(log)의 기본 개념 (2의 몇 제곱이 N인가?)

【주의사항】
- 반드시 정렬된 배열에서만 사용 가능!
- 정렬되지 않은 배열에서는 잘못된 결과가 나올 수 있음
*/

public class 이분탐색 {

    public static void main(String[] args) {
        이분탐색 search = new 이분탐색();

        System.out.println("=== 이분탐색 알고리즘 실행 ===\n");

        // 예시 실행
        search.runExample();
    }

    // 이분탐색의 핵심 구현 메서드 (반복문 버전)
    public int binarySearch(int[] arr, int target) {
        // 탐색 범위의 시작점과 끝점을 설정
        // left: 현재 탐색 범위의 가장 왼쪽 인덱스
        // right: 현재 탐색 범위의 가장 오른쪽 인덱스
        int left = 0;
        int right = arr.length - 1;

        // 단계별 진행 상황을 보여주기 위한 변수
        int step = 1;

        System.out.println("찾는 값: " + target);
        System.out.println("배열: " + Arrays.toString(arr));
        System.out.println("배열 크기: " + arr.length);
        System.out.println();

        // left <= right인 동안 계속 탐색
        // left > right가 되면 더 이상 탐색할 구간이 없다는 의미
        while (left <= right) {
            System.out.println("--- 단계 " + step + " ---");

            // 중간점 계산
            // 주의: (left + right) / 2로 하면 큰 수에서 오버플로우 발생 가능
            // 안전한 방법: left + (right - left) / 2
            int mid = left + (right - left) / 2;
            
            /* 
            ★ 왜 이렇게 중간점을 계산할까? (중요한 부분!)
            - 일반적인 방법: mid = (left + right) / 2
            - 안전한 방법: mid = left + (right - left) / 2
            
            두 방법 모두 수학적으로는 같은 결과를 내지만,
            left와 right가 매우 큰 수일 때 (left + right)가 
            정수의 최대값을 넘어서 오버플로우가 발생할 수 있습니다.
            
            예: left = 2,000,000,000, right = 2,000,000,000일 때
            - left + right = 4,000,000,000 (int 최대값 약 21억을 초과!)
            - left + (right - left) / 2 = 2,000,000,000 + 0 = 2,000,000,000 (안전)
            */

            System.out.println("현재 탐색 범위: [" + left + ", " + right + "]");
            System.out.println("배열 값으로 보면: [" + arr[left] + ", " + arr[right] + "]");
            System.out.println("중간점 인덱스: " + mid);
            System.out.println("중간점 값: " + arr[mid]);

            // 중간값과 찾는 값을 비교
            if (arr[mid] == target) {
                // 찾았다! 인덱스를 반환
                System.out.println("★ 찾았습니다! 인덱스 " + mid + "에서 값 " + target + " 발견");
                return mid;

            } else if (arr[mid] > target) {
                // 중간값이 찾는 값보다 크다
                // → 배열이 오름차순 정렬되어 있으므로, 중간값 오른쪽의 모든 값들도 target보다 크다
                // → 따라서 오른쪽 절반은 모두 버리고 왼쪽 절반에서만 계속 탐색
                System.out.println("중간값(" + arr[mid] + ")이 찾는 값(" + target + ")보다 큽니다");
                System.out.println("→ 오른쪽 절반을 버리고 왼쪽에서 계속 탐색");
                right = mid - 1;  // 탐색 범위를 왼쪽 절반으로 축소
                
                /* 
                ★ 왜 right = mid - 1일까? (헷갈리기 쉬운 부분!)
                
                mid 위치의 값은 이미 확인했고 target과 다르다는 것을 알고 있습니다.
                따라서 다음 탐색에서는 mid를 포함할 필요가 없습니다.
                
                - mid 위치: 이미 확인했으므로 제외
                - mid 오른쪽: 모두 target보다 크므로 제외
                - 결론: mid-1까지만 포함하여 탐색 범위 설정
                
                만약 right = mid로 하면 무한루프에 빠질 수 있습니다!
                */

            } else {
                // 중간값이 찾는 값보다 작다
                // → 배열이 오름차순 정렬되어 있으므로, 중간값 왼쪽의 모든 값들도 target보다 작다
                // → 따라서 왼쪽 절반은 모두 버리고 오른쪽 절반에서만 계속 탐색
                System.out.println("중간값(" + arr[mid] + ")이 찾는 값(" + target + ")보다 작습니다");
                System.out.println("→ 왼쪽 절반을 버리고 오른쪽에서 계속 탐색");
                left = mid + 1;   // 탐색 범위를 오른쪽 절반으로 축소
                
                /* 
                ★ 왜 left = mid + 1일까?
                위와 같은 이유로, mid는 이미 확인했고 target과 다르므로 제외하고,
                mid+1부터 오른쪽 끝까지만 탐색하면 됩니다.
                */
            }

            System.out.println("새로운 탐색 범위: [" + left + ", " + right + "]");

            // 탐색 범위가 유효한지 확인
            if (left <= right) {
                System.out.println("탐색 범위가 남아있습니다. 계속 진행...");
            } else {
                System.out.println("탐색 범위가 없습니다. 값을 찾지 못했습니다.");
            }

            System.out.println();
            step++;
        }

        // 여기까지 왔다는 것은 left > right가 되었다는 의미
        // 즉, 더 이상 탐색할 구간이 없으므로 값을 찾지 못함
        System.out.println("탐색 완료: 값 " + target + "을(를) 찾지 못했습니다.");
        return -1;  // 관례적으로 -1을 반환하여 "찾지 못함"을 표시
    }

    // 재귀 버전의 이분탐색 (참고용)
    // 동작 원리는 같지만 자기 자신을 호출하는 방식으로 구현
    public int binarySearchRecursive(int[] arr, int target, int left, int right, int step) {
        // 기저 조건: 탐색 범위가 없으면 찾지 못한 것
        if (left > right) {
            System.out.println("재귀 탐색 완료: 값을 찾지 못했습니다.");
            return -1;
        }

        System.out.println("--- 재귀 단계 " + step + " ---");

        // 중간점 계산 (반복문 버전과 동일)
        int mid = left + (right - left) / 2;

        System.out.println("현재 탐색 범위: [" + left + ", " + right + "]");
        System.out.println("중간점: " + mid + ", 값: " + arr[mid]);

        if (arr[mid] == target) {
            System.out.println("★ 재귀에서 찾았습니다! 인덱스 " + mid);
            return mid;
        } else if (arr[mid] > target) {
            System.out.println("왼쪽 절반에서 재귀 호출...");
            return binarySearchRecursive(arr, target, left, mid - 1, step + 1);
        } else {
            System.out.println("오른쪽 절반에서 재귀 호출...");
            return binarySearchRecursive(arr, target, mid + 1, right, step + 1);
        }
    }

    // 예시를 실행하는 메서드
    public void runExample() {
        // 테스트용 정렬된 배열 생성
        // 중요: 이분탐색은 반드시 정렬된 배열에서만 작동!
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};

        System.out.println("=== 예시 1: 값이 존재하는 경우 ===");
        int target1 = 15;
        int result1 = binarySearch(sortedArray, target1);
        System.out.println("최종 결과: " + (result1 != -1 ? "인덱스 " + result1 + "에서 발견" : "찾지 못함"));
        System.out.println();

        System.out.println("=== 예시 2: 값이 존재하지 않는 경우 ===");
        int target2 = 16;
        int result2 = binarySearch(sortedArray, target2);
        System.out.println("최종 결과: " + (result2 != -1 ? "인덱스 " + result2 + "에서 발견" : "찾지 못함"));
        System.out.println();

        System.out.println("=== 예시 3: 첫 번째 원소를 찾는 경우 ===");
        int target3 = 1;
        int result3 = binarySearch(sortedArray, target3);
        System.out.println("최종 결과: " + (result3 != -1 ? "인덱스 " + result3 + "에서 발견" : "찾지 못함"));
        System.out.println();

        System.out.println("=== 예시 4: 마지막 원소를 찾는 경우 ===");
        int target4 = 29;
        int result4 = binarySearch(sortedArray, target4);
        System.out.println("최종 결과: " + (result4 != -1 ? "인덱스 " + result4 + "에서 발견" : "찾지 못함"));
        System.out.println();

        System.out.println("=== 재귀 버전 예시 ===");
        int target5 = 13;
        System.out.println("찾는 값: " + target5);
        int result5 = binarySearchRecursive(sortedArray, target5, 0, sortedArray.length - 1, 1);
        System.out.println("재귀 버전 최종 결과: " + (result5 != -1 ? "인덱스 " + result5 + "에서 발견" : "찾지 못함"));
        System.out.println();

        // 성능 비교 예시
        demonstrateEfficiency();
    }

    // 이분탐색의 효율성을 보여주는 메서드
    public void demonstrateEfficiency() {
        System.out.println("=== 이분탐색의 효율성 시연 ===");

        // 큰 배열 생성 (1부터 100,000까지의 홀수)
        int[] bigArray = new int[50000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = 2 * i + 1;  // 1, 3, 5, 7, 9, ...
        }

        System.out.println("배열 크기: " + bigArray.length + "개");
        System.out.println("배열 범위: " + bigArray[0] + " ~ " + bigArray[bigArray.length - 1]);
        System.out.println();

        // 이분탐색으로 값 찾기 (단계 수 세기)
        int targetBig = 77777;  // 존재하는 값
        System.out.println("이분탐색으로 " + targetBig + " 찾기:");

        // 간단 버전 (단계 수만 출력)
        int steps = binarySearchWithStepCount(bigArray, targetBig);
        System.out.println("총 " + steps + "단계 만에 찾음!");
        System.out.println();

        // 선형탐색과 비교
        System.out.println("만약 선형탐색(처음부터 하나씩 비교)을 했다면:");
        int linearSteps = linearSearchStepCount(bigArray, targetBig);
        System.out.println("총 " + linearSteps + "단계 필요했을 것");
        System.out.println();

        System.out.println("효율성 비교:");
        System.out.println("- 이분탐색: " + steps + "단계");
        System.out.println("- 선형탐색: " + linearSteps + "단계");
        System.out.println("- 이분탐색이 약 " + (linearSteps / steps) + "배 빠름!");
    }

    // 단계 수만 세는 간단한 이분탐색
    private int binarySearchWithStepCount(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int steps = 0;

        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return steps;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return steps;
    }

    // 선형탐색의 단계 수를 계산하는 메서드 (비교용)
    private int linearSearchStepCount(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i + 1;  // 1단계부터 시작하므로 +1
            }
        }
        return arr.length;  // 찾지 못한 경우 모든 원소를 확인
    }
}