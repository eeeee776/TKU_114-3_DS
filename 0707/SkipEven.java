public class SkipEven {
    public static void main(String[] args) {
        // 使用 for 迴圈讓 i 從 1 跑到 10
        for (int i = 1; i <= 10; i++) {
            // 判斷是否為偶數 (除以 2 的餘數為 0)
            if (i % 2 == 0) {
                // 如果是偶數，使用 continue 跳過本次迴圈，直接進入下一次迴圈
                continue;
            }
            
            // 如果不是偶數 (即奇數)，則印出該數字
            System.out.println(i);
        }
    }
}