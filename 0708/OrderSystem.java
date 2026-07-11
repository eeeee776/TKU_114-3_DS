import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int option = -1;       // 儲存使用者選擇的選單項目
        int totalQuantity = 0; // counter: 累加總數量 
        int totalAmount = 0;   // accumulator: 累加總金額 

        // 1. 使用 while 迴圈重複點餐直到選擇 0 結帳
        while (option != 0) {
            // 顯示選單 
            System.out.println("=== 簡易點餐系統 ===");
            System.out.println("1: Black tea (30元)");
            System.out.println("2: Green tea (35元)");
            System.out.println("3: Coffee (50元)");
            System.out.println("0: Checkout (結帳)");
            System.out.print("請輸入商品選項: ");
            
            // 2. 使用者輸入商品選項 
            option = sc.nextInt();

            // 如果選擇 0，跳出迴圈準備結帳
            if (option == 0) {
                break;
            }

            int price = 0;
            String itemName = "";

            // process: 判斷商品單價 
            switch (option) {
                case 1:
                    itemName = "Black tea";
                    price = 30;
                    break;
                case 2:
                    itemName = "Green tea";
                    price = 35;
                    break;
                case 3:
                    itemName = "Coffee";
                    price = 50;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。\n");
                    continue; // 略過底下步驟，重新顯示選單
            }

            // 3. 若選擇 1、2、3，要求輸入數量 
            System.out.print("請輸入數量: ");
            int quantity = sc.nextInt();

            // 4. 數量必須大於 0 (input validation) 
            while (quantity <= 0) {
                System.out.print("數量必須大於 0，請重新輸入數量: ");
                quantity = sc.nextInt();
            }

            // 5. 計算本次小計 
            int subtotal = price * quantity;
            
            // output: 每次點餐小計 
            System.out.println("=> 本次小計: " + itemName + " " + quantity + " 杯，共 " + subtotal + " 元\n");

            // 6. 累加總數量與總金額 
            totalQuantity += quantity;
            totalAmount += subtotal;
        }

        // 7. 選擇 0 時輸出總數量與總金額 
        System.out.println("\n=== 結帳明細 ===");
        System.out.println("最後總數量: " + totalQuantity + " 杯");
        System.out.println("最後總金額: " + totalAmount + " 元");

        sc.close();
    }
}