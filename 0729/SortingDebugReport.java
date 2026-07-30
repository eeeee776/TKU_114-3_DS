import java.util.Arrays;

public class SortingDebugReport {
    public static void main(String[] args) {
        System.out.println("【錯誤一：Selection Sort 內層範圍錯誤】");
        // 錯誤原因：內層迴圈從 0 開始，導致每輪又把已排好或前面的元素拖過來比較
        int[] bug1 = {5, 3, 4, 1, 2};
        selectionSortBug1(bug1);
        System.out.println("錯誤輸出：" + Arrays.toString(bug1));
        
        System.out.println("\n【錯誤二：Insertion Sort key 未保存】");
        // 錯誤原因：沒有將 values[index] 存進 key 中，導致右移時原本的值被覆蓋消失
        int[] bug2 = {5, 3, 4, 1, 2};
        insertionSortBug2(bug2);
        System.out.println("錯誤輸出：" + Arrays.toString(bug2));

        System.out.println("\n【錯誤三：Selection Sort 比較方向錯誤】");
        // 錯誤原因：想要排升冪，但條件寫成 values[index] > values[minIndex] 變成降冪
        int[] bug3 = {5, 3, 4, 1, 2};
        selectionSortBug3(bug3);
        System.out.println("錯誤輸出：" + Arrays.toString(bug3));
    }

    // --- 錯誤示範區 ---

    public static void selectionSortBug1(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            // BUG: index = 0，會重複去掃描已經排序好的區塊
            for (int index = 0; index < values.length; index++) { 
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }
            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    public static void insertionSortBug2(int[] values) {
        for (int index = 1; index < values.length; index++) {
            // BUG: 忘記 int key = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] > values[index]) {
                values[position + 1] = values[position];
                position--;
            }
            // 這裡放不回原本的 key，導致資料遺失或重複
        }
    }

    public static void selectionSortBug3(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                // BUG: 升冪排序應該是尋找更小的值 (<)，這裡寫成了 >
                if (values[index] > values[minIndex]) {
                    minIndex = index;
                }
            }
            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    // --- 修正後正確示範區 (以 Insertion 為例) ---
    public static void insertionSortFixed(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index]; // 修正：確實將 key 暫存
            int position = index - 1;
            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key; // 修正：放回 key
        }
    }
}