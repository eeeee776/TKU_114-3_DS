import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        // 建立 Scanner 物件來讀取使用者輸入
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("請輸入一個整數 (輸入 0 結束): ");
        int number = scanner.nextInt(); // 讀取第一次輸入的整數

        // 當輸入的數字不等於 0 時，持續執行迴圈
        while (number != 0) {
            // 輸出該數字
            System.out.println("您輸入的數字是: " + number);
            
            // 再次讀取下一個整數，這是避免無窮迴圈的關鍵步驟
            System.out.print("請輸入一個整數 (輸入 0 結束): ");
            number = scanner.nextInt(); 
        }
        
        System.out.println("程式結束。");
        scanner.close(); 
    }
}