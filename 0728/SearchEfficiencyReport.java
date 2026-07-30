public class SearchEfficiencyReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        
        for (int size : sizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) data[i] = i * 2; // 建立已排序資料

            System.out.println("=== 資料筆數：" + size + " ===");
            runTest(data, data[0], "第一筆資料 (" + data[0] + ")");
            runTest(data, data[size - 1], "最後一筆資料 (" + data[size - 1] + ")");
            runTest(data, -1, "不存在資料 (-1)");
            System.out.println();
        }
        
        System.out.println("【觀察結果】");
        System.out.println("1. Sequential Search 的比較次數與目標位置呈線性正相關，找不到時需檢查所有資料 (O(n))。");
        System.out.println("2. Binary Search 即使在 1024 筆資料中，最差情況也只需約 10-11 次比較，展現了 O(log n) 的極高效率。");
    }

    public static void runTest(int[] data, int target, String description) {
        System.out.println("【測試目標】" + description);
        System.out.println("  - Sequential Search 比較次數: " + sequentialSearchChecks(data, target));
        System.out.println("  - Binary Search 比較次數: " + binarySearchChecks(data, target));
    }

    public static int sequentialSearchChecks(int[] data, int target) {
        int checks = 0;
        for (int value : data) {
            checks++;
            if (value == target) return checks;
        }
        return checks;
    }

    public static int binarySearchChecks(int[] data, int target) {
        int checks = 0, low = 0, high = data.length - 1;
        while (low <= high) {
            checks++;
            int mid = low + (high - low) / 2;
            if (data[mid] == target) return checks;
            if (target < data[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return checks;
    }
}