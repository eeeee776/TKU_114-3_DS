import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 聯絡人管理系統 ===");
            System.out.println("1. 新增聯絡人");
            System.out.println("2. 搜尋聯絡人");
            System.out.println("3. 修改電話");
            System.out.println("4. 刪除聯絡人");
            System.out.println("5. 完整清單");
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
                case 1:
                    System.out.print("請輸入代碼：");
                    String code = scanner.nextLine().trim();
                    System.out.print("請輸入姓名：");
                    String name = scanner.nextLine().trim();
                    System.out.print("請輸入電話：");
                    String phone = scanner.nextLine().trim();
                    System.out.print("請輸入電子郵件：");
                    String email = scanner.nextLine().trim();

                    // 將驗證與新增邏輯交給 Method
                    boolean addSuccess = addContact(contacts, code, name, phone, email);
                    if (addSuccess) {
                        System.out.println("新增成功！");
                    }
                    break;

                case 2:
                    System.out.print("請輸入要搜尋的代碼：");
                    String searchCode = scanner.nextLine().trim();
                    Contact found = findByCode(contacts, searchCode);
                    if (found != null) {
                        System.out.println("搜尋結果：" + found);
                    } else {
                        System.out.println("找不到代碼為「" + searchCode + "」的聯絡人。");
                    }
                    break;

                case 3:
                    System.out.print("請輸入要修改的聯絡人代碼：");
                    String updateCode = scanner.nextLine().trim();
                    System.out.print("請輸入新電話：");
                    String newPhone = scanner.nextLine().trim();
                    
                    if (updatePhone(contacts, updateCode, newPhone)) {
                        System.out.println("電話修改成功！");
                    } else {
                        System.out.println("修改失敗：找不到該代碼的聯絡人。");
                    }
                    break;

                case 4:
                    System.out.print("請輸入要刪除的聯絡人代碼：");
                    String deleteCode = scanner.nextLine().trim();
                    
                    if (deleteContact(contacts, deleteCode)) {
                        System.out.println("刪除成功！");
                    } else {
                        System.out.println("刪除失敗：找不到該代碼的聯絡人。");
                    }
                    break;

                case 5:
                    displayAll(contacts);
                    break;

                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
        }
        scanner.close();
    }

    // ==========================================
    // 以下為自訂的 6 個 Method，負責處理核心資料邏輯
    // ==========================================

    /**
     * 自訂 Method 1：依據代碼尋找聯絡人 (所有操作的基礎核心)
     */
    public static Contact findByCode(ArrayList<Contact> contacts, String code) {
        for (Contact c : contacts) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    /**
     * 自訂 Method 2：新增聯絡人並處理檢查邏輯
     * @return 成功回傳 true，失敗(重複或姓名空白)回傳 false
     */
    public static boolean addContact(ArrayList<Contact> contacts, String code, String name, String phone, String email) {
        if (name.isEmpty()) {
            System.out.println("錯誤：姓名不可為空白！");
            return false;
        }
        if (findByCode(contacts, code) != null) {
            System.out.println("錯誤：代碼「" + code + "」已存在！");
            return false;
        }
        
        contacts.add(new Contact(code, name, phone, email));
        return true;
    }

    /**
     * 自訂 Method 3：修改電話
     */
    public static boolean updatePhone(ArrayList<Contact> contacts, String code, String newPhone) {
        Contact target = findByCode(contacts, code);
        if (target != null) {
            target.setPhone(newPhone); // 直接透過物件參考更新欄位
            return true;
        }
        return false;
    }

    /**
     * 自訂 Method 4：刪除聯絡人
     */
    public static boolean deleteContact(ArrayList<Contact> contacts, String code) {
        Contact target = findByCode(contacts, code);
        if (target != null) {
            return contacts.remove(target); // 傳入物件進行刪除
        }
        return false;
    }

    /**
     * 自訂 Method 5：列出所有聯絡人
     */
    public static void displayAll(ArrayList<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("目前通訊錄為空。");
            return;
        }
        System.out.println("--- 聯絡人清單 ---");
        for (Contact c : contacts) {
            System.out.println(c);
        }
        System.out.println("共計 " + contacts.size() + " 筆資料。");
    }
}