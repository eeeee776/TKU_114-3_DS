import java.util.Scanner;

public class HealthAdvisor {
    public static void main(String[] args) {
        // 建立 Scanner 物件以讀取使用者輸入
        Scanner scanner = new Scanner(System.in);
        String continueInput = "y"; // 初始化迴圈控制變數

        // 7. 使用 while 詢問是否繼續輸入下一筆資料
        while (continueInput.equalsIgnoreCase("y")) {
            
            // 1. 使用 Scanner 輸入姓名
            System.out.print("請輸入姓名：");
            String name = scanner.next();

            // 2. 輸入身高，單位為公尺
            System.out.print("請輸入身高 (公尺)：");
            double height = scanner.nextDouble();

            // 3. 輸入體重，單位為公斤
            System.out.print("請輸入體重 (公斤)：");
            double weight = scanner.nextDouble();

            // 4. 計算 BMI。公式：weight / (height * height)
            double bmi = weight / (height * height);

            // 5. 使用 if else if else 判斷 BMI 分級
            String level = "";
            if (bmi < 18.5) {
                level = "Underweight";
            } else if (bmi < 24) {
                level = "Normal";
            } else if (bmi < 27) {
                level = "Overweight";
            } else {
                level = "Obese";
            }

            // 6. 輸出姓名、BMI 與分級結果 (依照輸出範例格式)
            System.out.println("\n=== BMI Report ===");
            System.out.println("Name: " + name);
            System.out.println("BMI: " + bmi);
            System.out.println("Level: " + level + "\n");

            // 詢問使用者是否要繼續執行迴圈
            System.out.print("是否繼續輸入下一筆？(y/n) : ");
            continueInput = scanner.next();
        }

        scanner.close(); // 程式結束前關閉 Scanner
    }
}