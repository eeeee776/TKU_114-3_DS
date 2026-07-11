package week1_practice;

import java.util.Scanner;

public class DrinkOrderSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 計數器與累加器
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;
        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            printMenu();
            System.out.print("請輸入選項:");
            int option = sc.nextInt();

            // 輸入 0 結帳並結束迴圈
            if (option == 0) {
                System.out.println();
                break;
            }

            // 處理 1~4 的有效商品選項
            if (option >= 1 && option <= 4) {
                int quantity = readValidQuantity(sc);
                
                // 累加各商品銷售數量
                switch (option) {
                    case 1: blackTeaCount += quantity; break;
                    case 2: greenTeaCount += quantity; break;
                    case 3: milkTeaCount += quantity; break;
                    case 4: coffeeCount += quantity; break;
                }

                // 取得商品名稱與單價，計算小計
                String itemName = getItemName(option);
                int price = getPrice(option);
                int subtotal = calculateSubtotal(price, quantity);

                // 印出該次點餐小計
                System.out.println(itemName + " x " + quantity);
                System.out.println("Subtotal: " + subtotal);
                System.out.println(); // 輸出一行空白，區隔下一次的選單
                
                // 累加總杯數與折扣前總金額
                totalItems += quantity;
                totalAmount += subtotal;
                
            } else {
                System.out.println("無效的選項，請重新輸入。\n");
            }
        }

        // 計算折扣後總金額並輸出收據
        int finalAmount = calculateDiscountedTotal(totalAmount);
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, finalAmount);
        
        sc.close();
    }

    // 1. 顯示商品選單
    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea   $30");
        System.out.println("2. Green tea   $35");
        System.out.println("3. Milk tea    $45");
        System.out.println("4. Coffee      $50");
        System.out.println("0. Checkout");
    }

    // 2. 取得商品單價
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return 0;
        }
    }

    // 3. 取得商品名稱
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Milk tea";
            case 4: return "Coffee";
            default: return "";
        }
    }

    // 4. 驗證並讀取正確的數量 (必須大於 0)
    public static int readValidQuantity(Scanner sc) {
        int quantity;
        while (true) {
            System.out.print("請輸入數量:");
            quantity = sc.nextInt();
            if (quantity > 0) {
                break;
            } else {
                System.out.println("數量必須大於 0，請重新輸入。");
            }
        }
        return quantity;
    }

    // 5. 計算本次小計
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // 6. 計算折扣後總金額 (滿 300 打 9 折)
    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        }
        return totalAmount;
    }

    // 7. 輸出完整收據
    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount, int finalAmount) {
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        
        // 判斷是否有折扣，依據圖片範例輸出 "Yes" 或 "No"
        if (totalAmount >= 300) {
            System.out.println("Discount: Yes");
        } else {
            System.out.println("Discount: No");
        }
        
        System.out.println("Final amount: " + finalAmount);
    }
}