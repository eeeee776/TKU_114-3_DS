import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 購物車系統 ===");
            System.out.println("1. 加入商品");
            System.out.println("2. 修改數量");
            System.out.println("3. 移除商品");
            System.out.println("4. 檢視購物車與總額");
            System.out.println("0. 結帳離開");
            System.out.print("請選擇功能：");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("錯誤：請輸入有效的數字選項！");
                continue;
            }

            if (choice == 0) {
                System.out.println("系統已離開，感謝您的光臨。");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("請輸入商品代碼：");
                    String code = scanner.nextLine().trim();
                    
                    CartItem existingItem = findItemByCode(cart, code);
                    
                    // 關鍵邏輯：檢查物件是否已在購物車中
                    if (existingItem != null) {
                        System.out.print("購物車已存在此商品，請輸入要【追加】的數量：");
                        int addQty = getValidInteger(scanner);
                        if (addQty > 0) {
                            existingItem.addQuantity(addQty);
                            System.out.println("數量追加成功！目前總數量：" + existingItem.getQuantity());
                        } else {
                            System.out.println("錯誤：追加數量必須大於 0。");
                        }
                    } else {
                        // 新商品加入流程
                        System.out.print("請輸入商品名稱：");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("錯誤：名稱不可為空白！");
                            break;
                        }
                        
                        System.out.print("請輸入單價：");
                        int price = getValidInteger(scanner);
                        System.out.print("請輸入數量：");
                        int qty = getValidInteger(scanner);
                        
                        if (price >= 0 && qty > 0) {
                            cart.add(new CartItem(code, name, price, qty));
                            System.out.println("商品已成功加入購物車！");
                        } else {
                            System.out.println("錯誤：單價不可為負，且數量必須大於 0。");
                        }
                    }
                    break;

                case 2:
                    System.out.print("請輸入要修改數量的商品代碼：");
                    String updateCode = scanner.nextLine().trim();
                    CartItem targetToUpdate = findItemByCode(cart, updateCode);
                    
                    if (targetToUpdate != null) {
                        System.out.print("請輸入新的數量：");
                        int newQty = getValidInteger(scanner);
                        
                        // 呼叫物件自身的 setQuantity，由物件判斷是否合法
                        if (targetToUpdate.setQuantity(newQty)) {
                            System.out.println("數量修改成功！");
                        } else {
                            System.out.println("錯誤：修改失敗，數量必須大於 0。");
                        }
                    } else {
                        System.out.println("錯誤：購物車內找不到該商品。");
                    }
                    break;

                case 3:
                    System.out.print("請輸入要移除的商品代碼：");
                    String removeCode = scanner.nextLine().trim();
                    if (removeItem(cart, removeCode)) {
                        System.out.println("商品已從購物車中移除。");
                    } else {
                        System.out.println("錯誤：購物車內找不到該商品。");
                    }
                    break;

                case 4:
                    displayCart(cart);
                    break;

                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
        }
        scanner.close();
    }

    // ==========================================
    // 自訂 Method 區
    // ==========================================

    /**
     * 核心搜尋方法：尋找購物車內是否已有該商品
     */
    public static CartItem findItemByCode(ArrayList<CartItem> cart, String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 從購物車中移除商品
     */
    public static boolean removeItem(ArrayList<CartItem> cart, String code) {
        CartItem target = findItemByCode(cart, code);
        if (target != null) {
            return cart.remove(target);
        }
        return false;
    }

    /**
     * 顯示購物車內容並計算總額
     */
    public static void displayCart(ArrayList<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("購物車目前是空的。");
            return;
        }
        
        System.out.println("--- 購物車內容 ---");
        int total = 0;
        for (CartItem item : cart) {
            System.out.println(item);
            total += item.getSubtotal(); // 直接拿物件的小計來加總
        }
        System.out.println("==================");
        System.out.println("結帳總金額：$" + total);
    }

    /**
     * 輔助方法：讀取整數並防護非數字輸入
     */
    private static int getValidInteger(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // 發生格式錯誤時回傳 -1，讓呼叫端當作無效數值處理
        }
    }
}