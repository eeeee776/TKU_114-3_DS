import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {
    // 當前顯示的文字內容
    private String currentText = "";
    
    // 使用 Deque (Stack 行為) 保存修改前的歷史紀錄
    private Deque<String> history = new ArrayDeque<>();

    /**
     * 新增文字
     */
    public void append(String newText) {
        // 1. 修改前，先將當前狀態 push 到 Stack 保存
        history.push(currentText);
        
        // 2. 進行修改
        currentText += newText;
        System.out.println("✏️ 新增文字：\"" + newText + "\"");
    }

    /**
     * 刪除最後數個字元
     */
    public void deleteLast(int count) {
        // 1. 修改前，先保存狀態
        history.push(currentText);

        // 2. 防呆機制：如果要求刪除的字數大於或等於目前長度，直接清空
        if (count >= currentText.length()) {
            currentText = "";
            System.out.println("🗑️ 刪除字元數大於或等於總長度，已全部清空。");
        } else {
            // 使用 substring 切割字串，保留 0 到 (總長度 - 刪除長度) 的部分
            currentText = currentText.substring(0, currentText.length() - count);
            System.out.println("🗑️ 刪除了最後 " + count + " 個字元。");
        }
    }

    /**
     * 復原上一步 (Undo)
     */
    public void undo() {
        // Underflow 防護：檢查 Stack 是否為空
        if (history.isEmpty()) {
            System.out.println("❌ 復原失敗：已經沒有歷史紀錄可以 Undo 了！");
            return;
        }

        // LIFO：從 Stack 頂端取出最近一次保存的狀態，覆蓋當前文字
        String previousState = history.pop();
        currentText = previousState;
        System.out.println("↩️ 執行 Undo 成功！");
    }

    /**
     * 顯示目前內容與狀態
     */
    public void showContent() {
        System.out.println("📝 目前內容：[" + currentText + "]");
        System.out.println("   (可復原步數: " + history.size() + ")");
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();
        System.out.println("=== 📝 啟動文字編輯器 ===");
        editor.showContent();

        // 測試 1：連續新增文字 (會產生 3 筆歷史紀錄)
        editor.append("Hello");
        editor.append(" Java");
        editor.append(" World");
        editor.showContent(); // 預期：[Hello Java World]

        // 測試 2：刪除最後數個字元 (會再產生 1 筆歷史紀錄)
        editor.deleteLast(6); // 刪除 " World" (包含空白)
        editor.showContent(); // 預期：[Hello Java]

        // 測試 3：連續 Undo 驗證 (作業要求至少三次)
        System.out.println(">>> 準備進行連續 Undo 測試...");
        
        editor.undo(); // 第 1 次 Undo：復原「刪除操作」
        editor.showContent(); // 預期回到：[Hello Java World]

        editor.undo(); // 第 2 次 Undo：復原「新增 World」
        editor.showContent(); // 預期回到：[Hello Java]

        editor.undo(); // 第 3 次 Undo：復原「新增 Java」
        editor.showContent(); // 預期回到：[Hello]
        
        editor.undo(); // 第 4 次 Undo：復原「新增 Hello」
        editor.showContent(); // 預期回到：[]

        // 測試 4：空結構操作防護 (此時 Stack 已空，再 Undo 應印出錯誤訊息而不當機)
        editor.undo(); 
    }
}