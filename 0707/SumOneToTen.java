public class SumOneToTen {
    public static void main(String[] args) {
        // 宣告一個變數 sum 用來儲存加總的結果，初始值設定為 0
        int sum = 0;

        // 使用 for 迴圈，讓 i 從 1 跑到 10
        for (int i = 1; i <= 10; i++) {
            // 將目前的 i 值加到 sum 變數中
            sum = sum + i; // 也可以簡寫成 sum += i;
        }

        // 迴圈結束後，輸出最終的總和結果
        System.out.println("Sum: " + sum);
    }
}