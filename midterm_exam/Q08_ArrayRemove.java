public class Q08_ArrayRemove {
    public static void main(String[] args) {
        int[] values = {4, 7, 2, 7, 9, 7, 1};
        int target = 7;

        System.out.println("出現次數 : " + countOccurrences(values, target));
        System.out.println("最後索引 : " + findLastIndex(values, target));

        int[] result = removeAll(values, target);
        System.out.print("移除後 : ");
        printArray(result);
        System.out.print("原始陣列 : ");
        printArray(values);
    }

    // 1. 計算指定值出現的次數
    public static int countOccurrences(int[] data, int target) {
        int count = 0;
        for (int value : data) {
            if (value == target) {
                count++;
            }
        }
        return count;
    }

    // 2. 尋找指定值最後一次出現的索引
    public static int findLastIndex(int[] data, int target) {
        // 從陣列尾端往前找
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1; 
    }

    // 3. 建立並回傳不包含指定值的新陣列
    public static int[] removeAll(int[] data, int target) {
       
        int occurrences = countOccurrences(data, target);
        
        int[] newArray = new int[data.length - occurrences];
        
        int newIndex = 0; // 
        for (int value : data) {
            // 
            if (value != target) {
                newArray[newIndex] = value;
                newIndex++;
            }
        }
        
        return newArray;
    }

    private static void printArray(int[] data) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}