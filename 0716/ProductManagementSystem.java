import java.util.Scanner;

public class ProductManagementSystem {
    
    // 主程式入口
    public static void main(String[] args) {
        Product[] products = new Product[10];
        initializeProducts(products);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("歡迎使用物件導向商品管理系統");

        while (running) {
            displayMenu();
            System.out.print("請選擇功能 (1-9): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗換行符號

            switch (choice) {
                case 1:
                    displayAllProducts(products);
                    break;
                case 2:
                    processSearch(products, scanner);
                    break;
                case 3:
                    processAddProduct(products, scanner);
                    break;
                case 4:
                    processSell(products, scanner);
                    break;
                case 5:
                    processRestock(products, scanner);
                    break;
                case 6:
                    processModifyPrice(products, scanner);
                    break;
                case 7:
                    displayLowStockProducts(products);
                    break;
                case 8:
                    displayTotalInventoryValue(products);
                    break;
                case 9:
                    running = false;
                    System.out.println("系統結束，感謝您的使用！操作摘要已記錄。");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }
        scanner.close();
    }

    // 輔助方法 1：初始化商品陣列
    public static void initializeProducts(Product[] products) {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("Headset", 1200, 8);
        products[4] = new Product("Webcam", 950, 15);
    }

    // 輔助方法 2：顯示選單
    public static void displayMenu() {
        System.out.println("\n--- 選單功能 ---");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
    }

    // 輔助方法 3：顯示全部商品
    public static void displayAllProducts(Product[] products) {
        System.out.println("\n--- 顯示全部商品 ---");
        boolean hasProduct = false;
        for (Product p : products) {
            if (p != null) {
                System.out.println(p);
                hasProduct = true;
            }
        }
        if (!hasProduct) System.out.println("目前沒有任何商品。");
    }

    // 輔助方法 4：核心搜尋邏輯 (回傳物件或 null)
    public static Product findProduct(Product[] products, String name) {
        if (name == null) return null;
        for (Product p : products) {
            if (p != null && p.getName().equalsIgnoreCase(name.trim())) {
                return p;
            }
        }
        return null;
    }

    // 輔助方法 5：處理搜尋互動
    public static void processSearch(Product[] products, Scanner scanner) {
        System.out.print("請輸入要搜尋的商品名稱: ");
        String name = scanner.nextLine();
        Product found = findProduct(products, name);
        if (found != null) {
            System.out.println("搜尋結果: " + found);
        } else {
            System.out.println("找不到符合名稱的商品。");
        }
    }

    // 輔助方法 6：處理新增商品
    public static void processAddProduct(Product[] products, Scanner scanner) {
        System.out.print("請輸入新商品名稱: ");
        String name = scanner.nextLine().trim();
        
        if (findProduct(products, name) != null) {
            System.out.println("錯誤：不可新增重複名稱的商品。");
            return;
        }

        int emptyIndex = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                emptyIndex = i;
                break;
            }
        }

        if (emptyIndex == -1) {
            System.out.println("錯誤：陣列已滿，無法新增商品。");
            return;
        }

        System.out.print("請輸入價格: ");
        int price = scanner.nextInt();
        System.out.print("請輸入初始庫存: ");
        int stock = scanner.nextInt();
        
        products[emptyIndex] = new Product(name, price, stock);
        System.out.println("商品新增成功！");
    }

    // 輔助方法 7：處理出售商品
    public static void processSell(Product[] products, Scanner scanner) {
        System.out.print("請輸入要出售的商品名稱: ");
        String name = scanner.nextLine();
        Product found = findProduct(products, name);
        if (found != null) {
            System.out.print("請輸入出售數量: ");
            int qty = scanner.nextInt();
            if (found.sell(qty)) {
                System.out.println("出售成功！目前庫存剩餘: " + found.getStock());
            } else {
                System.out.println("出售失敗 (數量錯誤或庫存不足)。");
            }
        } else {
            System.out.println("找不到該商品。");
        }
    }

    // 輔助方法 8：處理補充庫存
    public static void processRestock(Product[] products, Scanner scanner) {
        System.out.print("請輸入要補充庫存的商品名稱: ");
        String name = scanner.nextLine();
        Product found = findProduct(products, name);
        if (found != null) {
            System.out.print("請輸入補充數量: ");
            int qty = scanner.nextInt();
            if (found.restock(qty)) {
                System.out.println("補貨成功！目前庫存為: " + found.getStock());
            } else {
                System.out.println("補貨失敗 (數量必須大於 0)。");
            }
        } else {
            System.out.println("找不到該商品。");
        }
    }

    // 輔助方法 9：處理修改價格
    public static void processModifyPrice(Product[] products, Scanner scanner) {
        System.out.print("請輸入要修改價格的商品名稱: ");
        String name = scanner.nextLine();
        Product found = findProduct(products, name);
        if (found != null) {
            System.out.print("請輸入新價格: ");
            int newPrice = scanner.nextInt();
            if (found.setPrice(newPrice)) {
                System.out.println("價格修改成功！");
            } else {
                System.out.println("修改失敗 (價格必須大於 0)。");
            }
        } else {
            System.out.println("找不到該商品。");
        }
    }

    // 輔助方法 10：顯示低庫存商品
    public static void displayLowStockProducts(Product[] products) {
        System.out.println("\n--- 低庫存商品清單 (<10) ---");
        boolean foundAny = false;
        for (Product p : products) {
            if (p != null && p.isLowStock()) {
                System.out.println(p);
                foundAny = true;
            }
        }
        if (!foundAny) System.out.println("目前沒有低庫存商品。");
    }

    // 輔助方法 11：計算並顯示總價值
    public static void displayTotalInventoryValue(Product[] products) {
        long total = 0;
        for (Product p : products) {
            if (p != null) {
                total += p.getInventoryValue();
            }
        }
        System.out.println("目前全部庫存總價值為: " + total);
    }
}