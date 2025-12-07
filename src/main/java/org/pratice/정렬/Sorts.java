package org.pratice.정렬;

public class Sorts {
    public static void main(String[] args) {
        int[] arr = new int[50000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 50000 - i;
        }
        int[] arr1 = arr.clone();
        int[] arr2 = arr.clone();
        int[] arr3 = arr.clone();
        int[] arr4 = arr.clone();
        int[] arr5 = arr.clone();
        extracted(arr1, "버블정렬", Sorts::bubbleSort);
        extracted(arr2, "선택정렬", Sorts::selectionSort);
        extracted(arr3, "삽입정렬", Sorts::insertionSort);
        extracted(arr4, "병합정렬", Sorts::mergeSort);
        extracted(arr5, "퀵정렬", Sorts::quickSort);
    }

    private static void extracted(int[] arr, String input, SortFunction sortFunction) {
        long start = System.nanoTime();
        SortResult result = sortFunction.sort(arr);
        long end = System.nanoTime();

        String swapLabel = "스왑횟수";

        if (input.equals("삽입정렬")) {
            swapLabel = "밀기횟수";
        } else if (input.equals("병합정렬")) {
            swapLabel = "이동횟수";
        }

        System.out.println(
                input + " : " + (end - start) / 1_000_000 + "ms, " +
                        swapLabel + " : " + toKoreanNumber(result.swap) + ", " +
                        "탐색횟수 : " + toKoreanNumber(result.search)
        );

    }

    private static SortResult bubbleSort(int[] arr) {
        long swap = 0;
        long search = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                search++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    swap++;
                }
            }
        }
        return new SortResult(swap, search);
    }

    private static SortResult selectionSort(int[] arr) {
        long swap = 0;
        long search = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                search++;
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                    swap++;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[minIndex] = temp;
            swap++;
        }
        return new SortResult(swap, search);
    }

    private static SortResult insertionSort(int[] arr) {
        long swift = 0;
        long search = 0;
        for (int i = 1; i < arr.length; i++) {
            search++;
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                search++;
                swift++;
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
        return new SortResult(swift, search);
    }

    private static SortResult mergeSort(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static SortResult mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            SortResult leftResult = mergeSort(arr, left, mid);
            SortResult rightResult = mergeSort(arr, mid + 1, right);
            SortResult mergeResult = merge(arr, left, mid, right);
            return new SortResult(
                    leftResult.swap + rightResult.swap + mergeResult.swap, // 전체 이동 횟수
                    leftResult.search + rightResult.search + mergeResult.search // 전체 비교 횟수
            );
        }
        return new SortResult(0,0);
    }

    private static SortResult merge(int[] arr, int left, int mid, int right) {
        long comparisons = 0;
        long moves = 0;
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            comparisons++;
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
            moves++;
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
            moves++;
        }
        while (j <= right) {
            temp[k++] = arr[j++];
            moves++;
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
        moves += temp.length;

        return new SortResult(moves, comparisons);
    }



    private static void heapSort() {

    }

    private static SortResult quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    private static SortResult quickSort(int[] arr, int left, int right) {

        PartitionResult pr = partition(arr, left, right);
        int pivotIndex = pr.pivotIndex;
        long totalSwaps = pr.swaps;
        long totalSearches = pr.searches;

        if (left < pivotIndex - 1) {
            SortResult leftResult = quickSort(arr, left, pivotIndex - 1);
            totalSwaps += leftResult.swap;
            totalSearches += leftResult.search;
        }

        if (pivotIndex < right) {
            SortResult rightResult = quickSort(arr, pivotIndex + 1, right);
            totalSwaps += rightResult.swap;
            totalSearches += rightResult.search;
        }

        return new SortResult(totalSwaps, totalSearches);
    }

    private static PartitionResult partition(int[] arr, int left, int right) {
        long swap = 0;
        long search = 0;
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
                search++;
            }
            while (arr[right] > pivot) {
                right--;
                search++;
            }

            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
                swap++;
            }
        }
        return new PartitionResult(swap, search, left);
    }

    private static void treeSort() {

    }

    private static String toKoreanNumber(long n) {
        long 억 = n / 100_000_000;
        n %= 100_000_000;

        long 만 = n / 10_000;
        long 일 = n % 10_000;

        StringBuilder sb = new StringBuilder();

        if (억 > 0) sb.append(억).append("억");
        if (만 > 0) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(만).append("만");
        }
        if (일 > 0) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(일);
        }

        sb.append("번");

        return sb.toString();
    }
}
