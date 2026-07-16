import java.util.Scanner;

public class ProductSearchSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 模擬 0714 的商品資料陣列 (請依據實際情況替換)
        String[] productNames = {
            "Apple iPhone 15 Pro", 
            "Samsung Galaxy S24 Ultra", 
            "Google Pixel 8 Pro", 
            "Sony Xperia 1 V", 
            "asus Zenfone 10", 
            "apple ipad air 5", 
            "MacBook Pro 14 M3"
        };
        double[] productPrices = {36900, 43900, 32900, 36990, 19990, 19900, 64900};
        int[] productStocks = {50, 30, 20, 10, 15, 40, 5};

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== 商品名稱搜尋系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋，忽略大小寫與前後空白");
            System.out.println("3. 部分名稱搜尋，可顯示多筆結果");
            System.out.println("4. 顯示名稱最長的商品");
            System.out.println("5. 顯示商品名稱與搜尋關鍵字第一次出現的位置");
            System.out.println("6. 結束");
            System.out.print("請選擇功能 (1-6): ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- 全部商品 ---");
                    for (int i = 0; i < productNames.length; i++) {
                        System.out.printf("編號 %d | 名稱: %-25s | 價格: %.0f | 庫存: %d\n", 
                                          (i + 1), productNames[i], productPrices[i], productStocks[i]);
                    }
                    break;

                case "2":
                    System.out.print("\n請輸入完整商品名稱: ");
                    String exactSearch = scanner.nextLine().trim();
                    boolean exactFound = false;
                    for (int i = 0; i < productNames.length; i++) {
                        // 忽略前後空白並忽略大小寫比對
                        if (productNames[i].trim().equalsIgnoreCase(exactSearch)) {
                            System.out.println("找到商品！");
                            System.out.printf("名稱: %s | 價格: %.0f | 庫存: %d\n", 
                                              productNames[i], productPrices[i], productStocks[i]);
                            exactFound = true;
                            break; // 假設完整名稱只有一個
                        }
                    }
                    if (!exactFound) {
                        System.out.println("找不到完全符合該名稱的商品。");
                    }
                    break;

                case "3":
                    System.out.print("\n請輸入部分商品名稱: ");
                    String partialSearch = scanner.nextLine().toLowerCase();
                    boolean partialFound = false;
                    System.out.println("--- 搜尋結果 ---");
                    for (int i = 0; i < productNames.length; i++) {
                        // 轉小寫以進行不分大小寫的部分搜尋
                        if (productNames[i].toLowerCase().contains(partialSearch)) {
                            System.out.printf("名稱: %-25s | 價格: %.0f | 庫存: %d\n", 
                                              productNames[i], productPrices[i], productStocks[i]);
                            partialFound = true;
                        }
                    }
                    if (!partialFound) {
                        System.out.println("找不到包含此關鍵字的商品。");
                    }
                    break;

                case "4":
                    String longestName = "";
                    int longestIndex = -1;
                    for (int i = 0; i < productNames.length; i++) {
                        if (productNames[i].length() > longestName.length()) {
                            longestName = productNames[i];
                            longestIndex = i;
                        }
                    }
                    System.out.println("\n--- 名稱最長的商品 ---");
                    if (longestIndex != -1) {
                        System.out.printf("名稱: %s (長度: %d) | 價格: %.0f | 庫存: %d\n", 
                                          productNames[longestIndex], longestName.length(), productPrices[longestIndex], productStocks[longestIndex]);
                    }
                    break;

                case "5":
                    System.out.print("\n請輸入搜尋關鍵字: ");
                    String keyword = scanner.nextLine().toLowerCase();
                    System.out.println("--- 關鍵字出現位置 ---");
                    for (String name : productNames) {
                        // 為了方便比對，將商品名稱轉小寫後尋找索引
                        int index = name.toLowerCase().indexOf(keyword);
                        if (index != -1) {
                            System.out.printf("商品: %-25s | 關鍵字 [%s] 第一次出現索引: %d\n", name, keyword, index);
                        } else {
                            System.out.printf("商品: %-25s | 未找到關鍵字\n", name);
                        }
                    }
                    break;

                case "6":
                    System.out.println("系統結束。");
                    isRunning = false;
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }
        scanner.close();
    }
}

/* 
 * ==========================================
 * 測試案例紀錄 (至少 6 組)
 * ==========================================
 * 
 * [測試案例 1 - 正常邊界測試 (功能 2)] 
 * 目的：測試「忽略前後空白與大小寫」的完整搜尋功能。
 * 操作：選擇 2，輸入 "   apple IPHONE 15 pRo   "
 * 預期結果：成功找到 "Apple iPhone 15 Pro"，並顯示正確的價格與庫存。
 * 
 * [測試案例 2 - 錯誤值測試 (功能 2)]
 * 目的：測試輸入不存在的商品名稱。
 * 操作：選擇 2，輸入 "Nokia 3310"
 * 預期結果：顯示「找不到完全符合該名稱的商品。」
 * 
 * [測試案例 3 - 多重結果測試 (功能 3)]
 * 目的：測試部分名稱搜尋是否能回傳多筆結果。
 * 操作：選擇 3，輸入 "pro"
 * 預期結果：顯示 "Apple iPhone 15 Pro", "Google Pixel 8 Pro", "MacBook Pro 14 M3" 共三筆資料。
 * 
 * [測試案例 4 - 正常值測試 (功能 4)]
 * 目的：測試找出最長商品名稱的功能。
 * 操作：選擇 4
 * 預期結果：顯示 "Samsung Galaxy S24 Ultra"，並印出其長度為 24 及其價格與庫存。
 * 
 * [測試案例 5 - 正常值測試 (功能 5)]
 * 目的：測試關鍵字 indexOf 功能。
 * 操作：選擇 5，輸入 "e"
 * 預期結果：各商品若包含 "e" 則印出第一個 "e" 出現的索引值。例如 "Apple iPhone 15 Pro" 印出索引 4，"asus Zenfone 10" 印出索引 6 (zEnfonE 的第一個 e)。
 * 
 * [測試案例 6 - 錯誤值測試 (功能選單防呆)]
 * 目的：測試輸入非 1~6 的選單選項。
 * 操作：在主畫面輸入 "7" 或 "abc"
 * 預期結果：顯示「無效的選項，請重新輸入。」並重新顯示主選單。
 */