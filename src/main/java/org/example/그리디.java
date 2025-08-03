package org.example;

import java.util.*;

/*
 * 그리디 알고리즘 (Greedy Algorithm) 소개:
 * 
 * 그리디 알고리즘은 매 순간 최적의 선택을 하여 전체적으로 최적해를 구하는 알고리즘입니다.
 * "탐욕스럽게" 현재 상황에서 가장 좋아보이는 것을 선택하는 방식으로 작동합니다.
 * 
 * 시간복잡도: O(n log n) - 정렬이 필요한 경우가 대부분
 * 공간복잡도: O(1) - 추가 공간을 거의 사용하지 않음
 * 
 * 먼저 알아야 할 기본 지식:
 * 1. 정렬 알고리즘 (Arrays.sort(), Collections.sort())
 * 2. Comparator 인터페이스 사용법
 * 3. 그리디 선택 속성(Greedy Choice Property) 이해
 * 4. 최적 부분 구조(Optimal Substructure) 개념
 * 
 * 그리디 알고리즘이 사용되는 문제 유형:
 * 1. 활동 선택 문제 (Activity Selection Problem)
 * 2. 분할 가능한 배낭 문제 (Fractional Knapsack Problem)
 * 3. 최소 신장 트리 (MST - Kruskal, Prim)
 * 4. 최단 경로 (Dijkstra Algorithm)
 * 5. 허프만 코딩 (Huffman Coding)
 * 6. 거스름돈 문제 (Coin Change - 특정 조건)
 * 7. 회의실 배정, 강의실 배정 문제
 * 8. 구간 스케줄링 문제
 * 
 * 비슷한 알고리즘들과 주의사항:
 * 
 * 1. 동적 계획법(DP)과의 차이점:
 *    - 그리디: 현재 최적 선택만 고려, 되돌아가지 않음
 *    - DP: 모든 경우를 고려하여 최적해 보장
 *    - 착각하기 쉬운 점: 0-1 배낭문제는 그리디로 해결 불가능!
 * 
 * 2. 완전 탐색(Brute Force)과의 차이점:
 *    - 그리디: 일부 경우만 탐색하여 빠른 해결
 *    - 완전탐색: 모든 경우 탐색하므로 정확하지만 느림
 * 
 * 3. 분기한정법(Branch and Bound)과의 차이점:
 *    - 그리디: 한 번 선택하면 되돌아가지 않음
 *    - 분기한정: 상한/하한을 이용해 가지치기 수행
 * 
 * 잘못 접근하기 쉬운 문제들:
 * - 0-1 배낭 문제: 그리디로 접근하면 틀림 (DP 사용해야 함)
 * - 외판원 문제(TSP): 그리디는 근사해만 제공
 * - 부분집합의 합 문제: 그리디로는 해결 불가능
 */

public class 그리디 {
    
    // 활동을 나타내는 클래스
    static class Activity {
        int start;  // 시작 시간
        int end;    // 종료 시간
        int index;  // 원래 인덱스 (문제에서 활동 번호가 필요한 경우)
        
        Activity(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }
    
    public static void main(String[] args) {
        // 실제 코딩테스트에서는 입력을 받지만, 여기서는 직접 입력
        
        // 예시 데이터: 회의실 예약 문제
        // 각 회의의 (시작시간, 종료시간)
        int[][] meetings = {
            {1, 4},   // 회의 0: 1시~4시
            {3, 5},   // 회의 1: 3시~5시  
            {0, 6},   // 회의 2: 0시~6시
            {5, 7},   // 회의 3: 5시~7시
            {3, 9},   // 회의 4: 3시~9시
            {5, 9},   // 회의 5: 5시~9시
            {6, 10},  // 회의 6: 6시~10시
            {8, 11},  // 회의 7: 8시~11시
            {8, 12},  // 회의 8: 8시~12시
            {2, 14},  // 회의 9: 2시~14시
            {12, 16}  // 회의 10: 12시~16시
        };
        
        // 그리디 알고리즘으로 최대 회의 개수 구하기
        int maxActivities = activitySelection(meetings);
        System.out.println("선택 가능한 최대 회의 개수: " + maxActivities);
    }
    
    public static int activitySelection(int[][] meetings) {
        int n = meetings.length;
        
        // 1단계: Activity 객체 배열 생성
        // 정렬과 인덱스 추적을 위해 객체로 변환
        Activity[] activities = new Activity[n];
        for (int i = 0; i < n; i++) {
            activities[i] = new Activity(meetings[i][0], meetings[i][1], i);
        }
        
        // 2단계: 종료 시간 기준으로 정렬 (핵심!)
        // 왜 종료시간으로 정렬하는가?
        // - 일찍 끝나는 활동을 선택할수록 나중에 더 많은 활동을 선택할 기회가 생김
        // - 이것이 그리디 선택의 핵심 아이디어
        Arrays.sort(activities, new Comparator<Activity>() {
            @Override
            public int compare(Activity a1, Activity a2) {
                // 종료시간이 같다면 시작시간이 늦은 것을 우선 (더 짧은 활동 우선)
                if (a1.end == a2.end) {
                    return a2.start - a1.start;
                }
                return a1.end - a2.end;
            }
        });
        
        // 3단계: 그리디 선택 수행
        int count = 0;  // 선택된 활동의 개수
        int lastEndTime = -1;  // 마지막으로 선택된 활동의 종료 시간
        
        // 정렬된 순서대로 활동들을 검사
        for (int i = 0; i < n; i++) {
            Activity current = activities[i];
            
            // 핵심 그리디 선택 조건:
            // 현재 활동의 시작시간이 이전에 선택한 활동의 종료시간 이후라면 선택
            if (current.start >= lastEndTime) {
                count++;  // 활동 선택
                lastEndTime = current.end;  // 종료시간 업데이트
                
                // 실제 코딩테스트에서는 보통 이 출력 부분은 생략
                // 하지만 디버깅용으로 어떤 활동이 선택되는지 확인
                System.out.println("선택된 회의 " + current.index + 
                                 ": " + current.start + "시 ~ " + current.end + "시");
            }
            
            /* 
             * 여기서 왜 이 방법이 최적해를 보장하는가?
             * 
             * 수학적 증명 (교환 논리):
             * 1. 종료시간이 가장 빠른 활동을 선택하지 않은 최적해가 있다고 가정
             * 2. 그 최적해에서 첫 번째 선택을 가장 빠른 종료시간 활동으로 교체
             * 3. 교체 후에도 여전히 유효한 해가 되고, 활동 개수는 줄어들지 않음
             * 4. 따라서 가장 빠른 종료시간 활동을 선택하는 것이 항상 최적
             * 
             * 직관적 이해:
             * - 일찍 끝나는 회의를 선택할수록 뒤의 회의들과 겹칠 확률이 줄어듦
             * - 더 많은 선택의 여지를 남겨둠
             */
        }
        
        return count;
    }
    
    /*
     * 추가 설명: 그리디 알고리즘의 한계
     * 
     * 1. 항상 최적해를 보장하지는 않음
     *    - 문제가 그리디 선택 속성과 최적 부분 구조를 만족해야 함
     *    - 반례: 0-1 배낭 문제에서 가치/무게 비율로 그리디 선택하면 틀림
     * 
     * 2. 증명이 필요함
     *    - 그리디 선택이 최적해의 일부가 될 수 있음을 증명해야 함
     *    - 수학적 귀납법이나 교환 논리를 사용
     * 
     * 3. 코딩테스트에서 그리디 문제 판별법:
     *    - "최대", "최소", "최적"이라는 키워드
     *    - 정렬 후 순차적 선택으로 해결 가능해 보이는 경우
     *    - 매 단계에서 명확한 선택 기준이 있는 경우
     */
}