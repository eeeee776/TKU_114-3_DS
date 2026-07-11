package week1_practice;

import java.util.Scanner;

public class PersonalProfileApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 輸入基本資料
        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        int age = readPositiveInt(sc, "請輸入年齡：");
        double height = readPositiveDouble(sc, "請輸入身高 (公尺)：");
        double weight = readPositiveDouble(sc, "請輸入體重 (公斤)：");

        // 2. 進行計算與判斷
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);
        boolean adult = isAdult(age);

        System.out.println(); // 輸出範例中有一行空白

        // 3. 輸出完整報表
        printReport(name, age, adult, height, weight, bmi, level);

        sc.close();
    }

    // 檢查並讀取大於 0 的整數
    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            value = sc.nextInt();
            if (value > 0) {
                break;
            } else {
                System.out.println("輸入不合法，年齡必須大於 0，請重新輸入。");
            }
        }
        return value;
    }

    // 檢查並讀取大於 0 的浮點數
    public static double readPositiveDouble(Scanner sc, String message) {
        double value;
        while (true) {
            System.out.print(message);
            value = sc.nextDouble();
            if (value > 0) {
                break;
            } else {
                System.out.println("輸入不合法，數值必須大於 0，請重新輸入。");
            }
        }
        return value;
    }

    // 計算 BMI
    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }

    // 判斷 BMI 分級
    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // 判斷是否成年 (以台灣標準滿 18 歲為成年)
    public static boolean isAdult(int age) {
        return age >= 18;
    }

    // 輸出健康報表
    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println("=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + level);
    }
}