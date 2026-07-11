public class TotalPrice {
    public static void main(String[] args) {
        // 宣告一個名為 total 的累加器變數，並將初始值設為 0
        int total = 0;

        // 依序將三個商品的價格加入累加器中
        total += 30;
        total += 45;
        total += 60;

        // 輸出最後的總金額，符合預期輸出格式
        System.out.println("Total price: " + total);
    }
}