import java.util.ArrayList;

public class RepairAlgorithms {
    // 使用 Merge Sort 依優先等級降冪；相同等級保持登記順序 (Stable)
    public static void sortByPriorityDesc(RepairTask[] tasks) {
        RepairTask[] temp = new RepairTask[tasks.length];
        sortByPriorityDesc(tasks, temp, 0, tasks.length - 1);
    }

    private static void sortByPriorityDesc(RepairTask[] tasks, RepairTask[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByPriorityDesc(tasks, temp, left, mid);
        sortByPriorityDesc(tasks, temp, mid + 1, right);
        
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (tasks[i].getPriority() >= tasks[j].getPriority()) {
                temp[k++] = tasks[i++];
            } else {
                temp[k++] = tasks[j++];
            }
        }
        while (i <= mid) temp[k++] = tasks[i++];
        while (j <= right) temp[k++] = tasks[j++];
        for (int idx = left; idx <= right; idx++) tasks[idx] = temp[idx];
    }

    // 依編號搜尋 (Sequential Search)
    public static RepairTask searchById(ArrayList<RepairTask> tasks, String id) {
        for (RepairTask task : tasks) {
            if (task.getId().equals(id)) return task;
        }
        return null;
    }

    // 依設備名稱搜尋 (Sequential Search)
    public static ArrayList<RepairTask> searchByEquipment(ArrayList<RepairTask> tasks, String equipment) {
        ArrayList<RepairTask> results = new ArrayList<>();
        for (RepairTask task : tasks) {
            if (task.getEquipmentName().toLowerCase().contains(equipment.toLowerCase())) {
                results.add(task);
            }
        }
        return results;
    }
}