import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int option = -1;
        int totalItems = 0;   // 計數器：計算總數量
        int totalAmount = 0;  // 累加器：計算總金額

        // 使用 while 建立重複選單
        while (option != 0) {
            System.out.println("=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入選項:");
            
            // 讀取商品選項
            option = sc.nextInt();

            // 選擇 0 時結帳並結束迴圈
            if (option == 0) {
                break;
            }

            int price = 0;

            // 使用 switch 判斷商品選項與單價
            switch (option) {
                case 1:
                    price = 30;
                    break;
                case 2:
                    price = 35;
                    break;
                case 3:
                    price = 50;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。\n");
                    continue; // 回到迴圈開頭重新顯示選單
            }

            System.out.print("請輸入數量:");
            int quantity = sc.nextInt();

            // 使用輸入驗證確認數量大於 0
            while (quantity <= 0) {
                System.out.print("數量必須大於 0，請重新輸入數量:");
                quantity = sc.nextInt();
            }

            // 計算本次小計
            int subtotal = price * quantity;
            System.out.println("Subtotal: " + subtotal + "\n");

            // 累加總數量與總金額
            totalItems += quantity;
            totalAmount += subtotal;
        }

        // 輸出最後總數量與總金額
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);

        sc.close();
    }
}