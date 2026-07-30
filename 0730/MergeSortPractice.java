import java.util.Arrays;

public class MergeSortPractice {
    public static void main(String[] args) {
        test(new int[]{41, 12, 35, 8, 27, 19, 50, 3});
        test(new int[]{});
        test(new int[]{5});
        test(new int[]{1, 2, 3, 4});
        test(new int[]{4, 3, 2, 1});
    }

    public static void test(int[] arr) {
        System.out.println("=== 測試陣列: " + Arrays.toString(arr) + " ===");
        if (arr.length == 0) {
            System.out.println("完成: []\n");
            return;
        }
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1, 0);
        System.out.println("完成: " + Arrays.toString(arr) + "\n");
    }

    public static void mergeSort(int[] arr, int[] temp, int left, int right, int depth) {
        String indent = "  ".repeat(depth);
        if (left >= right) {
            int[] range = Arrays.copyOfRange(arr, left, right + 1);
            System.out.println(indent + "拆分: " + Arrays.toString(range));
            return;
        }

        int mid = left + (right - left) / 2;
        int[] currentRange = Arrays.copyOfRange(arr, left, right + 1);
        System.out.println(indent + "拆分: " + Arrays.toString(currentRange));
        
        mergeSort(arr, temp, left, mid, depth + 1);
        mergeSort(arr, temp, mid + 1, right, depth + 1);
        merge(arr, temp, left, mid, right);
        
        int[] mergedRange = Arrays.copyOfRange(arr, left, right + 1);
        System.out.println(indent + "合併 " + left + ".." + right + ": " + Arrays.toString(mergedRange));
    }

    public static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) {
            arr[idx] = temp[idx];
        }
    }
}