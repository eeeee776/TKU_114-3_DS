public class Q04_LoopRepair {
    public static void main(String[] args) {
        System.out.println(sumOddRange(3, 7));
        System.out.println(sumOddRange(7, 3));
        System.out.println(sumOddRange(2, 2));
        System.out.println(sumOddRange(-3, 3));
    }

    public static int sumOddRange(int start, int end) {
        int sum = 0;
        
        // 找出實際的區間起點與終點（解決 start > end 的反向區間問題）
        int min = Math.min(start, end);
        int max = Math.max(start, end);

        // 將條件改為 <= max，以包含區間終點
        for (int i = min; i <= max; i++) {
            if (i % 2 != 0) {
                sum += i;
            }
        }
        
        return sum;
    }
}