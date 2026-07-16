import java.util.Scanner;
import java.util.Arrays;

public class ProductDataManager {

    public static void main(String[] args) {
        // 程式內先準備的陣列
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        // 2. 分別存入商品名稱、價格與庫存陣列
        String[] names = new String[records.length];
        double[] prices = new double[records.length];
        int[] stocks = new int[records.length];

        // 1. 使用 split() 解析每筆資料
        for (int i = 0; i < records.length; i++) {
            String[] parts = records[i].split(",");
            names[i] = parts[0].trim();
            prices[i] = Double.parseDouble(parts[1].trim());
            stocks[i] = Integer.parseInt(parts[2].trim());
        }

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== 商品文字資料管理器 ===");
            System.out.println("1. 顯示商品表格");
            System.out.println("2. 搜尋商品 (支援完整與部分名稱)");
            System.out.println("3. 顯示低庫存商品");
            System.out.println("4. 顯示庫存總價值");
            System.out.println("5. 輸入新文字資料並驗證");
            System.out.println("6. 結束");
            System.out.print("請選擇功能 (1-6): ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // 3. 顯示解析後的商品表格
                    System.out.println("\n--- 商品表格 ---");
                    System.out.printf("%-15s | %-8s | %-8s\n", "商品名稱", "價格", "庫存");
                    System.out.println("----------------------------------------");
                    for (int i = 0; i < names.length; i++) {
                        System.out.printf("%-15s | %-8.0f | %-8d\n", names[i], prices[i], stocks[i]);
                    }
                    break;

                case "2":
                    // 4. 支援完整名稱與部分名稱搜尋
                    System.out.print("\n請輸入搜尋關鍵字: ");
                    String keyword = scanner.nextLine().trim();
                    boolean found = false;
                    System.out.println("--- 搜尋結果 ---");
                    for (int i = 0; i < names.length; i++) {
                        // 忽略大小寫的完整比對 或 包含字串的部分比對
                        if (names[i].equalsIgnoreCase(keyword) || 
                            names[i].toLowerCase().contains(keyword.toLowerCase())) {
                            System.out.printf("名稱: %-15s | 價格: %.0f | 庫存: %d\n", names[i], prices[i], stocks[i]);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("找不到包含此關鍵字的商品。");
                    }
                    break;

                case "3":
                    // 5. 顯示低庫存商品 (設定低庫存標準為小於 10)
                    System.out.println("\n--- 低庫存商品 (庫存 < 10) ---");
                    boolean hasLowStock = false;
                    for (int i = 0; i < names.length; i++) {
                        if (stocks[i] < 10) {
                            System.out.printf("名稱: %-15s | 目前庫存: %d\n", names[i], stocks[i]);
                            hasLowStock = true;
                        }
                    }
                    if (!hasLowStock) {
                        System.out.println("目前沒有低於安全庫存的商品。");
                    }
                    break;

                case "4":
                    // 6. 顯示庫存總價值
                    double totalValue = 0;
                    for (int i = 0; i < names.length; i++) {
                        totalValue += prices[i] * stocks[i];
                    }
                    System.out.printf("\n目前所有商品的庫存總價值: %.0f\n", totalValue);
                    break;

                case "5":
                    // 7. 允許輸入一筆新文字資料並驗證格式
                    System.out.print("\n請輸入新商品資料 (格式: 名稱,價格,庫存): ");
                    String newData = scanner.nextLine();
                    
                    String[] newParts = newData.split(",");
                    
                    // 8. 格式錯誤或數字轉換錯誤時顯示原因，程式不能中止
                    if (newParts.length != 3) {
                        System.out.println("【格式錯誤】輸入的資料未包含3個部分！(請確保以逗號分隔名稱、價格、庫存)");
                    } else {
                        try {
                            String newName = newParts[0].trim();
                            // 嘗試解析數字，若失敗會拋出 NumberFormatException
                            double newPrice = Double.parseDouble(newParts[1].trim());
                            int newStock = Integer.parseInt(newParts[2].trim());
                            
                            System.out.println("【驗證成功】資料格式正確！");
                            
                            // 將新資料擴充加入陣列中
                            names = Arrays.copyOf(names, names.length + 1);
                            prices = Arrays.copyOf(prices, prices.length + 1);
                            stocks = Arrays.copyOf(stocks, stocks.length + 1);
                            
                            names[names.length - 1] = newName;
                            prices[prices.length - 1] = newPrice;
                            stocks[stocks.length - 1] = newStock;
                            
                            System.out.println("已成功將 " + newName + " 加入商品清單！");
                            
                        } catch (NumberFormatException e) {
                            System.out.println("【數字轉換錯誤】價格或庫存欄位包含非數字字元！");
                            System.out.println("系統詳細錯誤訊息: " + e.getMessage());
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
 * 測試案例紀錄 (至少 8 組)
 * ==========================================
 * 
 * [測試案例 1 - 正常流程 (功能 1)] 
 * 目的：測試系統初始資料解析與顯示。
 * 操作：選擇 1
 * 預期結果：成功印出 5 筆初始商品 (Keyboard, Mouse, Monitor, USB Cable, Headset) 及其價格和庫存。
 * 
 * [測試案例 2 - 正常搜尋 (功能 2 - 完整名稱)]
 * 目的：測試是否能精確搜尋到商品。
 * 操作：選擇 2，輸入 "Mouse"
 * 預期結果：顯示 Mouse 的資料。
 * 
 * [測試案例 3 - 正常搜尋 (功能 2 - 部分名稱/忽略大小寫)]
 * 目的：測試部分關鍵字與大小寫忽略功能。
 * 操作：選擇 2，輸入 "o"
 * 預期結果：顯示 Keyboard, Mouse, Monitor 三筆包含 'o' 的資料。
 * 
 * [測試案例 4 - 找不到搜尋 (功能 2)]
 * 目的：測試搜尋不存在的商品。
 * 操作：選擇 2，輸入 "Apple"
 * 預期結果：顯示「找不到包含此關鍵字的商品。」
 * 
 * [測試案例 5 - 低庫存顯示 (功能 3)]
 * 目的：驗證低於 10 的庫存商品篩選邏輯。
 * 操作：選擇 3
 * 預期結果：正確篩選出 Monitor (5) 與 Headset (8)。
 * 
 * [測試案例 6 - 總價值計算 (功能 4)]
 * 目的：驗證陣列元素的相乘與加總邏輯。
 * 操作：選擇 4
 * 預期結果：(890*12)+(490*20)+(5200*5)+(250*30)+(1290*8) = 10680 + 9800 + 26000 + 7500 + 10320 = 64300。印出總值 64300。
 * 
 * [測試案例 7 - 格式錯誤防呆 (功能 5)]
 * 目的：測試輸入逗號不足的字串，驗證程式不中斷並給出錯誤提示。
 * 操作：選擇 5，輸入 "Webcam,1500"
 * 預期結果：顯示【格式錯誤】，程式不崩潰，回到主選單。
 * 
 * [測試案例 8 - 數字轉換錯誤防呆 (功能 5)]
 * 目的：測試將英文字母混入數字欄位，驗證 try-catch 機制。
 * 操作：選擇 5，輸入 "Webcam,1500,ABC"
 * 預期結果：顯示【數字轉換錯誤】，程式攔截 NumberFormatException，不崩潰，回到主選單。
 * 
 * [測試案例 9 - 正常新增並擴充陣列 (功能 5)]
 * 目的：測試完整正確的新增流程。
 * 操作：選擇 5，輸入 "Webcam,1500,15"，然後選擇 1 查看表格。
 * 預期結果：顯示【驗證成功】，並且表格的最後一行多出 Webcam 的資料，總商品數變為 6。
 */