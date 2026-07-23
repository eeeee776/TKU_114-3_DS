public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    // 緊急工作加到前端
    public void addUrgentTask(String taskId, String description) {
        TaskNode newNode = new TaskNode(taskId, description);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // 一般工作加到尾端
    public void addNormalTask(String taskId, String description) {
        TaskNode newNode = new TaskNode(taskId, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // 完成工作 (將狀態改為 true)
    public boolean completeTask(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equals(taskId)) {
                current.isCompleted = true;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 刪除工作
    public boolean removeTask(String taskId) {
        if (head == null) return false;

        if (head.taskId.equals(taskId)) {
            head = head.next;
            size--;
            return true;
        }

        TaskNode previous = head;
        TaskNode current = head.next;
        while (current != null) {
            if (current.taskId.equals(taskId)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // 取得工作總數
    public int getTotalCount() {
        return size;
    }

    // 取得未完成數量
    public int getUncompletedCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

    // 列出所有未完成工作
    public void printUncompletedTasks() {
        System.out.println("--- 尚未完成工作清單 ---");
        TaskNode current = head;
        boolean hasUncompleted = false;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("[" + current.taskId + "] " + current.description);
                hasUncompleted = true;
            }
            current = current.next;
        }
        if (!hasUncompleted) {
            System.out.println("目前沒有未完成的工作！");
        }
        System.out.println("工作總數：" + getTotalCount() + "，未完成數量：" + getUncompletedCount() + "\n");
    }
}