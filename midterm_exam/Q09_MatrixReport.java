public class Q09_MatrixReport {
    public static void main(String[] args) {
        int[][] data = {
            {5, 8, 2},
            {9, 4, 7},
            {3, 6, 10}
        };

        System.out.println("第 1 列總和：" + rowSum(data, 1));
        System.out.println("第 2 欄總和：" + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數：" + countAtLeast(data, 7));
        System.out.println("總和最大的列索引：" + findRowWithLargestTotal(data));
    }

    // 1. 計算指定列的總和
    public static int rowSum(int[][] data, int row) {
        if (data == null || data.length == 0) return -1;
        // 檢查列索引是否合法
        if (row < 0 || row >= data.length) return -1;
        
        int sum = 0;
        for (int value : data[row]) {
            sum += value;
        }
        return sum;
    }

    // 2. 計算指定欄的總和
    public static int columnSum(int[][] data, int column) {
        if (data == null || data.length == 0) return -1;
        if (column < 0) return -1;

        int sum = 0;
        boolean columnExists = false;

        for (int i = 0; i < data.length; i++) {
            // 確保當前列的長度足夠包含目標欄位
            if (data[i] != null && column < data[i].length) {
                sum += data[i][column];
                columnExists = true;
            }
        }
        
        return columnExists ? sum : -1;
    }

    // 3. 計算大於或等於門檻的資料筆數
    public static int countAtLeast(int[][] data, int target) {
        if (data == null || data.length == 0) return -1;

        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j] >= target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // 4. 回傳總和最大的列索引
    public static int findRowWithLargestTotal(int[][] data) {
        if (data == null || data.length == 0) return -1;

        int maxRowIndex = 0;
        // 先以第 0 列的總和作為初始最大值
        int maxSum = rowSum(data, 0); 

        for (int i = 1; i < data.length; i++) {
            int currentSum = rowSum(data, i);
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxRowIndex = i;
            }
        }
        return maxRowIndex;
    }
}