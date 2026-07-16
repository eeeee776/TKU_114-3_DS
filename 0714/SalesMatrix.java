import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 建立 3 列 4 欄的二維陣列 (3項商品, 4天)
        int[][] sales = new int[3][4];

        // 1. 輸入每一格銷售量 (含 >= 0 驗證)
        System.out.println("請輸入銷售資料：");
        inputSales(sc, sales);

        // 2. 以表格形式顯示完整資料
        System.out.println("\n--- 銷售矩陣報表 ---");
        displayTable(sales);

        // 3, 4, 5. 計算並顯示統計資料
        System.out.println("\n--- 銷售統計 ---");
        displayStatistics(sales);

        sc.close();
    }

    /**
     * 方法：處理輸入並驗證數值不得小於 0
     */
    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.printf("請輸入 商品 %d 在 第 %d 天的銷售量：", (i + 1), (j + 1));
                    int amount = sc.nextInt();
                    
                    if (amount >= 0) {
                        sales[i][j] = amount;
                        break; // 輸入正確，跳出驗證迴圈
                    } else {
                        System.out.println("錯誤！銷售量不得小於 0，請重新輸入。");
                    }
                }
            }
        }
    }

    /**
     * 方法：以表格形式顯示完整資料
     */
    public static void displayTable(int[][] sales) {
        // 印出表頭 (天數)
        System.out.print("       \t");
        for (int j = 0; j < sales[0].length; j++) {
            System.out.printf("第%d天\t", (j + 1));
        }
        System.out.println();

        // 印出內容 (商品及銷售量)
        for (int i = 0; i < sales.length; i++) {
            System.out.printf("商品 %d:\t", (i + 1));
            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%d\t", sales[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 方法：計算並顯示各項統計數據
     */
    public static void displayStatistics(int[][] sales) {
        int numProducts = sales.length;
        int numDays = sales[0].length;
        
        int[] productTotals = new int[numProducts]; // 儲存每項商品的總和
        int[] dailyTotals = new int[numDays];       // 儲存每天的總和
        
        // 走訪陣列，同時計算列總和與欄總和
        for (int i = 0; i < numProducts; i++) {
            for (int j = 0; j < numDays; j++) {
                productTotals[i] += sales[i][j];
                dailyTotals[j] += sales[i][j];
            }
        }
        
        // 變數用於記錄最高銷售量及對應的商品索引
        int maxTotal = -1;
        int bestProductIndex = -1;
        
        // 3. 顯示每項商品的銷售總量
        for (int i = 0; i < numProducts; i++) {
            System.out.printf("商品 %d 銷售總量: %d\n", (i + 1), productTotals[i]);
            
            // 5. 找出總銷售量最高的商品
            if (productTotals[i] > maxTotal) {
                maxTotal = productTotals[i];
                bestProductIndex = i;
            }
        }
        
        System.out.println("-------------------------");
        
        // 4. 顯示每天全部商品的銷售總量
        for (int j = 0; j < numDays; j++) {
            System.out.printf("第 %d 天總銷售量: %d\n", (j + 1), dailyTotals[j]);
        }
        
        System.out.println("-------------------------");
        
        // 顯示最高銷售量商品結果
        System.out.printf("總銷售量最高的商品是：商品 %d (總銷量為 %d)\n", (bestProductIndex + 1), maxTotal);
    }
}