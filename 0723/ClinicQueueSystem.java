import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClinicQueueSystem {
    // 使用 Queue 儲存等待看診的病患 (FIFO)
    private Deque<Patient> waitingQueue = new ArrayDeque<>();
    
    // 使用 Set 來記錄發放過的號碼，確保號碼不可重複
    private Set<String> registeredNumbers = new HashSet<>();
    
    // 記錄已經服務完成的總人數
    private int totalServedCount = 0;

    /**
     * 1. 掛號 (加入 Queue)
     */
    public void register(String number, String name, String department) {
        // 防護：檢查號碼是否重複
        if (registeredNumbers.contains(number)) {
            System.out.println("❌ 掛號失敗：號碼 " + number + " 已被使用！");
            return;
        }

        Patient newPatient = new Patient(number, name, department);
        waitingQueue.offer(newPatient); // 排入 Queue 的尾端
        registeredNumbers.add(number);  // 記錄號碼已被使用
        System.out.println("🏥 掛號成功：" + newPatient);
    }

    /**
     * 2. 叫號 (從 Queue 移除並看診)
     */
    public void callNext() {
        // 空結構防護：使用 poll() 安全取出，若為空會回傳 null
        Patient nextPatient = waitingQueue.poll();
        
        if (nextPatient == null) {
            System.out.println("📢 廣播：目前無人等待看診！");
        } else {
            System.out.println("📢 廣播：請【" + nextPatient.getName() + "】至 " + nextPatient.getDepartment() + " 看診。(號碼: " + nextPatient.getNumber() + ")");
            totalServedCount++; // 服務完成，總服務人數加 1
        }
    }

    /**
     * 3. 查看下一位 (不移除)
     */
    public void peekNext() {
        Patient nextPatient = waitingQueue.peek();
        if (nextPatient == null) {
            System.out.println("👀 護理站視角：下一位 (無)");
        } else {
            System.out.println("👀 護理站視角：下一位準備 -> " + nextPatient.getName());
        }
    }

    /**
     * 4. 顯示目前診所的完整狀態 (等待清單、各科別人數、總服務數)
     */
    public void showStatus() {
        System.out.println("\n=== 📊 診所目前狀態 ===");
        System.out.println("📝 等待清單：" + waitingQueue);
        
        // 動態計算各科別等待人數
        Map<String, Integer> deptCounts = new HashMap<>();
        for (Patient p : waitingQueue) {
            String dept = p.getDepartment();
            deptCounts.put(dept, deptCounts.getOrDefault(dept, 0) + 1);
        }
        
        System.out.println("👥 各科別等待人數：");
        if (deptCounts.isEmpty()) {
            System.out.println("   (目前無人等待)");
        } else {
            for (Map.Entry<String, Integer> entry : deptCounts.entrySet()) {
                System.out.println("   - " + entry.getKey() + ": " + entry.getValue() + " 人");
            }
        }
        
        System.out.println("✅ 今日總服務人數：" + totalServedCount + " 人");
        System.out.println("=======================\n");
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();
        System.out.println("=== 🏥 診所系統啟動 ===");

        // 測試 1：正常掛號
        clinic.register("N001", "Alice", "內科");
        clinic.register("N002", "Bob", "外科");
        clinic.register("N003", "Charlie", "內科");
        
        // 測試 2：號碼重複防護
        clinic.register("N001", "David", "眼科"); // 預期失敗

        // 測試 3：查看狀態 (應算出內科 2 人、外科 1 人)
        clinic.showStatus();

        // 測試 4：叫號與 FIFO 驗證
        clinic.peekNext(); // 應該是 Alice
        clinic.callNext(); // Alice 看診
        clinic.callNext(); // Bob 看診
        
        // 測試 5：叫號後的狀態改變
        clinic.showStatus(); // 應剩 Charlie，總服務人數為 2
        
        // 測試 6：將佇列清空與空值防護
        clinic.callNext(); // Charlie 看診
        clinic.callNext(); // 應印出無人等待
    }
}