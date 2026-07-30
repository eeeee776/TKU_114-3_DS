import java.util.Scanner;

public class ProductIdSearchPractice {
    public static void main(String[] args) {
        // 1. 建立 8 筆未排序商品編號
        int[] productIds = {502, 103, 899, 421, 756, 234, 987, 345};
        Scanner scanner = new Scanner(System.in);
        
        // 2. 由鍵盤輸入
        System.out.print("請輸入要搜尋的商品編號：");
        int target = scanner.nextInt();
        
        int checks = 0;
        int foundIndex = -1;
        
        for (int i = 0; i < productIds.length; i++) {
            checks++;
            if (productIds[i] == target) {
                foundIndex = i;
                break;
            }
        }
        
        // 3. 顯示結果與明確訊息
        if (foundIndex != -1) {
            System.out.println("找到商品！索引位置：" + foundIndex);
        } else {
            System.out.println("找不到該商品編號。");
        }
        
        // 4. 顯示實際比較次數
        System.out.println("實際比較次數：" + checks);
        
        scanner.close();
    }
}