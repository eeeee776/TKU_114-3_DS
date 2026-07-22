import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 使用 String 泛型建立姓名名單
        ArrayList<String> names = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 名單管理系統 ===");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部");
            System.out.println("0. 離開");
            System.out.print("請選擇功能：");

            // 讀取整行並轉換，避免 nextInt() 殘留換行字元的問題
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
                case 1: // Create: 新增
                    System.out.print("請輸入要新增的姓名：");
                    String newName = scanner.nextLine().trim();
                    if (newName.isEmpty()) {
                        System.out.println("錯誤：姓名不可為空白！");
                    } else {
                        names.add(newName);
                        System.out.println("新增成功！");
                    }
                    break;

                case 2: // Read: 搜尋
                    System.out.print("請輸入要搜尋的姓名：");
                    String searchTarget = scanner.nextLine().trim();
                    int searchIndex = findNameIndex(names, searchTarget);
                    
                    if (searchIndex != -1) {
                        System.out.println("找到了！「" + names.get(searchIndex) + "」位於索引 " + searchIndex);
                    } else {
                        System.out.println("找不到姓名：「" + searchTarget + "」");
                    }
                    break;

                case 3: // Update: 修改
                    System.out.print("請輸入要修改的【原姓名】：");
                    String oldName = scanner.nextLine().trim();
                    int updateIndex = findNameIndex(names, oldName);
                    
                    if (updateIndex != -1) {
                        System.out.print("請輸入【新姓名】：");
                        String replaceName = scanner.nextLine().trim();
                        if (replaceName.isEmpty()) {
                            System.out.println("錯誤：新姓名不可為空白！");
                        } else {
                            names.set(updateIndex, replaceName);
                            System.out.println("修改成功！");
                        }
                    } else {
                        System.out.println("找不到指定的原姓名，無法進行修改。");
                    }
                    break;

                case 4: // Delete: 刪除
                    System.out.print("請輸入要刪除的姓名：");
                    String deleteTarget = scanner.nextLine().trim();
                    int deleteIndex = findNameIndex(names, deleteTarget);
                    
                    if (deleteIndex != -1) {
                        // 利用 remove(index) 會回傳被刪除元素的特性來顯示訊息
                        String removed = names.remove(deleteIndex);
                        System.out.println("刪除成功：已移除「" + removed + "」");
                    } else {
                        System.out.println("刪除失敗：找不到姓名「" + deleteTarget + "」");
                    }
                    break;

                case 5: // Read: 列出全部
                    if (names.isEmpty()) {
                        System.out.println("目前名單為空。");
                    } else {
                        System.out.println("--- 目前名單 ---");
                        // 需要顯示編號，所以使用一般 for 迴圈
                        for (int i = 0; i < names.size(); i++) {
                            System.out.println("[" + i + "] " + names.get(i));
                        }
                    }
                    break;

                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
        }
        scanner.close();
    }

    /**
     * 搜尋姓名所在的索引 (忽略大小寫)
     * 這是 CRUD 系統中最重要的核心方法，Update 和 Delete 都需要依賴它來找資料位置
     * 
     * @param names  姓名集合
     * @param target 欲搜尋的目標姓名
     * @return 如果找到回傳索引，找不到回傳 -1
     */
    public static int findNameIndex(ArrayList<String> names, String target) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}