public class PriceMaxMin {
    public static void main(String[] args) {
        // 設定三個商品價格 (數值可依需求自行替換)
        int price1 = 120;
        int price2 = 50;
        int price3 = 250;

        // 將第一個價格預設為目前的最高價與最低價
        int max = price1;
        int min = price1;

        // 逐一比較以找出最高價格
        if (price2 > max) {
            max = price2;
        }
        if (price3 > max) {
            max = price3;
        }

        // 逐一比較以找出最低價格
        if (price2 < min) {
            min = price2;
        }
        if (price3 < min) {
            min = price3;
        }

        // 輸出最終結果
        System.out.println("最高價格: " + max);
        System.out.println("最低價格: " + min);
    }
}