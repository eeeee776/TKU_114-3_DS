public class PricePrinter {
    
    // 主程式：程式執行的起點
    public static void main(String[] args) {
        // 呼叫 printItem 方法，並傳入不同的商品名稱(String)與價格(int)
        printItem("Black tea", 35);
        printItem("Coffee", 55);
        printItem("Sandwich", 40);
    }

    // 定義一個帶有兩個參數的方法：itemName 與 price
    public static void printItem(String itemName, int price) {
        // 輸出傳入的商品名稱與價格
        System.out.println("商品名稱：" + itemName + "，價格：" + price + "元");
    }
    
}