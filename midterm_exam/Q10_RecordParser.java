public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };

        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }

        System.out.println("合法筆數 : " + countValidRecords(records));
        System.out.println("總金額 : " + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {
        // 規則 6：紀錄為 null 或只包含空白時，不合法
        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        String[] parts = record.split("\\|", -1);

        // 規則 1：剛好有 4 個欄位
        if (parts.length != 4) {
            return false;
        }

        // 規則 2：代碼與名稱移除前後空白後不可為空字串
        String code = parts[0].trim();
        String name = parts[1].trim();
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        // 規則 3, 4, 5：檢查數字格式與範圍
        try {
            int quantity = Integer.parseInt(parts[2].trim());
            int price = Integer.parseInt(parts[3].trim());

            if (quantity <= 0 || price < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            // 規則 5：文字無法轉換為整數時，不合法且不中斷程式
            return false;
        }

        return true;
    }

    public static int calculateRecordTotal(String record) {
        // 紀錄不合法時回傳 -1
        if (!isValidRecord(record)) {
            return -1;
        }

        // 走到這裡代表紀錄一定合法，可以直接解析計算
        String[] parts = record.split("\\|", -1);
        int quantity = Integer.parseInt(parts[2].trim());
        int price = Integer.parseInt(parts[3].trim());
        
        return quantity * price;
    }

    public static int countValidRecords(String[] records) {
        if (records == null) return 0;
        
        int count = 0;
        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }
        return count;
    }

    public static int calculateGrandTotal(String[] records) {
        if (records == null) return 0;
        
        int total = 0;
        for (String record : records) {
            int recordTotal = calculateRecordTotal(record);
            // 只有合法的紀錄才會計算總額
            if (recordTotal != -1) {
                total += recordTotal;
            }
        }
        return total;
    }
}