public class RepairTask {
    private String id;
    private String equipmentName;
    private int priority; // 數字越大優先級越高

    public RepairTask(String id, String equipmentName, int priority) {
        this.id = id;
        this.equipmentName = equipmentName;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + equipmentName + " (優先等級: " + priority + ")";
    }
}