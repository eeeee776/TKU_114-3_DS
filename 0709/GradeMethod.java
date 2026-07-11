public class GradeMethod {
    public static void main(String[] args) {
        int javaScore = 85;
        int englishScore = 92;
        int mathScore = 78;

        // 1. 呼叫計算平均的方法
        double average = calculateAverage(javaScore, englishScore, mathScore);
        
        // 2. 呼叫判斷等級的方法
        String grade = getGrade(average);

        // 輸出結果
        System.out.println("Average Score: " + average);
        System.out.println("Grade: " + grade);
    }

    // 定義 calculateAverage 方法：接收三個 int 成績，回傳 double 平均值
    public static double calculateAverage(int javaScore, int englishScore, int mathScore) {
        // 注意：除以 3.0 確保得到帶有小數點的 double 結果，而非整數除法
        return (javaScore + englishScore + mathScore) / 3.0; 
    }

    // 定義 getGrade 方法：接收 double 平均值，回傳 String 成績等級
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
    
}