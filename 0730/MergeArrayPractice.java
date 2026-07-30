import java.util.Arrays;

public class MergeArrayPractice {
    public static void main(String[] args) {
        testMerge(new int[]{1, 3, 5}, new int[]{2, 4, 6, 8});
        testMerge(new int[]{}, new int[]{1, 2, 3});
        testMerge(new int[]{-5, -1, 0, 0, 2}, new int[]{-3, 0, 1, 2, 5});
    }

    public static void testMerge(int[] left, int[] right) {
        System.out.println("左陣列: " + Arrays.toString(left));
        System.out.println("右陣列: " + Arrays.toString(right));
        int[] result = merge(left, right);
        System.out.println("合併後: " + Arrays.toString(result));
        System.out.println("-".repeat(30));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }
}