public class ScoreRankingPractice {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 92, 60, 45, 88, 100};
        selectionSortDescending(scores);

        System.out.println("名次\t分數\t結果");
        int rank = 1;
        for (int i = 0; i < scores.length; i++) {
            // 遇到同分時，保持跟上一筆相同的名次；不同分時，名次等於 (索引 + 1)
            if (i > 0 && scores[i] < scores[i - 1]) {
                rank = i + 1;
            }
            String status = scores[i] >= 60 ? "及格" : "不及格";
            System.out.printf("%d\t%d\t%s%n", rank, scores[i], status);
        }
    }

    public static void selectionSortDescending(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int maxIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                if (values[index] > values[maxIndex]) {
                    maxIndex = index;
                }
            }
            if (maxIndex != start) {
                int temp = values[start];
                values[start] = values[maxIndex];
                values[maxIndex] = temp;
            }
        }
    }
}