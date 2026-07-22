public class Equipment {
    private String code;
    private String name;
    private boolean isAvailable; // 可借用狀態

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.isAvailable = true; // 預設新增時都是可借用的
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * 執行借出操作
     * @return 成功借出回傳 true，若已被借出則回傳 false
     */
    public boolean borrowItem() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    /**
     * 執行歸還操作
     * @return 成功歸還回傳 true，若原本就沒被借出則回傳 false
     */
    public boolean returnItem() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "可借用" : "已借出";
        return "代碼: " + code + " | 名稱: " + name + " | 狀態: " + status;
    }
}