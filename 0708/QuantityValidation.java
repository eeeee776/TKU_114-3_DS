import java.util.Scanner;

public class QuantityValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 第一次請使用者輸入商品數量
        System.out.print("請輸入商品數量: ");
        int quantity = sc.nextInt();
        
        // 進行輸入驗證：若數量小於或等於 0，則進入迴圈
        while (quantity <= 0) {
            System.out.print("數量不合法 (必須大於 0)，請重新輸入: ");
            quantity = sc.nextInt();
        }
        
        // 離開迴圈代表輸入合法，印出最終結果
        System.out.println("您輸入的商品數量為: " + quantity);
        
        sc.close();
    }
}