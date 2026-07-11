package week1_practice;

import java.util.Scanner;

public class AtmSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 初始餘額與統計變數
        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        while (true) {
            printMenu();
            System.out.print("請輸入選項:");
            int option = sc.nextInt();

            // 選擇 0 離開系統，並印出最終摘要
            if (option == 0) {
                System.out.println();
                printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                break;
            }

            switch (option) {
                case 1:
                    // 查詢餘額
                    printBalance(balance);
                    break;
                case 2:
                    // 存款
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額:");
                    balance = deposit(balance, depositAmount);
                    depositCount++;
                    totalDeposit += depositAmount;
                    printBalance(balance);
                    break;
                case 3:
                    // 提款
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額:");
                    if (canWithdraw(balance, withdrawAmount)) {
                        balance = withdraw(balance, withdrawAmount);
                        withdrawCount++;
                        totalWithdraw += withdrawAmount;
                        printBalance(balance);
                    } else {
                        System.out.println("餘額不足，無法提款。");
                    }
                    break;
                case 4:
                    // 顯示目前操作統計
                    System.out.println();
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
            
            // 印出空白行以符合範例排版
            System.out.println();
        }
        
        sc.close();
    }

    // 1. 顯示 ATM 選單
    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    // 2. 讀取並驗證大於 0 的正整數金額
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) {
                break;
            } else {
                System.out.println("金額必須大於 0，請重新輸入。");
            }
        }
        return amount;
    }

    // 3. 執行存款並回傳新餘額
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    // 4. 執行提款並回傳新餘額
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    // 5. 判斷餘額是否足夠提款
    public static boolean canWithdraw(int balance, int amount) {
        return balance >= amount;
    }

    // 6. 輸出目前餘額
    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    // 7. 輸出操作摘要
    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}