public class Q12_BookingReport {
    public static void main(String[] args) {
        Q12_Booking[] bookings = {
            new Q12_Booking("B001", "Amy", 2, 750, true),
            new Q12_Booking("B002", "Ben", 4, 600, false),
            new Q12_Booking("B003", "Cara", 3, 900, true),
            new Q12_Booking("B004", "Dan", 1, 1200, true)
        };

        System.out.println("已確認筆數：" + countConfirmed(bookings));
        System.out.println("已確認收入：" + calculateConfirmedRevenue(bookings));

        Q12_Booking found = findById(bookings, "b003");
        System.out.println("搜尋結果：" + found);

        Q12_Booking largest = findLargestConfirmed(bookings);
        System.out.println("最高確認預約：" + largest);
    }

    // 1. 計算已確認筆數
    public static int countConfirmed(Q12_Booking[] bookings) {
        if (bookings == null) return 0;
        
        int count = 0;
        for (Q12_Booking b : bookings) {
            if (b != null && b.isConfirmed()) {
                count++;
            }
        }
        return count;
    }

    // 2. 計算已確認收入
    public static double calculateConfirmedRevenue(Q12_Booking[] bookings) {
        if (bookings == null) return 0.0;
        
        double totalRevenue = 0.0;
        for (Q12_Booking b : bookings) {
            if (b != null && b.isConfirmed()) {
                totalRevenue += b.getTotalPrice();
            }
        }
        return totalRevenue;
    }

    // 3. 依代碼搜尋
    public static Q12_Booking findById(Q12_Booking[] bookings, String id) {
        if (bookings == null || id == null) return null;
        
        for (Q12_Booking b : bookings) {
            if (b != null && b.getId() != null && b.getId().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null; 

    // 4. 找出最高確認預約
    public static Q12_Booking findLargestConfirmed(Q12_Booking[] bookings) {
        if (bookings == null) return null;
        
        Q12_Booking largest = null;
        
        for (Q12_Booking b : bookings) {
            // 只比較已確認的預約
            if (b != null && b.isConfirmed()) {
                if (largest == null) {
                    largest = b;
                } 
                // 如果後續找到的預約金額「大於」目前的最高金額才更新
                // 這樣可以保證平手時，保留陣列中較前面的物件
                else if (b.getTotalPrice() > largest.getTotalPrice()) {
                    largest = b;
                }
            }
        }
        
        return largest;
    }
}