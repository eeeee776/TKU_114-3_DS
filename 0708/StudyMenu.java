import java.util.Scanner;

public class StudyMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 設定初始值為非 0 的數字，確保能進入 while 迴圈
        int option = -1;

        // 當 option 不等於 0 時，重複顯示選單並等待輸入
        while (option != 0) {
            System.out.println("=== 學習選單 ===");
            System.out.println("1: 輸出 Review Java");
            System.out.println("2: 輸出 Practice loops");
            System.out.println("3: 輸出 Push to GitHub");
            System.out.println("0: 離開");
            System.out.print("請輸入選項: ");
            
            option = sc.nextInt();

            // 根據使用者輸入的選項執行對應動作
            switch (option) {
                case 1:
                    System.out.println("Review Java\n");
                    break;
                case 2:
                    System.out.println("Practice loops\n");
                    break;
                case 3:
                    System.out.println("Push to GitHub\n");
                    break;
                case 0:
                    System.out.println("離開程式\n");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。\n");
            }
        }

        // 關閉 Scanner 資源
        sc.close();
    }
}