public class SubtotalCalculator {
    public static void main(String[] args) {
        // 呼叫 calculateSubtotal 方法，傳入價格與數量
        // 因為該方法有回傳值(int)，所以我們宣告一個 int 變數 result 來接收並儲存它
        int result = calculateSubtotal(35, 3);
        
        // 將儲存起來的結果印出
        System.out.println("Subtotal: " + result);
    }

    // 定義一個回傳型態為 int 的方法：calculateSubtotal
    public static int calculateSubtotal(int price, int quantity) {
        // 計算小計 (價格 * 數量) 並將結果 return
        return price * quantity;
    }
    
}