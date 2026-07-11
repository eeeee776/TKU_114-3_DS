public class NumberGrid {
    public static void main(String[] args) {
        // 外層迴圈控制列數 (共 3 列)
        for (int i = 1; i <= 3; i++) {
            
            // 內層迴圈控制該列要印出的數字 (1 到 5)
            for (int j = 1; j <= 5; j++) {
                // 使用 print 讓數字印在同一行，並加上空白區隔
                System.out.print(j + " ");
            }
            
            // 內層迴圈跑完一輪 (印完 1 到 5) 後，進行換行
            System.out.println();
        }
    }
}