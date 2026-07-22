public class Course {
    private String code;
    private String name;
    private int capacity; // 課程容量限制
    private int enrolled; // 目前選課人數

    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = Math.max(capacity, 1); // 防護：容量至少要 1 人
        this.enrolled = 0; // 新開課程預設人數為 0
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    /**
     * 判斷課程是否已額滿
     */
    public boolean isFull() {
        return enrolled >= capacity;
    }

    /**
     * 執行選課
     * @return 成功選課回傳 true，若已額滿則回傳 false
     */
    public boolean enroll() {
        if (!isFull()) {
            enrolled++;
            return true;
        }
        return false;
    }

    /**
     * 執行退選
     * @return 成功退選回傳 true，若目前無人選課則回傳 false
     */
    public boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String status = isFull() ? "【已額滿】" : "【可選課】";
        return "代碼: " + code + " | 名稱: " + name + " | 人數: " + enrolled + "/" + capacity + " " + status;
    }
}