// 檔案二：EmployeeSearchSystem.java
public class EmployeeSearchSystem {
    public static void main(String[] args) {
        // 依編號排序的員工陣列
        Employee[] employees = {
            new Employee(1001, "張三", "資訊部", "#110"),
            new Employee(1005, "李四", "人資部", "#120"),
            new Employee(1012, "王五", "業務部", "#130"),
            new Employee(1020, "趙六", "財務部", "#140")
        };
        
        Employee[] emptyEmployees = {}; // 測試空陣列

        System.out.println(searchEmployee(employees, 1012)); // 找得到
        System.out.println(searchEmployee(employees, 9999)); // 找不到
        System.out.println(searchEmployee(emptyEmployees, 1001)); // 空陣列
    }

    public static String searchEmployee(Employee[] employees, int targetId) {
        if (employees.length == 0) return "系統錯誤：員工資料庫為空。";

        int low = 0;
        int high = employees.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (employees[mid].getId() == targetId) {
                return "找到資料 - " + employees[mid].toString();
            } else if (targetId < employees[mid].getId()) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return "找不到編號為 " + targetId + " 的員工。";
    }
}