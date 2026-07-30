import java.util.Scanner;

public class SeatNumberSearchPractice {
    public static void main(String[] args) {
        // 1. 建立 12 筆已排序座位編號
        int[] seats = {101, 105, 110, 112, 115, 120, 122, 130, 135, 140, 145, 150};
        Scanner scanner = new Scanner(System.in);
        
        // 2. 由鍵盤輸入
        System.out.print("請輸入要搜尋的座位編號：");
        int target = scanner.nextInt();
        
        int low = 0;
        int high = seats.length - 1;
        int foundIndex = -1;
        
        // 3. Binary Search
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // 4. 每一輪顯示 low、mid、high
            System.out.printf("low=%d, mid=%d, high=%d%n", low, mid, high);
            
            if (seats[mid] == target) {
                foundIndex = mid;
                break;
            } else if (target < seats[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        if (foundIndex != -1) {
            System.out.println("找到座位！索引位置：" + foundIndex);
        } else {
            System.out.println("找不到該座位。");
        }
        
        scanner.close();
    }
}