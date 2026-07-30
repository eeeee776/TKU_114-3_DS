public class ProductSortPractice {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P01", "滑鼠", 500, 10),
            new Product("P02", "鍵盤", 1200, 5),
            new Product("P03", "隨身碟", 500, 20), // 價格相同，測試穩定性
            new Product("P04", "螢幕", 4500, 3),
            new Product("P05", "耳機", 1200, 15), // 價格相同，測試穩定性
            new Product("P06", "滑鼠墊", 150, 50),
            new Product("P07", "麥克風", 2500, 8),
            new Product("P08", "網路線", 150, 30)
        };

        insertionSortByPrice(products);

        System.out.println("編號\t名稱\t價格\t庫存");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public static void insertionSortByPrice(Product[] values) {
        for (int index = 1; index < values.length; index++) {
            Product key = values[index];
            int position = index - 1;

            // 使用 > 確保排序的穩定性 (Stable)
            while (position >= 0 && values[position].getPrice() > key.getPrice()) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }
}