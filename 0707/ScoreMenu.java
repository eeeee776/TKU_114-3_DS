import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = scanner.next();

        System.out.print("請輸入 Java 成績：");
        double javaScore = scanner.nextDouble();
        
        System.out.print("請輸入 English 成績：");
        double englishScore = scanner.nextDouble();
        
        System.out.print("請輸入 Math 成績：");
        double mathScore = scanner.nextDouble();

        // 3. 計算平均分數 (作業要求：平均分數必須使用 double)
        double average = (javaScore + englishScore + mathScore) / 3.0;

        // 4. 判斷及格或不及格 
        String passStatus = "";
        if (average >= 60) {
            passStatus = "Pass (及格)";
        } else {
            passStatus = "Fail (不及格)";
        }

        // 5. 判斷等第 (作業要求：至少使用一次 if else if else)
        String grade = "";
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int option = -1; // 初始化選項變數，預設為 -1 以便進入迴圈

        // 7. 使用 while，讓選單可以重複操作，直到輸入 0
        while (option != 0) {
            // 列印選單
            System.out.println("\n=== 成績查詢選單 ===");
            System.out.println("1 : 顯示平均分數");
            System.out.println("2 : 顯示及格狀態");
            System.out.println("3 : 顯示等第");
            System.out.println("0 : 離開");
            System.out.print("請選擇功能 (0-3)：");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(name + " 的平均分數為: " + average);
                    break;
                case 2:
                    System.out.println(name + " 的狀態: " + passStatus);
                    break;
                case 3:
                    System.out.println(name + " 的等第為: " + grade);
                    break;
                case 0:
                    System.out.println("系統結束，謝謝！");
                    break;
                default:
                    System.out.println("輸入錯誤，請重新輸入 0 到 3 的選項。");
            }
        }

        scanner.close();
    }
}