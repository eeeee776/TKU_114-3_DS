import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 建立儲存 Equipment 物件的動態集合
        ArrayList<Equipment> equipments = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 設備管理系統 ===");
            System.out.println("1. 新增設備");
            System.out.println("2. 依代碼搜尋");
            System.out.println("3. 借出設備");
            System.out.println("4. 歸還設備");
            System.out.println("5. 列出可借設備");
            System.out.println("0. 離開");
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
                System.out.println("系統已離開。");
                break;
            }

            switch (choice) {
                case 1: // Create
                    System.out.print("請輸入設備代碼：");
                    String code = scanner.nextLine().trim();
                    if (code.isEmpty()) {
                        System.out.println("錯誤：代碼不可為空白！");
                        break;
                    }
                    
                    // 檢查代碼是否重複
                    if (findByCode(equipments, code) != null) {
                        System.out.println("錯誤：此代碼已存在，無法重複新增！");
                    } else {
                        System.out.print("請輸入設備名稱：");
                        String name = scanner.nextLine().trim();
                        equipments.add(new Equipment(code, name));
                        System.out.println("設備新增成功！");
                    }
                    break;

                case 2: // Read: 單筆
                    System.out.print("請輸入搜尋的設備代碼：");
                    String searchCode = scanner.nextLine().trim();
                    Equipment foundEq = findByCode(equipments, searchCode);
                    
                    if (foundEq != null) {
                        System.out.println("搜尋結果：" + foundEq);
                    } else {
                        System.out.println("找不到代碼為「" + searchCode + "」的設備。");
                    }
                    break;

                case 3: // Update: 修改狀態為借出
                    System.out.print("請輸入要借出的設備代碼：");
                    String borrowCode = scanner.nextLine().trim();
                    Equipment eqToBorrow = findByCode(equipments, borrowCode);
                    
                    if (eqToBorrow == null) {
                        System.out.println("錯誤：找不到該設備。");
                    } else {
                        // 呼叫物件自身的方法，讓物件決定能否借出
                        if (eqToBorrow.borrowItem()) {
                            System.out.println("借出成功！");
                        } else {
                            System.out.println("借出失敗：該設備目前已被借出。");
                        }
                    }
                    break;

                case 4: // Update: 修改狀態為歸還
                    System.out.print("請輸入要歸還的設備代碼：");
                    String returnCode = scanner.nextLine().trim();
                    Equipment eqToReturn = findByCode(equipments, returnCode);
                    
                    if (eqToReturn == null) {
                        System.out.println("錯誤：找不到該設備。");
                    } else {
                        if (eqToReturn.returnItem()) {
                            System.out.println("歸還成功！");
                        } else {
                            System.out.println("歸還失敗：該設備目前已在系統中，並未被借出。");
                        }
                    }
                    break;

                case 5: // Read: 條件列出
                    System.out.println("--- 可借用設備清單 ---");
                    boolean hasAvailable = false;
                    
                    for (Equipment eq : equipments) {
                        if (eq.isAvailable()) { // 只印出狀態為 true 的設備
                            System.out.println(eq);
                            hasAvailable = true;
                        }
                    }
                    
                    if (!hasAvailable) {
                        System.out.println("目前沒有任何可借用的設備。");
                    }
                    break;

                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
        }
        scanner.close();
    }

    /**
     * 核心方法：透過唯一的代碼來尋找設備物件
     * 
     * @param equipments 設備集合
     * @param code 要尋找的代碼
     * @return 找到回傳該 Equipment 物件，找不到回傳 null
     */
    public static Equipment findByCode(ArrayList<Equipment> equipments, String code) {
        for (Equipment eq : equipments) {
            if (eq.getCode().equalsIgnoreCase(code)) {
                return eq; // 回傳物件參考
            }
        }
        return null;
    }
}