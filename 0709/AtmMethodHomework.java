import java.util.Scanner;

public class AtmMethodHomework {
    
    // 主程式：只保留核心流程控制，將細節委派給各個方法
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 規則 1：初始餘額設定為 1000
        int balance = 1000;
        int option = -1;

        // 規則 5：選單可重複操作直到輸入 0
        while (option != 0) {
            // 呼叫方法顯示選單
            printMenu();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // 呼叫方法印出餘額
                    printBalance(balance);
                    break;
                    
                case 2:
                    // 呼叫方法讀取大於 0 的存款金額
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額: ");
                    // 呼叫方法計算存款後的餘額，並更新 balance 變數
                    balance = deposit(balance, depositAmount);
                    
                    System.out.println("=> 存款成功！");
                    printBalance(balance);
                    break;
                    
                case 3:
                    // 呼叫方法讀取大於 0 的提款金額
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");
                    
                    // 規則 4：提款金額不能大於目前餘額
                    while (withdrawAmount > balance) {
                        System.out.println("餘額不足 (目前餘額為 " + balance + " 元)，請重新輸入！");
                        // 重新要求輸入大於 0 的金額
                        withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");
                    }
                    
                    // 呼叫方法計算提款後的餘額，並更新 balance 變數
                    balance = withdraw(balance, withdrawAmount);
                    
                    System.out.println("=> 提款成功！請取鈔。");
                    printBalance(balance);
                    break;
                    
                case 0:
                    System.out.println("=> 感謝您使用本 ATM 系統，再見！\n");
                    break;
                    
                default:
                    System.out.println("=> 無效的選項，請重新輸入。\n");
                    break;
            }
        }
        
        sc.close();
    }

    // ==========================================
    // 以下為作業要求的 5 個自訂方法
    // ==========================================

    // 1. 顯示選單 (無參數，無回傳值)
    public static void printMenu() {
        System.out.println("=== ATM 系統 ===");
        System.out.println("1: 查詢餘額");
        System.out.println("2: 存款");
        System.out.println("3: 提款");
        System.out.println("0: 離開");
        System.out.print("請選擇功能 (0-3): ");
    }

    // 2. 讀取正整數金額 (有參數，有回傳值)
    // 透過 message 參數，可以彈性決定要印出「請輸入存款金額」還是「請輸入提款金額」
    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        int amount = sc.nextInt();
        
        // 規則 2 & 3：金額必須大於 0
        while (amount <= 0) {
            System.out.print("金額必須大於 0，請重新輸入: ");
            amount = sc.nextInt();
        }
        return amount;
    }

    // 3. 處理存款 (有參數，有回傳值)
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    // 4. 處理提款 (有參數，有回傳值)
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    // 5. 印出餘額 (有參數，無回傳值)
    public static void printBalance(int balance) {
        System.out.println("=> 目前餘額: " + balance + " 元\n");
    }
}