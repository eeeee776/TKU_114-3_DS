public class RecursiveNameSearchPractice {
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};
        String[] emptyNames = {};
        
        // 5. 測試各種邊界與情況
        System.out.println("搜尋 Alice (第一筆): " + search(names, "Alice", 0)); // 預期: 0
        System.out.println("搜尋 Eve (最後一筆): " + search(names, "Eve", 0));   // 預期: 4
        System.out.println("搜尋 Frank (不存在): " + search(names, "Frank", 0)); // 預期: -1
        System.out.println("搜尋空陣列: " + search(emptyNames, "Bob", 0));      // 預期: -1
    }
    
    // 1. 建立 search 方法
    public static int search(String[] names, String target, int index) {
        // Base case: 到達陣列尾端或空陣列
        if (index >= names.length) {
            return -1;
        }
        
        // Recursive case: 3. 使用 equals() 比較字串
        if (names[index].equals(target)) {
            return index; // 4. 找到時回傳索引
        }
        
        // 2. 不使用迴圈，以遞迴進行下一個位置的搜尋
        return search(names, target, index + 1);
    }
}