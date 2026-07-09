public class DrinkMenu {
    public static void main(String[] args) {
        // 設定一個整數 option
        int option = 2; // 您可以更改這個數字來測試不同的結果

        // 根據 option 輸出對應的飲料
        switch (option) {
            case 1:
                System.out.println("Black tea");
                break;
            case 2:
                System.out.println("Green tea");
                break;
            case 3:
                System.out.println("Coffee");
                break;
            default:
                System.out.println("Unknown option");
        }
    }
}