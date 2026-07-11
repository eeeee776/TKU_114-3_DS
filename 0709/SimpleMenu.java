import java.util.Scanner;

public class SimpleMenu {
    
    // main 方法現在只保留主要的執行流程
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            // 在原本輸出選單的位置，改為呼叫 printMenu() 方法
            printMenu();
            
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Hello");
                    break;
                case 2:
                    System.out.println("Java");
                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }
        
        sc.close();
    }

    // 將原本寫在 main 裡面的選單輸出程式碼，搬移到這個獨立的方法中
    public static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Say hello");
        System.out.println("2. Say Java");
        System.out.println("0. Exit");
        System.out.print("請輸入選項：");
    }
    
}