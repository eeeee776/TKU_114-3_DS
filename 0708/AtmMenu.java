import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 規則 1：初始餘額設定為 1000
        int balance = 1000;
        int option = -1;

        // 規則 6 & 作業要求：至少使用一次 while 讓選單可以重複操作
        while (option != 0) {
            System.out.println("=== ATM 系統 ===");
            System.out.println("1: 查詢餘額");
            System.out.println("2: 存款");
            System.out.println("3: 提款");
            System.out.println("0: 離開");
            System.out.print("請選擇功能 (0-3): ");
            
            option = sc.nextInt();

            // 規則 7 & 作業要求：至少使用一次 switch 判斷選項
            switch (option) {
                case 1:
                    // 查詢餘額
                    System.out.println("=> 目前餘額: " + balance + " 元\n");
                    break;
                    
                case 2:
                    // 存款功能
                    System.out.print("請輸入存款金額: ");
                    int deposit = sc.nextInt();
                    
                    // 作業要求：輸入驗證 (規則 2：存款金額必須大於 0)
                    while (deposit <= 0) {
                        System.out.print("存款金額必須大於 0，請重新輸入: ");
                        deposit = sc.nextInt();
                    }
                    
                    balance += deposit; // 更新餘額
                    System.out.println("=> 存款成功！目前餘額: " + balance + " 元\n");
                    break;
                    
                case 3:
                    // 提款功能
                    System.out.print("請輸入提款金額: ");
                    int withdraw = sc.nextInt();
                    
                    // 作業要求：輸入驗證 (規則 3 & 4：提款金額必須大於 0，且不能大於目前餘額)
                    while (withdraw <= 0 || withdraw > balance) {
                        if (withdraw <= 0) {
                            System.out.print("提款金額必須大於 0，請重新輸入: ");
                        } else {
                            System.out.print("餘額不足 (目前餘額為 " + balance + " 元)，請重新輸入提款金額: ");
                        }
                        withdraw = sc.nextInt();
                    }
                    
                    balance -= withdraw; // 扣除提款金額
                    System.out.println("=> 提款成功！請取鈔。目前餘額: " + balance + " 元\n");
                    break;
                    
                case 0:
                    // 離開系統
                    System.out.println("=> 感謝您使用本 ATM 系統，再見！\n");
                    break;
                    
                default:
                    System.out.println("=> 無效的選項，請重新輸入。\n");
                    break;
            }
        }
        
        sc.close();
    }
}