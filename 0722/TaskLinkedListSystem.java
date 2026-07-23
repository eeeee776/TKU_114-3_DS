public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("1. 建立一般工作與緊急工作");
        taskList.addNormalTask("T01", "整理報表");
        taskList.addNormalTask("T02", "回覆客戶信件");
        taskList.addUrgentTask("E01", "修復伺服器當機"); // 應該在最前面
        taskList.printUncompletedTasks();

        System.out.println("2. 完成工作 E01");
        taskList.completeTask("E01");
        taskList.printUncompletedTasks(); // E01 不應顯示在未完成清單中

        System.out.println("3. 刪除工作 T02");
        taskList.removeTask("T02");
        taskList.printUncompletedTasks();

        System.out.println("4. 完成剩下的 T01");
        taskList.completeTask("T01");
        taskList.printUncompletedTasks(); // 應該顯示沒有未完成工作
    }
}