import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserUndoSystem {
    // 使用 Deque 搭配 ArrayDeque 實作 Stack 行為，保存過去的頁面
    private Deque<String> history = new ArrayDeque<>();
    
    // 獨立保存目前正在瀏覽的頁面
    private String currentPage = null;

    /**
     * 1. 開啟新頁面 (對應 Undo 概念中的「保存修改前狀態」)
     */
    public void visit(String url) {
        // 如果原本已經有開啟頁面，在跳轉前必須先把它 push 進 Stack 暫存
        if (currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;
        System.out.println("🔄 網址跳轉：" + currentPage);
    }

    /**
     * 2. 返回上一頁 (對應 Undo 概念中的 pop 與還原)
     */
    public void back() {
        // 核心觀念：操作 Stack 前一定要檢查 isEmpty()，防止 underflow 發生例外
        if (history.isEmpty()) {
            System.out.println("❌ 操作失敗：已經沒有上一頁了！");
            return;
        }
        
        // 從頂端 pop 出最近一次保存的頁面，覆蓋當前狀態 (LIFO：最後進的先出)
        String previousPage = history.pop();
        currentPage = previousPage;
        System.out.println("⬅️ 返回上一頁成功");
    }

    /**
     * 3. 查看目前頁面
     */
    public void showCurrentPage() {
        if (currentPage == null) {
            System.out.println("👁️ 目前頁面：(無)");
        } else {
            System.out.println("👁️ 目前頁面：" + currentPage);
        }
        System.out.println("   [歷史紀錄 Stack 大小: " + history.size() + "]");
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();
        System.out.println("=== 瀏覽器啟動 ===");

        // 至少 8 次操作測試
        
        // 操作 1~3：連續開啟三個網頁
        browser.visit("https://google.com");
        browser.visit("https://github.com");
        browser.visit("https://stackoverflow.com");
        
        // 操作 4：查看目前頁面 (應為 stackoverflow，Stack 中有 google 與 github)
        browser.showCurrentPage();

        // 操作 5：第一次返回上一頁
        browser.back();
        
        // 操作 6：查看目前頁面 (應為 github，Stack 中剩 google)
        browser.showCurrentPage();

        // 操作 7：第二次返回上一頁
        browser.back();
        browser.showCurrentPage(); // 應回到 google

        // 操作 8：第三次返回上一頁 (嘗試觸發 underflow 防護)
        browser.back(); 
        
        // 操作 9：確認當前頁面是否還是 google，且程式沒有當掉
        browser.showCurrentPage();
    }
}