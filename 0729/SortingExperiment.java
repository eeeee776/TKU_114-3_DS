import java.util.Arrays;

public class SortingExperiment {
    public static void main(String[] args) {
        int[] sorted = {10, 20, 30, 40, 50, 60, 70, 80};
        int[] reverse = {80, 70, 60, 50, 40, 30, 20, 10};
        int[] random = {45, 12, 80, 22, 60, 33, 90, 8};

        runExperiment("已排序資料", sorted);
        runExperiment("反向排序資料", reverse);
        runExperiment("隨機排列資料", random);
        
        System.out.println("\n【觀察結論】");
        System.out.println("1. Selection Sort 無論遇到何種資料，比較次數永遠是固定的。");
        System.out.println("2. Insertion Sort 遇到已排序資料時表現極好，比較次數降至最低且無須移動。");
        System.out.println("3. Insertion Sort 遇到反向資料時最吃虧，移動次數達到最大值。");
    }

    public static void runExperiment(String name, int[] original) {
        System.out.println("=== " + name + " ===");
        int[] dataForSelection = original.clone();
        int[] dataForInsertion = original.clone();

        int[] selRes = selectionSort(dataForSelection);
        int[] insRes = insertionSort(dataForInsertion);

        System.out.printf("Selection Sort -> 比較 %d 次，交換 %d 次%n", selRes[0], selRes[1]);
        System.out.printf("Insertion Sort -> 比較 %d 次，移動 %d 次%n", insRes[0], insRes[1]);
        System.out.println();
    }

    public static int[] selectionSort(int[] values) {
        int comp = 0, swap = 0;
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int i = start + 1; i < values.length; i++) {
                comp++;
                if (values[i] < values[minIndex]) minIndex = i;
            }
            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swap++;
            }
        }
        return new int[]{comp, swap};
    }

    public static int[] insertionSort(int[] values) {
        int comp = 0, move = 0;
        for (int i = 1; i < values.length; i++) {
            int key = values[i];
            int pos = i - 1;
            while (pos >= 0) {
                comp++;
                if (values[pos] > key) {
                    values[pos + 1] = values[pos];
                    move++;
                    pos--;
                } else {
                    break;
                }
            }
            values[pos + 1] = key;
        }
        return new int[]{comp, move};
    }
}