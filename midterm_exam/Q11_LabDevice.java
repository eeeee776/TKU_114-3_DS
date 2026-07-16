public class Q11_LabDevice {
    private String code;
    private String name;
    private int usageHours;
    private boolean active;

    public Q11_LabDevice(
        String code,
        String name,
        int usageHours,
        boolean active
    ) {
        // 處理 code：null 或空字串設為 UNKNOWN
        if (code == null || code.trim().isEmpty()) {
            this.code = "UNKNOWN";
        } else {
            this.code = code.trim();
        }

        // 處理 name：null 或空字串設為 Unnamed
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unnamed";
        } else {
            this.name = name.trim();
        }

        // 處理 usageHours：小於 0 儲存為 0
        this.usageHours = Math.max(0, usageHours);
        
        this.active = active;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public int getUsageHours() {
        return this.usageHours;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setName(String name) {
        // 只有非 null 且非空白才更新，否則保留原名稱
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void addUsageHours(int hours) {
        // 只接受大於 0 的時數
        if (hours > 0) {
            this.usageHours += hours;
        }
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean needsMaintenance() {
        return this.usageHours >= 100;
    }

    @Override
    public String toString() {
        // 決定狀態顯示文字
        String status = this.active ? "active" : "inactive";
        
        // 依照題目要求的格式回傳字串
        return this.code + " | " + this.name + " | " + this.usageHours + " hours | " + status;
    }

    // --- 題目提供的 main 方法，用於測試 ---
    public static void main(String[] args) {
        Q11_LabDevice device = new Q11_LabDevice(
            " D01 ",
            " Printer ",
            90,
            true
        );

        device.addUsageHours(30);
        device.addUsageHours(-5); // 應被忽略
        device.setName(" 3D Printer ");

        System.out.println(device);
        System.out.println("需要保養：" + device.needsMaintenance());

        device.deactivate();
        System.out.println("啟用狀態：" + device.isActive());
    }
}