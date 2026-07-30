import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class RepairSchedulingSystem {
    public static void main(String[] args) {
        ArrayList<RepairTask> allTasks = new ArrayList<>();
        Deque<RepairTask> waitingQueue = new ArrayDeque<>();
        Deque<RepairTask> completedStack = new ArrayDeque<>();

        addTask(allTasks, waitingQueue, new RepairTask("T01", "投影機", 3));
        addTask(allTasks, waitingQueue, new RepairTask("T02", "筆記型電腦", 5));
        addTask(allTasks, waitingQueue, new RepairTask("T03", "印表機", 3));
        addTask(allTasks, waitingQueue, new RepairTask("T04", "伺服器", 9));

        System.out.println("---------------------------------");
        
        RepairTask[] sortedTasks = allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.sortByPriorityDesc(sortedTasks);
        System.out.println("【依優先等級排序 (相同等級保證先後順序)】");
        for (RepairTask task : sortedTasks) System.out.println(task);

        System.out.println("---------------------------------");

        // 搜尋功能測試
        System.out.println("搜尋編號 T03: " + RepairAlgorithms.searchById(allTasks, "T03"));
        System.out.println("搜尋設備名稱含 '機': " + RepairAlgorithms.searchByEquipment(allTasks, "機"));

        System.out.println("---------------------------------");

        // 工作處理與統計
        processTask(waitingQueue, completedStack);
        processTask(waitingQueue, completedStack);
        undoLastTask(waitingQueue, completedStack);
        
        printStats(allTasks, waitingQueue, completedStack);
    }

    public static void addTask(ArrayList<RepairTask> all, Deque<RepairTask> queue, RepairTask task) {
        all.add(task);
        queue.offer(task);
    }

    public static void processTask(Deque<RepairTask> queue, Deque<RepairTask> stack) {
        RepairTask task = queue.poll();
        if (task != null) {
            stack.push(task);
            System.out.println("【完成維修】" + task);
        } else {
            System.out.println("【提示】無等待中維修工作。");
        }
    }

    public static void undoLastTask(Deque<RepairTask> queue, Deque<RepairTask> stack) {
        RepairTask task = stack.poll();
        if (task != null) {
            queue.offerFirst(task);
            System.out.println("【復原維修紀錄】工作退回等待區: " + task);
        } else {
            System.out.println("【提示】無可復原的紀錄。");
        }
    }

    public static void printStats(ArrayList<RepairTask> all, Deque<RepairTask> queue, Deque<RepairTask> stack) {
        System.out.println("\n【系統統計】");
        System.out.println("總登記工作數: " + all.size());
        System.out.println("等待處理數: " + queue.size());
        System.out.println("已完成數: " + stack.size());
    }
}