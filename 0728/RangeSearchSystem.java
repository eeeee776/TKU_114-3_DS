import java.util.Arrays;

public class RangeSearchSystem {
    public static void main(String[] args) {
        int[] values = {10, 20, 20, 20, 20, 30, 40, 50, 50, 60};
        
        findAndPrintRange(values, 20); // 測試多筆重複
        findAndPrintRange(values, 50); // 測試兩筆重複
        findAndPrintRange(values, 10); // 測試單筆
        findAndPrintRange(values, 99); // 測試不存在
    }

    public static void findAndPrintRange(int[] values, int target) {
        int firstIndex = findBound(values, target, true);
        int lastIndex = findBound(values, target, false);
        
        int[] result = {firstIndex, lastIndex};
        System.out.println("尋找目標：" + target);
        System.out.println("索引範圍：" + Arrays.toString(result));
        
        if (firstIndex == -1) {
            System.out.println("出現次數：0\n");
        } else {
            int count = lastIndex - firstIndex + 1;
            System.out.println("出現次數：" + count + "\n");
        }
    }

    // isFirst 為 true 找第一筆，false 找最後一筆
    public static int findBound(int[] values, int target, boolean isFirst) {
        int low = 0;
        int high = values.length - 1;
        int resultIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (values[mid] == target) {
                resultIndex = mid; // 先記錄下來
                if (isFirst) {
                    high = mid - 1; // 繼續往左找第一筆
                } else {
                    low = mid + 1;  // 繼續往右找最後一筆
                }
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return resultIndex;
    }
}