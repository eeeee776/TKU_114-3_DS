package week1_practice;

import java.util.Scanner;

public class GradeStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int count = 0;
        int total = 0;
        int max = -1; 
        int min = 101; 
        int passCount = 0;
        int failCount = 0;

        while (true) {
            System.out.print("請輸入成績 (輸入 -1 結束) : ");
            int score = sc.nextInt();

            // 輸入 -1 表示結束
            if (score == -1) {
                break;
            }

            // 驗證輸入範圍，如果不合法則要求重新輸入
            if (!isValidScore(score)) {
                System.out.println("成績不合法，請重新輸入。");
                continue;
            }

            // 統計總分與人數
            count++;
            total += score;

            // 統計最高分與最低分
            if (count == 1) {
                max = score;
                min = score;
            } else {
                if (score > max) {
                    max = score;
                }
                if (score < min) {
                    min = score;
                }
            }

            // 統計及格與不及格人數
            if (isPassing(score)) {
                passCount++;
            } else {
                failCount++;
            }
        }

        System.out.println(); // 輸出一行空白，符合執行範例的排版

        // 處理特殊情況：沒有輸入任何成績
        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            double average = (double) total / count;
            String grade = getGrade(average);
            printSummary(count, total, average, max, min, passCount, failCount, grade);
        }

        sc.close();
    }

    // 檢查成績是否在 0 到 100 之間
    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    // 判斷是否及格 (大於等於 60 分)
    public static boolean isPassing(int score) {
        return score >= 60;
    }

    // 根據平均分數回傳等第
    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // 輸出統計報表
    public static void printSummary(int count, int total, double average, int max, int min, int passCount, int failCount, String grade) {
        System.out.println("=== Grade Summary ===");
        System.out.println("Count: " + count);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        System.out.println("Average grade: " + grade);
    }
}
