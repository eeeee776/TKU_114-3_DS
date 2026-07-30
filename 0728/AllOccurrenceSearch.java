public class AllOccurrenceSearch {
    public static void main(String[] args) {
        int[] values = {45, 12, 89, 12, 33, 12, 7};
        int target = 12;
        
        int checks = 0;
        int count = 0;
        
        System.out.print("目標值 " + target + " 的索引位置：");
        for (int i = 0; i < values.length; i++) {
            checks++;
            if (values[i] == target) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println();
        
        if (count == 0) {
            System.out.println("找不到目標數值。");
        } else {
            System.out.println("出現次數：" + count);
        }
        System.out.println("實際比較次數：" + checks);
    }
}