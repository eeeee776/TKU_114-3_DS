import java.util.Scanner;

public class ProductArraySystem {

    // 用於最後結束時顯示的操作摘要統計
    static int totalItemsSold = 0;
    static int totalItemsRestocked = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 初始化商品對應陣列
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        boolean isRunning = true;

        System.out.println("歡迎使用商品陣列管理系統");

        while (isRunning) {
            displayMenu();
            System.out.print("\n請選擇功能 (1-7): ");
            int choice = sc.nextInt();

            System.out.println("------------------------------------------------");
            switch (choice) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProduct(sc, names, prices, stocks);
                    break;
                case 3:
                    purchaseProduct(sc, names, prices, stocks);
                    break;
                case 4:
                    replenishStock(sc, names, prices, stocks);
                    break;
                case 5:
                    displayLowStock(names, stocks);
                    break;
                case 6:
                    displayTotalInventoryValue(names, prices, stocks);
                    break;
                case 7:
                    displaySummaryAndExit();
                    isRunning = false;
                    break;
                default:
                    System.out.println("錯誤：無效的選項，請輸入 1 到 7 之間的數字。");
            }
        }
        sc.close();
    }

    // 自訂方法 1：顯示主選單
    public static void displayMenu() {
        System.out.println("\n================ 選單功能 ================");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (庫存 < 10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束並顯示操作摘要");
        System.out.println("==========================================");
    }

    // 自訂方法 2：顯示全部商品
    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("【全部商品列表】");
        System.out.printf("%-5s %-15s %-10s %-10s\n", "編號", "商品名稱", "價格", "庫存量");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-6d %-15s $%-9d %-10d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    // 自訂方法 3：取得並驗證正確的商品索引 (陣列防呆與編號轉換)
    public static int getValidProductIndex(Scanner sc, int maxLen) {
        while (true) {
            System.out.print("請輸入商品編號 (1 到 " + maxLen + "): ");
            int id = sc.nextInt();
            if (id >= 1 && id <= maxLen) {
                return id - 1; // 將編號轉換為陣列索引
            }
            System.out.println("錯誤：找不到該商品編號，請重新輸入。");
        }
    }

    // 自訂方法 4：依商品編號查詢
    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.println("【商品查詢】");
        int index = getValidProductIndex(sc, names.length);
        System.out.println("查詢結果 -> 名稱: " + names[index] + " | 價格: $" + prices[index] + " | 庫存: " + stocks[index]);
    }

    // 自訂方法 5：購買商品並扣除庫存 (包含數量與庫存驗證)
    public static void purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.println("【購買商品】");
        int index = getValidProductIndex(sc, names.length);
        
        System.out.println("您選擇了: " + names[index] + " (目前庫存: " + stocks[index] + ", 單價: $" + prices[index] + ")");
        
        if (stocks[index] == 0) {
            System.out.println("抱歉，該商品目前已售完！");
            return;
        }

        while (true) {
            System.out.print("請輸入購買數量: ");
            int qty = sc.nextInt();

            if (qty <= 0) {
                System.out.println("錯誤：購買數量必須大於 0！");
            } else if (qty > stocks[index]) {
                System.out.println("錯誤：購買數量不能超過現有庫存 (" + stocks[index] + ")！");
            } else {
                stocks[index] -= qty;
                totalItemsSold += qty;
                int cost = qty * prices[index];
                System.out.println("購買成功！總共花費: $" + cost + "。剩餘庫存: " + stocks[index]);
                break;
            }
        }
    }

    // 自訂方法 6：補充商品庫存 (包含數量驗證)
    public static void replenishStock(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.println("【補充庫存】");
        int index = getValidProductIndex(sc, names.length);
        
        System.out.println("您選擇了: " + names[index] + " (目前庫存: " + stocks[index] + ")");
        
        while (true) {
            System.out.print("請輸入補充數量: ");
            int qty = sc.nextInt();

            if (qty <= 0) {
                System.out.println("錯誤：補貨數量必須大於 0！");
            } else {
                stocks[index] += qty;
                totalItemsRestocked += qty;
                System.out.println("補貨成功！" + names[index] + " 最新庫存為: " + stocks[index]);
                break;
            }
        }
    }

    // 自訂方法 7：顯示低庫存商品
    public static void displayLowStock(String[] names, int[] stocks) {
        System.out.println("【低庫存商品警報 (庫存 < 10)】");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println("警報 -> 商品: " + names[i] + " | 庫存僅剩: " + stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前所有商品庫存皆十分充足。");
        }
    }

    // 自訂方法 8：顯示全部庫存總價值
    public static void displayTotalInventoryValue(String[] names, int[] prices, int[] stocks) {
        System.out.println("【庫存總價值統計】");
        long totalValue = 0; // 使用 long 避免數值過大溢位
        for (int i = 0; i < names.length; i++) {
            int itemValue = prices[i] * stocks[i];
            totalValue += itemValue;
        }
        System.out.println("系統內所有商品的庫存總價值為: $" + totalValue);
    }

    // 自訂方法 9：結束並顯示操作摘要
    public static void displaySummaryAndExit() {
        System.out.println("【系統結束與操作摘要】");
        System.out.println("本次系統執行期間：");
        System.out.println(" - 總共賣出商品件數： " + totalItemsSold + " 件");
        System.out.println(" - 總共補貨商品件數： " + totalItemsRestocked + " 件");
        System.out.println("感謝您的使用，系統已成功關閉。");
    }
}