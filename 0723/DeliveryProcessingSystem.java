import java.util.ArrayDeque;
import java.util.Deque;

public class DeliveryProcessingSystem {
    // 1. 等待區：使用 Queue 行為 (offer 加入尾端, poll 移除前端)
    private Deque<DeliveryTask> waitingQueue = new ArrayDeque<>();
    
    // 2. 完成區：使用 Stack 行為 (push 加入頂端, pop 移除頂端)
    private Deque<DeliveryTask> completedStack = new ArrayDeque<>();

    /**
     * 新增配送工作 (加入 Queue 尾端)
     */
    public void addTask(String id, String destination) {
        DeliveryTask task = new DeliveryTask(id, destination);
        waitingQueue.offer(task);
        System.out.println("📦 新增配送工作：" + task);
    }

    /**
     * 查看下一筆工作 (只看 Queue 前端，不取出)
     */
    public void peekNext() {
        DeliveryTask next = waitingQueue.peek();
        if (next == null) {
            System.out.println("👀 司機視角：下一筆工作 (無)");
        } else {
            System.out.println("👀 司機視角：準備配送 -> " + next);
        }
    }

    /**
     * 完成下一筆 (從 Queue 前端取出，存入 Stack 頂端)
     */
    public void completeNext() {
        // 從 Queue 前端取出
        DeliveryTask task = waitingQueue.poll();
        
        if (task == null) {
            System.out.println("⚠️ 警告：目前沒有待配送的工作！");
        } else {
            // 存入 Stack 頂端成為歷史紀錄
            completedStack.push(task);
            System.out.println("✅ 完成配送：" + task);
        }
    }

    /**
     * 復原最近完成 (從 Stack 頂端取出，根據題目要求放回 Queue 尾端)
     */
    public void undoLastCompleted() {
        // 空結構防護 (Underflow)
        if (completedStack.isEmpty()) {
            System.out.println("❌ 復原失敗：沒有已完成的紀錄可以復原！");
            return;
        }

        // 從 Stack 頂端取出最後完成的工作
        DeliveryTask lastCompleted = completedStack.pop();
        
        // 【重要】根據題目要求：復原後工作回到等待 Queue 尾端
        waitingQueue.offer(lastCompleted);
        System.out.println("↩️ 復原工作 (因異常退回等待區重新排隊)：" + lastCompleted);
    }

    /**
     * 輸出等待數、完成數與所有處理紀錄
     */
    public void showStatus() {
        System.out.println("\n=== 📊 配送系統狀態 ===");
        System.out.println("🚚 等待配送數量：" + waitingQueue.size() + " 筆");
        System.out.println("   [Queue 清單] " + waitingQueue);
        
        System.out.println("🏁 已完成數量：" + completedStack.size() + " 筆");
        System.out.println("   [Stack 紀錄] " + completedStack + " <- (最左邊是最新完成的)");
        System.out.println("=======================\n");
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();
        System.out.println("=== 🚚 配送系統啟動 ===");

        // 測試 1：新增工作
        system.addTask("D001", "台北車站");
        system.addTask("D002", "中正紀念堂");
        system.addTask("D003", "大安森林公園");
        
        // 測試 2：查看下一筆與目前狀態
        system.peekNext(); // 應為 D001 台北車站
        system.showStatus();

        // 測試 3：完成前兩筆工作 (資料從 Queue 轉移到 Stack)
        System.out.println(">>> 司機開始配送...");
        system.completeNext(); // 完成 D001
        system.completeNext(); // 完成 D002
        
        system.showStatus(); // Queue 剩 D003，Stack 有 D002, D001 (D002 在頂端)

        // 測試 4：復原最近完成 (Stack pop 出 D002，並 offer 回 Queue 尾端)
        System.out.println(">>> 發現 D002 配送失敗，執行復原...");
        system.undoLastCompleted();
        
        // 測試 5：驗證復原結果 (D003 在前，復原的 D002 在 Queue 的尾端)
        system.showStatus();
        
        // 測試 6：空結構操作測試
        system.completeNext(); // 完成 D003
        system.completeNext(); // 完成 D002
        system.completeNext(); // Queue 已空，測試安全防護不當機
    }
}