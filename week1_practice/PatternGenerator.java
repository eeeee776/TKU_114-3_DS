package week1_practice;

import java.util.Scanner;

public class PatternGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            int option = sc.nextInt();
            
            if (option == 0) {
                System.out.println("程式結束。");
                break;
            }
            
            System.out.println(); // 排版用空白行
            
            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2:
                    int height1 = readPositiveInt(sc, "請輸入正三角形高度：");
                    System.out.println();
                    printTriangle(height1);
                    break;
                case 3:
                    int height2 = readPositiveInt(sc, "請輸入倒三角形高度：");
                    System.out.println();
                    printReverseTriangle(height2);
                    break;
                case 4:
                    int rows = readPositiveInt(sc, "請輸入列數：");
                    int cols = readPositiveInt(sc, "請輸入欄數：");
                    System.out.println();
                    printNumberGrid(rows, cols);
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
            System.out.println(); // 區隔下一次操作
        }
        
        sc.close();
    }

    // 1. 顯示選單
    public static void printMenu() {
        System.out.println("=== Pattern Generator ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    // 2. 讀取並驗證大於 0 的正整數
    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            value = sc.nextInt();
            if (value > 0) {
                break;
            } else {
                System.out.println("數值必須大於 0，請重新輸入。");
            }
        }
        return value;
    }

    // 3. 輸出完整九九乘法表
    public static void printMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                // 使用 \t 讓表格對齊
                System.out.print(j + " x " + i + " = " + (i * j) + "\t");
            }
            System.out.println();
        }
    }

    // 4. 輸出正三角形星號
    public static void printTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 5. 輸出倒三角形星號
    public static void printReverseTriangle(int height) {
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 6. 輸出數字方格
    public static void printNumberGrid(int rows, int cols) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}