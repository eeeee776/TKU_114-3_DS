public class ReverseTriangleStars {
    public static void main(String[] args) {
        // 外層迴圈控制列數，從 5 開始遞減到 1
        for (int row = 5; row >= 1; row--) {
            
            // 內層迴圈根據目前的 row 變數，決定該列要印出幾個星號
            for (int col = 1; col <= row; col++) {
                System.out.print("*");
            }
            
            // 內層迴圈跑完（該列星號印完）後，進行換行
            System.out.println();
        }
    }
}