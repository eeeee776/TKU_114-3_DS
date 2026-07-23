import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CounterServiceSystem {
    // 使用 Deque 搭配 ArrayDeque 實作 Queue，保存等待中的客戶
    private Deque<String> waitingQueue = new ArrayDeque<>();
    
    // 另外準備一個 List 來保存已經服務完畢的紀錄
    private List<String> history = new ArrayList<>();

    /**
     * 1. 取號：將客戶加入排隊隊伍 (加入 Queue 尾端)
     */
    public void takeTicket(String number, String name) {
        String customer = number + " " + name;
        waitingQueue.offer(customer); // offer() 會將資料放到隊伍最後面
        System.out.println("🎟️ 取號成功：" + customer);
    }

    /**
     * 2. 叫號：呼叫排在最前面的客戶 (從 Queue 前端取出)
     */
    public void callNext() {
        // Queue 的 poll() 在沒有資料時會安全地回傳 null，而不會引發例外
        String nextCustomer = waitingQueue.poll();
        
        if (nextCustomer == null) {
            System.out.println("📢 廣播：目前無人等待叫號！");
        } else {
            System.out.println("📢 廣播：請【" + nextCustomer + "】到櫃台辦理。");
            // 將服務過的客戶存入歷史紀錄
            history.add(nextCustomer);
        }
    }

    /**
     * 3. 查看下一位：只偷看 Queue 最前端的資料，但不取出
     */
    public void peekNext() {
        String nextCustomer = waitingQueue.peek();
        if (nextCustomer == null) {
            System.out.println("👀 櫃台視角：下一位 (無)");
        } else {
            System.out.println("👀 櫃台視角：下一位是 " + nextCustomer);
        }
    }

    /**
     * 4. 查詢等待人數
     */
    public void showWaitingCount() {
        System.out.println("👥 目前等待人數：" + waitingQueue.size() + " 人");
    }

    /**
     * 5. 查看歷史服務紀錄
     */
    public void showHistory() {
        System.out.println("📜 今日已服務名單：" + history);
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        CounterServiceSystem counter = new CounterServiceSystem();
        System.out.println("=== 🏦 銀行櫃台叫號系統開始服務 ===");

        // 測試：空 Queue 叫號，確保系統不會當機
        counter.callNext(); 
        System.out.println("-----------------------------------");

        // 三人依序取號 (FIFO 示範準備)
        counter.takeTicket("A001", "Alice");
        counter.takeTicket("A002", "Bob");
        counter.takeTicket("A003", "Charlie");
        
        // 查看目前狀態
        counter.showWaitingCount(); // 應為 3 人
        counter.peekNext();         // 應為最早來的 A001 Alice
        System.out.println("-----------------------------------");

        // 開始叫號 (展現 FIFO：最早進來的 Alice 會最先被 poll 出來)
        counter.callNext();         // 叫 A001
        counter.showWaitingCount(); // 剩餘 2 人
        counter.peekNext();         // 下一位變成 A002 Bob
        System.out.println("-----------------------------------");

        counter.callNext();         // 叫 A002
        
        // 查看最終紀錄
        counter.showHistory();      // 歷史紀錄中應該只有 Alice 與 Bob
    }
}