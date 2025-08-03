package org.example;

import java.util.*;

/*
 * ===== 맨해튼 거리(Manhattan Distance) 알고리즘 =====
 * 
 * 1. 알고리즘 소개:
 *    - 맨해튼 거리는 두 점 (x1, y1)과 (x2, y2) 사이의 거리를 |x1-x2| + |y1-y2|로 계산하는 방법
 *    - 격자 형태의 도시에서 택시가 이동할 수 있는 최단거리와 같다고 해서 Taxicab distance라고도 함
 *    - 대각선 이동이 불가능한 환경에서의 실제 이동거리를 나타냄
 * 
 * 2. 시간복잡도:
 *    - 두 점 간 거리 계산: O(1)
 *    - N개 점들 중 특정 점과 가장 가까운 점 찾기: O(N)
 *    - 모든 점들 간의 거리 계산: O(N²)
 * 
 * 3. 먼저 알아야 할 기본 개념:
 *    - 절댓값 계산: Math.abs() 사용
 *    - 2차원 좌표계 이해
 *    - 배열이나 리스트를 통한 좌표 저장 방법
 * 
 * 4. 코딩테스트에서 활용되는 문제 유형:
 *    - 격자에서의 최단거리 문제 (BFS와 함께 사용)
 *    - 택시 요금 계산 문제
 *    - 창고에서 물건까지의 거리 계산
 *    - 로봇 청소기의 이동거리 계산
 *    - 배달 경로 최적화 문제
 *    - 좌표평면에서 가장 가까운/먼 점 찾기
 *    - 클러스터링 문제 (K-means 등)
 * 
 * 5. 비슷한 알고리즘들과 주의사항:
 *    - 유클리드 거리: √((x1-x2)² + (y1-y2)²) - 직선거리, 대각선 이동 가능할 때 사용
 *    - 체비셰프 거리: max(|x1-x2|, |y1-y2|) - 킹이 체스판에서 이동하는 거리
 *    - 주의: 문제에서 "최단거리"라고 하면 유클리드 거리로 착각하기 쉬움
 *    - 격자나 도시 블록 형태에서는 맨해튼 거리가 실제 이동거리
 *    - BFS 문제에서 가중치가 모두 1일 때는 맨해튼 거리가 실제 최단경로와 일치
 */

public class 맨해튼거리 {
    
    public static void main(String[] args) {
        // 실전 코딩테스트 시뮬레이션: 여러 창고 중 가장 가까운 창고 찾기
        
        // 창고들의 좌표 (실제 코딩테스트에서는 입력으로 받음)
        int[][] warehouses = {
            {1, 1},   // 창고 0
            {5, 3},   // 창고 1  
            {2, 7},   // 창고 2
            {8, 1},   // 창고 3
            {3, 4}    // 창고 4
        };
        
        // 배달 목적지 좌표
        int[] destination = {4, 2};
        
        // 가장 가까운 창고 찾기
        int closestWarehouse = findClosestWarehouse(warehouses, destination);
        int minDistance = calculateManhattanDistance(warehouses[closestWarehouse], destination);
        
        System.out.println("가장 가까운 창고: " + closestWarehouse + "번");
        System.out.println("최단 배달거리: " + minDistance);
        
        // 추가 예시: 모든 창고까지의 거리 계산
        System.out.println("\n모든 창고까지의 거리:");
        for (int i = 0; i < warehouses.length; i++) {
            int distance = calculateManhattanDistance(warehouses[i], destination);
            System.out.println("창고 " + i + ": " + distance);
        }
    }
    
    /**
     * 두 점 사이의 맨해튼 거리를 계산하는 핵심 함수
     * 이 함수가 맨해튼 거리 알고리즘의 핵심이며, 코딩테스트에서 가장 자주 사용됨
     */
    public static int calculateManhattanDistance(int[] point1, int[] point2) {
        // 맨해튼 거리 공식: |x1-x2| + |y1-y2|
        // Math.abs()를 사용해 절댓값 계산 - 음수가 나와도 거리는 항상 양수여야 하기 때문
        int deltaX = Math.abs(point1[0] - point2[0]);  // x좌표 차이의 절댓값
        int deltaY = Math.abs(point1[1] - point2[1]);  // y좌표 차이의 절댓값
        
        return deltaX + deltaY;  // 두 차이를 더하면 맨해튼 거리
    }
    
    /**
     * 여러 점들 중에서 목표점과 가장 가까운 점을 찾는 함수
     * 코딩테스트에서 "가장 가까운/먼 점 찾기" 유형에서 자주 사용되는 패턴
     */
    public static int findClosestWarehouse(int[][] warehouses, int[] destination) {
        int minDistance = Integer.MAX_VALUE;  // 최소거리를 저장할 변수 (초기값은 매우 큰 수)
        int closestIndex = -1;                // 가장 가까운 창고의 인덱스
        
        // 모든 창고에 대해 거리를 계산하여 최소값을 찾는 전형적인 패턴
        for (int i = 0; i < warehouses.length; i++) {
            // 현재 창고와 목적지 사이의 맨해튼 거리 계산
            int distance = calculateManhattanDistance(warehouses[i], destination);
            
            // 현재까지의 최소거리보다 작으면 업데이트
            // 등호(<=)를 사용하면 같은 거리일 때 뒤쪽 인덱스를 선택
            // 부등호(<)를 사용하면 같은 거리일 때 앞쪽 인덱스를 선택
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }
        
        return closestIndex;
    }
    
    /**
     * 실전 응용: 특정 반경 내의 모든 점들을 찾는 함수
     * 배달 가능 범위, 영향권 계산 등에 활용되는 패턴
     */
    public static List<Integer> findWarehousesWithinRange(int[][] warehouses, int[] center, int maxDistance) {
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < warehouses.length; i++) {
            int distance = calculateManhattanDistance(warehouses[i], center);
            
            // 맨해튼 거리가 지정된 범위 이내인 창고들만 선택
            if (distance <= maxDistance) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    /**
     * 고급 응용: 맨해튼 거리를 이용한 클러스터 중심 계산
     * 여러 점들의 맨해튼 거리 합이 최소가 되는 중심점을 찾는 함수
     * 이 방법은 중앙값(median)을 이용하는 것이 핵심 아이디어
     */
    public static int[] findOptimalCenter(int[][] points) {
        int n = points.length;
        
        // x좌표들과 y좌표들을 각각 분리해서 정렬
        // 맨해튼 거리의 특성상 x와 y를 독립적으로 처리할 수 있음
        int[] xCoords = new int[n];
        int[] yCoords = new int[n];
        
        for (int i = 0; i < n; i++) {
            xCoords[i] = points[i][0];
            yCoords[i] = points[i][1];
        }
        
        // 정렬 후 중앙값을 구함
        // 중앙값이 맨해튼 거리의 합을 최소화하는 핵심 원리!
        Arrays.sort(xCoords);
        Arrays.sort(yCoords);
        
        // 중앙값 계산 (홀수개면 가운데, 짝수개면 가운데 두 개 중 아무거나)
        int medianX = xCoords[n / 2];
        int medianY = yCoords[n / 2];
        
        return new int[]{medianX, medianY};
    }
    
    /**
     * 디버깅용 함수: 좌표들을 시각적으로 표현
     * 실제 코딩테스트에서는 사용하지 않지만, 알고리즘 이해에 도움
     */
    public static void visualizePoints(int[][] points, int[] target) {
        System.out.println("점들의 분포:");
        for (int i = 0; i < points.length; i++) {
            int distance = calculateManhattanDistance(points[i], target);
            System.out.printf("점 %d: (%d, %d) - 거리: %d\n", 
                i, points[i][0], points[i][1], distance);
        }
        System.out.printf("목표점: (%d, %d)\n", target[0], target[1]);
    }
}