public class RecursiveDigitSumPractice {
    public static void main(String[] args) {
        // 準備至少 5 組測試資料
        System.out.println("digitSum(5729) = " + digitSum(5729)); // 預期：23
        System.out.println("digitSum(0) = " + digitSum(0));       // 預期：0
        System.out.println("digitSum(8) = " + digitSum(8));       // 預期：8
        System.out.println("digitSum(12345) = " + digitSum(12345)); // 預期：15
        System.out.println("digitSum(9009) = " + digitSum(9009)); // 預期：18
    }

    public static int digitSum(int number) {
        // Base case: 當數字縮小到 0 時，停止呼叫並回傳 0
        if (number == 0) {
            return 0;
        }
        
        // Recursive case:
        // 1. number % 10 取得個位數（例如 5729 % 10 = 9）
        // 2. number / 10 去除個位數（例如 5729 / 10 = 572）
        // 3. 將個位數加上「剩餘數字的總和」
        return (number % 10) + digitSum(number / 10);
    }
}