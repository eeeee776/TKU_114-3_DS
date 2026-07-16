public class Q07_ArrayAudit {
    private static final int MIN_VALID = 10;
    private static final int MAX_VALID = 60;
    private static final int TARGET = 35;
    private static final int INVALID_MARK = -1;

    public static void main(String[] args) {
        int[] readings = {12, 71, 35, -4, 35, 22, 60, 9, 48, 61};

        System.out.println("有效筆數：" + countValid(readings));
        System.out.printf("有效平均：%.2f%n", averageValid(readings));
        System.out.println("最後符合門檻的索引：" + findLastAtLeast(readings, TARGET));

        int[] cleaned = createCleanCopy(readings);
        System.out.print("清理後資料：");
        printArray(cleaned);
        System.out.print("原始資料：");
        printArray(readings);
    }

    private static boolean isValid(int value) {
        return value >= MIN_VALID && value <= MAX_VALID;
    }

    // 1. 計算有效筆數
    public static int countValid(int[] data) {
        int count = 0;
        for (int value : data) {
            if (isValid(value)) {
                count++;
            }
        }
        return count;
    }

    // 2. 計算有效平均
    public static double averageValid(int[] data) {
        int count = 0;
        double sum = 0.0;
        
        for (int value : data) {
            if (isValid(value)) {
                sum += value;
                count++;
            }
        }
        
        // 依照規定，如果沒有任何有效資料，回傳 -1.0
        if (count == 0) {
            return -1.0;
        }
        
        return sum / count;
    }

    // 3. 尋找最後符合門檻的索引
    public static int findLastAtLeast(int[] data, int target) {
        // 從陣列尾端往前找，可以最快找到「最後一個」符合條件的索引
        for (int i = data.length - 1; i >= 0; i--) {
            if (isValid(data[i]) && data[i] >= target) {
                return i;
            }
        }
        return -1; // 找不到時回傳 -1
    }

    // 4. 建立清理後的新陣列
    public static int[] createCleanCopy(int[] data) {
        // 建立與原陣列相同長度的新陣列
        int[] cleaned = new int[data.length];
        
        for (int i = 0; i < data.length; i++) {
            if (isValid(data[i])) {
                cleaned[i] = data[i]; // 有效則保留原值
            } else {
                cleaned[i] = INVALID_MARK; // 無效則改為 -1
            }
        }
        
        return cleaned;
    }

    private static void printArray(int[] data) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}