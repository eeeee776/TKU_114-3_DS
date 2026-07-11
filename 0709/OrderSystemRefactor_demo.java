import java.util.Scanner;

public class OrderSystemRefactor_demo {
    
    // 8. 主程式 main 不應塞入所有細節，只保留主要流程
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int option = -1;
        int totalQuantity = 0;
        int totalAmount = 0;

        // 2. 使用 while 建立重複選單
        while (option != 0) {
            // 4. 使用方法顯示選單
            printMenu();
            System.out.print("請輸入商品選項: ");
            option = sc.nextInt();

            if (option == 0) {
                break;
            }

            // 3. 使用方法判斷商品價格
            int price = getPrice(option);
            String itemName = getItemName(option);

            if (price == 0) {
                System.out.println("無效的選項，請重新輸入。\n");
                continue; 
            }

            // 5. 使用方法讀取合法數量 (包含輸入驗證)
            int quantity = readValidQuantity(sc);

            // 6. 使用方法計算小計
            int subtotal = calculateSubtotal(price, quantity);
            
            System.out.println("=> 本次小計: " + itemName + " " + quantity + " 杯，共 " + subtotal + " 元\n");

            totalQuantity += quantity;
            totalAmount += subtotal;
        }

        // 7. 使用方法印出收據
        printReceipt(totalQuantity, totalAmount);

        sc.close();
    }

    // ==========================================
    // 以下為自訂方法 (共 6 個，符合「至少定義 5 個自訂方法」標準)
    // ==========================================

    // 方法 1：無參數、無回傳值
    public static void printMenu() {
        System.out.println("=== 簡易點餐系統 ===");
        System.out.println("1: Black tea (30元)");
        System.out.println("2: Green tea (35元)");
        System.out.println("3: Coffee (50元)");
        System.out.println("0: Checkout (結帳)");
    }

    // 方法 2：有參數、有回傳值 (符合完成標準)
    public static int getPrice(int option) {
        // 使用 switch 判斷價格
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }

    // 方法 3：有參數、有回傳值
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Coffee";
            default: return "";
        }
    }

    // 方法 4：有參數、有回傳值 (包含輸入驗證，符合完成標準)
    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量: ");
        int quantity = sc.nextInt();
        
        while (quantity <= 0) {
            System.out.print("數量必須大於 0，請重新輸入數量: ");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    // 方法 5：有多個參數、有回傳值
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // 方法 6：有多個參數、無回傳值
    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("\n=== 結帳明細 ===");
        System.out.println("最後總數量: " + totalItems + " 杯");
        System.out.println("最後總金額: " + totalAmount + " 元");
    }
}