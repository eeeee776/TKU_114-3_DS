public class PrintOrderMenu {
    
    public static void main(String[] args) {
        // 呼叫下方定義的顯示選單方法
        printOrderMenu();
    }

    // 定義一個沒有參數與回傳值的方法 printOrderMenu
    public static void printOrderMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea");
        System.out.println("2. Green tea");
        System.out.println("3. Coffee");
        System.out.println("0. Checkout");
    }
    
}