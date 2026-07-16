/*
 * 錯誤修正紀錄：
 * 
 * 1. 一個編譯錯誤
 *    - 錯誤位置：`System.out.println("系統結束，年齡：" + age)`
 *    - 錯誤類型：語法錯誤 (Syntax Error)
 *    - 原因：程式碼語句結尾缺少分號 (;)
 *    - 修正方式：在結尾加上 `;`
 * 
 * 2. 一個陣列越界錯誤
 *    - 錯誤位置：`for (int i = 0; i <= scores.length; i++)`
 *    - 錯誤類型：執行時期錯誤 (ArrayIndexOutOfBoundsException)
 *    - 原因：陣列的最後一個索引是 length - 1，使用 `<=` 會讓迴圈嘗試存取不存在的 scores[3]
 *    - 修正方式：將迴圈條件改為 `i < scores.length`
 * 
 * 3. 一個字串比較錯誤
 *    - 錯誤位置：`if (command == "exit")`
 *    - 錯誤類型：邏輯錯誤
 *    - 原因：在 Java 中，`==` 用於比較字串時是比較記憶體位址，而非字串內容。
 *    - 修正方式：使用 `.equals()` 方法，改為 `if (command.equals("exit"))`
 * 
 * 4. 一個整數除法造成的邏輯錯誤
 *    - 錯誤位置：`double average = total / scores.length;`
 *    - 錯誤類型：邏輯錯誤
 *    - 原因：`total` 和 `scores.length` 皆為整數，相除時會執行整數除法（無條件捨去小數），之後才轉為 double，導致小數點資料遺失。
 *    - 修正方式：將其中一個變數強制轉型為 double，例如 `(double) total / scores.length`
 * 
 * 5. 一個 Scanner 換行問題
 *    - 錯誤位置：`int age = sc.nextInt();` 與下一行讀取字串之間
 *    - 錯誤類型：邏輯錯誤
 *    - 原因：`nextInt()` 只會讀取數字，並將使用者按下 Enter 鍵產生的換行字元 (`\n`) 留在緩衝區，導致接下來的 `nextLine()` 直接讀取到空字串而跳過輸入。
 *    - 修正方式：在 `nextInt()` 後面加入一行 `sc.nextLine();` 來清空緩衝區的換行字元。
 */

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        // 修正 2：陣列越界錯誤 (將 <= 改為 <)
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        // 修正 4：整數除法邏輯錯誤 (加入 (double) 轉型)
        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f\n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        
        // 修正 5：Scanner 換行問題 (加入這行來消耗緩衝區的 \n)
        sc.nextLine(); 

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        // 修正 3：字串比較錯誤 (將 == 改為 .equals())
        if (command.equals("exit")) {
            // 修正 1：編譯錯誤 (加上分號)
            System.out.println("系統結束，年齡：" + age); 
        }

        sc.close();
    }
}