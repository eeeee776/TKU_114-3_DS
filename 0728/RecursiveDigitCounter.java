public class RecursiveDigitCounter {
    public static void main(String[] args) {
        // 至少 6 組測試資料
        System.out.println("在 8808 中找 8: " + countDigit(8808, 8)); // 預期: 3
        System.out.println("在 5729 中找 3: " + countDigit(5729, 3)); // 預期: 0
        System.out.println("在 0 中找 0: " + countDigit(0, 0));       // 預期: 1
        System.out.println("在 0 中找 5: " + countDigit(0, 5));       // 預期: 0
        System.out.println("在 1000 中找 0: " + countDigit(1000, 0)); // 預期: 3
        System.out.println("在 77777 中找 7: " + countDigit(77777, 7)); // 預期: 5
    }

    public static int countDigit(int number, int target) {
        // 處理負數情況
        if (number < 0) {
            number = -number;
        }
        
        // Base case：當數字只剩個位數時
        if (number < 10) {
            return (number == target) ? 1 : 0;
        }
        
        // Recursive case
        int match = (number % 10 == target) ? 1 : 0;
        return match + countDigit(number / 10, target);
    }
}