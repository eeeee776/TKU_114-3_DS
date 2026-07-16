import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 輸入資料筆數並建立對應長度的陣列
        int count = readCount(sc);
        int[] scores = new int[count];
        
        // 2. 逐筆輸入成績
        inputScores(sc, scores);
        
        // 3. 顯示全部成績
        System.out.print("\n全部成績：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
        
        // 4. 顯示總分、平均、最高分、最低分
        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);
        
        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        
        // 5. 顯示及格與不及格人數 (預設及格為 60 分)
        int passCount = countPass(scores);
        int failCount = scores.length - passCount;
        System.out.println("及格人數：" + passCount);
        System.out.println("不及格人數：" + failCount);
        
        // 6. 尋找目標成績
        System.out.print("\n請輸入一個目標成績以尋找索引：");
        int target = sc.nextInt();
        int index = findIndex(scores, target);
        
        if (index != -1) {
            System.out.println("目標成績第一次出現的索引為：" + index);
        } else {
            System.out.println("找不到該目標成績！");
        }
        
        sc.close();
    }

    // 方法：輸入資料筆數並驗證 (1 到 50)
    public static int readCount(Scanner sc) {
        int count;
        while (true) {
            System.out.print("請輸入資料筆數 (1 到 50)：");
            count = sc.nextInt();
            if (count >= 1 && count <= 50) {
                break;
            }
            System.out.println("輸入錯誤，資料筆數必須在 1 到 50 之間，請重新輸入。");
        }
        return count;
    }

    // 方法：逐筆輸入成績並驗證 (0 到 100)
    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績 (0 到 100)：");
                int score = sc.nextInt();
                if (score >= 0 && score <= 100) {
                    scores[i] = score;
                    break;
                }
                System.out.println("錯誤資料！成績必須在 0 到 100 之間，請重新輸入。");
            }
        }
    }

    // 方法：計算總分
    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    // 方法：尋找最高分
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    // 方法：尋找最低分
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    // 方法：計算及格人數
    public static int countPass(int[] scores) {
        int pass = 0;
        for (int score : scores) {
            if (score >= 60) {
                pass++;
            }
        }
        return pass;
    }

    // 方法：尋找目標成績的第一次索引，若無則回傳 -1
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }
}