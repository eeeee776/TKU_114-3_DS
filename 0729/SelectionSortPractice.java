import java.util.Arrays;

public class SelectionSortPractice {
    public static void main(String[] args) {
        testSelectionSort(new int[]{42, 18, 35, 7, 29, 14}, "一般測試");
        testSelectionSort(new int[]{}, "空陣列測試");
        testSelectionSort(new int[]{10}, "單一元素陣列測試");
    }

    public static void testSelectionSort(int[] values, String testName) {
        System.out.println("=== " + testName + " ===");
        System.out.println("初始陣列：" + Arrays.toString(values));
        int comparisons = 0;
        int swaps = 0;

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            
            // 實際發生交換才計數
            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swaps++;
            }
            
            System.out.printf("第 %d 輪：start 索引 %d，選中最小值的索引 %d，目前陣列 %s%n", 
                              start + 1, start, minIndex, Arrays.toString(values));
        }
        System.out.printf("統計：總共比較 %d 次，交換 %d 次%n%n", comparisons, swaps);
    }
}