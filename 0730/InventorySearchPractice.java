import java.util.Arrays;

public class InventorySearchPractice {
    public static void main(String[] args) {
        int[] inventory = {905, 112, 345, 876, 234, 555, 101, 404, 789, 202, 600, 310};
        
        System.out.println("排序前: " + Arrays.toString(inventory));
        int[] temp = new int[inventory.length];
        mergeSort(inventory, temp, 0, inventory.length - 1);
        System.out.println("排序後: " + Arrays.toString(inventory));

        search(inventory, 101); // 測試第一筆
        search(inventory, 905); // 測試最後一筆
        search(inventory, 999); // 測試不存在
    }

    public static void search(int[] arr, int target) {
        int index = binarySearch(arr, target);
        System.out.println("搜尋 " + target + " -> " + (index == -1 ? "找不到" : "索引: " + index));
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
    }
}