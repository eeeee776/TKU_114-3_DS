import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 使用 Integer 泛型建立動態集合
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("開始輸入成績 (範圍 0~100)，輸入 -1 結束程式");

        // 1. 持續接收輸入直到 -1
        while (true) {
            System.out.print("請輸入成績：");
            int input = scanner.nextInt();

            if (input == -1) {
                break; // 離開迴圈
            }

            // 2. 驗證資料範圍
            if (input >= 0 && input <= 100) {
                scores.add(input);
            } else {
                System.out.println("錯誤：成績必須介於 0 到 100 之間！");
            }
        }

        // 3. 處理空集合的邊界情況
        if (scores.isEmpty()) {
            System.out.println("目前沒有輸入任何有效成績。");
            scanner.close();
            return; // 提早結束程式
        }

        // 4. 呼叫 Method 計算並顯示結果
        System.out.println("\n--- 成績統計結果 ---");
        System.out.println("總筆數：" + scores.size());
        System.out.println("平均分：" + calculateAverage(scores));
        System.out.println("最高分：" + findMax(scores));
        System.out.println("最低分：" + findMin(scores));
        
        // 5. 取得篩選後的新集合並印出
        ArrayList<Integer> passedScores = filterPassingScores(scores);
        System.out.println("及格名單：" + passedScores);

        scanner.close();
    }

    // --- 以下為獨立的 Method，負責資料處理 ---

    /**
     * 計算平均分數
     */
    public static double calculateAverage(ArrayList<Integer> scores) {
        int sum = 0;
        // 使用 for-each 走訪集合
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    /**
     * 尋找最高分
     */
    public static int findMax(ArrayList<Integer> scores) {
        int max = scores.get(0); // 假設第一筆是最大值
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    /**
     * 尋找最低分
     */
    public static int findMin(ArrayList<Integer> scores) {
        int min = scores.get(0); // 假設第一筆是最小值
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    /**
     * 篩選及格分數，回傳一個新的 ArrayList
     */
    public static ArrayList<Integer> filterPassingScores(ArrayList<Integer> scores) {
        ArrayList<Integer> result = new ArrayList<>(); // 建立新集合存放結果
        for (int score : scores) {
            if (score >= 60) {
                result.add(score); // 條件符合才加入
            }
        }
        return result; // 不修改原集合，而是回傳篩選後的新集合
    }
}