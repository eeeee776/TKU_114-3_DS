import java.util.Arrays;

public class ProductSortingSystem {
    public static void main(String[] args) {
        StoreProduct[] original = {
            new StoreProduct("Apple", 30, 100),
            new StoreProduct("Banana", 15, 200),
            new StoreProduct("Cherry", 80, 50),
            new StoreProduct("Date", 45, 120),
            new StoreProduct("Elderberry", 150, 30),
            new StoreProduct("Fig", 60, 80),
            new StoreProduct("Grape", 25, 300),
            new StoreProduct("Honeydew", 90, 40),
            new StoreProduct("Kiwi", 30, 90),
            new StoreProduct("Lemon", 20, 150)
        };

        System.out.println("【模式一：價格升冪】");
        StoreProduct[] byPriceAsc = original.clone();
        sortPriceAsc(byPriceAsc);
        printArray(byPriceAsc);

        System.out.println("\n【模式二：價格降冪】");
        StoreProduct[] byPriceDesc = original.clone();
        sortPriceDesc(byPriceDesc);
        printArray(byPriceDesc);

        System.out.println("\n【模式三：庫存降冪】");
        StoreProduct[] byStockDesc = original.clone();
        sortStockDesc(byStockDesc);
        printArray(byStockDesc);
    }

    // 價格升冪 (使用 Insertion Sort)
    public static void sortPriceAsc(StoreProduct[] values) {
        for (int i = 1; i < values.length; i++) {
            StoreProduct key = values[i];
            int pos = i - 1;
            while (pos >= 0 && values[pos].getPrice() > key.getPrice()) {
                values[pos + 1] = values[pos];
                pos--;
            }
            values[pos + 1] = key;
        }
    }

    // 價格降冪 (使用 Selection Sort)
    public static void sortPriceDesc(StoreProduct[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int maxIdx = start;
            for (int i = start + 1; i < values.length; i++) {
                if (values[i].getPrice() > values[maxIdx].getPrice()) {
                    maxIdx = i;
                }
            }
            StoreProduct temp = values[start];
            values[start] = values[maxIdx];
            values[maxIdx] = temp;
        }
    }

    // 庫存降冪 (使用 Insertion Sort)
    public static void sortStockDesc(StoreProduct[] values) {
        for (int i = 1; i < values.length; i++) {
            StoreProduct key = values[i];
            int pos = i - 1;
            while (pos >= 0 && values[pos].getStock() < key.getStock()) {
                values[pos + 1] = values[pos];
                pos--;
            }
            values[pos + 1] = key;
        }
    }

    public static void printArray(StoreProduct[] arr) {
        for (StoreProduct p : arr) {
            System.out.println(p);
        }
    }
}