import java.util.Arrays;

public class InsertionSortPractice {
    public static void main(String[] args) {
        System.out.println("--- 一般資料 ---");
        testInsertionSort(new int[]{30, 10, 20, 50, 40, 5});

        System.out.println("--- 已排序資料 ---");
        testInsertionSort(new int[]{5, 10, 20, 30, 40, 50});

        System.out.println("--- 反向排序資料 ---");
        testInsertionSort(new int[]{50, 40, 30, 20, 10, 5});
        
        System.out.println("\n【觀察結論】");
        System.out.println("「反向排序資料」的右移與比較次數最多，因為每一筆新的資料都需要一路比較並將前方所有元素右移。");
        System.out.println("「已排序資料」不需要任何右移，效率最高。");
    }

    public static void testInsertionSort(int[] values) {
        int comparisons = 0;
        int moves = 0;

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0) {
                comparisons++;
                if (values[position] > key) {
                    values[position + 1] = values[position];
                    moves++;
                    position--;
                } else {
                    break;
                }
            }
            values[position + 1] = key;
            System.out.printf("插入 %d：位置 %d，目前陣列 %s%n", key, position + 1, Arrays.toString(values));
        }
        System.out.printf("-> 總比較：%d 次，總右移：%d 次%n%n", comparisons, moves);
    }
}