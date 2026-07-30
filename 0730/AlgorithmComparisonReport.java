import java.util.Arrays;
import java.util.Random;

public class AlgorithmComparisonReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        System.out.printf("%-10s %-15s %-15s %-15s %-15s\n", "Size", "Type", "Selection", "Insertion", "Merge");

        for (int size : sizes) {
            int[] sorted = generateSorted(size);
            int[] reverse = generateReverse(size);
            int[] random = generateRandom(size);

            runTest(size, "Sorted", sorted);
            runTest(size, "Reverse", reverse);
            runTest(size, "Random", random);
            System.out.println("-".repeat(70));
        }
    }

    private static void runTest(int size, String type, int[] arr) {
        long sel = selectionSortCounts(arr.clone());
        long ins = insertionSortCounts(arr.clone());
        long mrg = mergeSortCounts(arr.clone());
        System.out.printf("%-10d %-15s %-15d %-15d %-15d\n", size, type, sel, ins, mrg);
    }

    // 實作回傳比較次數
    public static long selectionSortCounts(int[] arr) {
        long count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                count++;
                if (arr[j] < arr[min]) min = j;
            }
            int temp = arr[i]; arr[i] = arr[min]; arr[min] = temp;
        }
        return count;
    }

    public static long insertionSortCounts(int[] arr) {
        long count = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                count++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
        return count;
    }

    public static long mergeSortCounts(int[] arr) {
        int[] temp = new int[arr.length];
        return mergeSortCount(arr, temp, 0, arr.length - 1);
    }

    private static long mergeSortCount(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        long count = mergeSortCount(arr, temp, left, mid);
        count += mergeSortCount(arr, temp, mid + 1, right);

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            count++;
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
        return count;
    }

    // 資料生成器
    private static int[] generateSorted(int size) {
        int[] arr = new int[size]; for(int i=0; i<size; i++) arr[i] = i; return arr;
    }
    private static int[] generateReverse(int size) {
        int[] arr = new int[size]; for(int i=0; i<size; i++) arr[i] = size - i; return arr;
    }
    private static int[] generateRandom(int size) {
        int[] arr = new int[size]; Random r = new Random(42); // 固定 seed
        for(int i=0; i<size; i++) arr[i] = r.nextInt(10000); return arr;
    }
}