import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

public class EventRegistrationSystem {
    private static final int MAX_CAPACITY = 2; // 設定極小容量測試候補與額滿功能

    public static void main(String[] args) {
        ArrayList<Registration> allRegistrations = new ArrayList<>(); // 總名單 (含正取與候補)
        ArrayList<Registration> officialList = new ArrayList<>();     // 正取名單
        Deque<Registration> waitlistQueue = new ArrayDeque<>();       // 候補 Queue
        Deque<Registration> cancelledStack = new ArrayDeque<>();      // 取消紀錄 Stack (支援復原)

        // 測試新增、額滿與候補
        register(allRegistrations, officialList, waitlistQueue, new Registration("R03", "Alice"));
        register(allRegistrations, officialList, waitlistQueue, new Registration("R01", "Bob"));
        register(allRegistrations, officialList, waitlistQueue, new Registration("R01", "Charlie")); // 重複編號測試
        register(allRegistrations, officialList, waitlistQueue, new Registration("R05", "David"));   // 進入候補
        register(allRegistrations, officialList, waitlistQueue, new Registration("R02", "Eve"));     // 進入候補

        System.out.println("---------------------------------");
        
        // 排序與查詢測試
        Registration[] sortedAll = allRegistrations.toArray(new Registration[0]);
        RegistrationAlgorithms.sortByIdAsc(sortedAll);
        
        System.out.println("【報名總名單 (依編號排序)】");
        for (Registration r : sortedAll) System.out.println(r);

        System.out.println("\nBinary Search 查 R05: " + 
            (RegistrationAlgorithms.binarySearchById(sortedAll, "R05") != -1 ? "找到" : "找不到"));
            
        System.out.println("Sequential Search 查 Bob: " + 
            RegistrationAlgorithms.searchByName(allRegistrations, "Bob"));

        System.out.println("---------------------------------");

        // 取消與遞補測試
        cancelRegistration(officialList, waitlistQueue, cancelledStack, "R01");
        cancelRegistration(officialList, waitlistQueue, cancelledStack, "R99"); // 取消不存在資料

        System.out.println("---------------------------------");

        // 復原取消測試
        undoCancel(officialList, cancelledStack);
        
        // 測試空候補 Queue 的遞補 (若一直取消)
        cancelRegistration(officialList, waitlistQueue, cancelledStack, "R03");
        cancelRegistration(officialList, waitlistQueue, cancelledStack, "R05"); // 原候補遞補上的
        cancelRegistration(officialList, waitlistQueue, cancelledStack, "R02"); // 候補空了
        cancelRegistration(officialList, waitlistQueue, cancelledStack, "R01"); 
    }

    public static void register(ArrayList<Registration> all, ArrayList<Registration> official, Deque<Registration> waitlist, Registration reg) {
        for (Registration existing : all) {
            if (existing.getId().equals(reg.getId())) {
                System.out.println("【報名失敗】編號 " + reg.getId() + " 已被使用。");
                return;
            }
        }
        all.add(reg);
        if (official.size() < MAX_CAPACITY) {
            official.add(reg);
            System.out.println("【報名成功】正取: " + reg);
        } else {
            waitlist.offer(reg);
            System.out.println("【名額已滿】進入候補: " + reg);
        }
    }

    public static void cancelRegistration(ArrayList<Registration> official, Deque<Registration> waitlist, Deque<Registration> cancelled, String targetId) {
        Iterator<Registration> it = official.iterator();
        while (it.hasNext()) {
            Registration reg = it.next();
            if (reg.getId().equals(targetId)) {
                it.remove();
                cancelled.push(reg);
                System.out.println("【取消報名】已取消: " + reg);
                
                // 執行遞補
                Registration nextWaitlist = waitlist.poll();
                if (nextWaitlist != null) {
                    official.add(nextWaitlist);
                    System.out.println("  -> 【候補遞補成功】正取新增: " + nextWaitlist);
                } else {
                    System.out.println("  -> 【提示】目前無人候補。");
                }
                return;
            }
        }
        System.out.println("【取消失敗】找不到編號為 " + targetId + " 的正取資料。");
    }

    public static void undoCancel(ArrayList<Registration> official, Deque<Registration> cancelled) {
        Registration reg = cancelled.poll();
        if (reg == null) {
            System.out.println("【復原失敗】沒有取消紀錄。");
            return;
        }
        
        // 若要復原，需檢查正取名額
        if (official.size() < MAX_CAPACITY) {
            official.add(reg);
            System.out.println("【復原成功】恢復正取: " + reg);
        } else {
            System.out.println("【復原警告】正取已滿，強制恢復但可能超過名額限制: " + reg);
            official.add(reg);
        }
    }
}