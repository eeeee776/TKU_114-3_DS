public class PassCounter {
    public static void main(String[] args) {
        // 宣告一個名為 count 的計數器變數，初始值設為 0
        int count = 0; 

        // 設定三個成績變數
        int score1 = 80;
        int score2 = 55;
        int score3 = 70;

        // 判斷第一個成績是否及格 (大於等於 60 分)
        if (score1 >= 60) {
            count++; // 若及格，計數器加 1
        }

        // 判斷第二個成績是否及格
        if (score2 >= 60) {
            count++;
        }

        // 判斷第三個成績是否及格
        if (score3 >= 60) {
            count++;
        }

        // 輸出最後計算的及格總數，以符合預期輸出
        System.out.println("Pass count: " + count);
    }
}