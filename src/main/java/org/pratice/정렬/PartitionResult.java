package org.pratice.정렬;

// 퀵정렬의 분할 결과를 담는 내부 클래스
    public class PartitionResult {
        long swaps;
        long searches;
        int pivotIndex; // 다음 분할을 위한 기준점 인덱스

        public PartitionResult(long swaps, long searches, int pivotIndex) {
            this.swaps = swaps;
            this.searches = searches;
            this.pivotIndex = pivotIndex;
        }
    }
