public class TemperatureLevel {
    public static void main(String[] args) {
        // 設定一個溫度 temperature
        double temperature = 25.0; // 您可以更改這個數字來測試不同的結果

        // 判斷溫度並輸出對應的感覺
        if (temperature < 15) {
            System.out.println("Cold");
        } else if (temperature < 28) {
            // 程式走到這裡，代表 temperature 已經 >= 15
            System.out.println("Comfortable");
        } else {
            // 程式走到這裡，代表 temperature 已經 >= 28
            System.out.println("Hot");
        }
    }
}