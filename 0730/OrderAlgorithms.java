import java.util.ArrayList;

public class OrderAlgorithms {
    // 依金額降冪排序 (保持穩定性)
    public static void mergeSortByAmountDesc(Order[] orders) {
        Order[] temp = new Order[orders.length];
        mergeSortByAmountDesc(orders, temp, 0, orders.length - 1);
    }

    private static void mergeSortByAmountDesc(Order[] orders, Order[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByAmountDesc(orders, temp, left, mid);
        mergeSortByAmountDesc(orders, temp, mid + 1, right);
        merge(orders, temp, left, mid, right);
    }

    private static void merge(Order[] orders, Order[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            // 金額降冪：大於等於先取左邊，保持原本的訂單順序 (Stable)
            if (orders[i].getAmount() >= orders[j].getAmount()) {
                temp[k++] = orders[i++];
            } else {
                temp[k++] = orders[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = orders[i++];
        }
        while (j <= right) {
            temp[k++] = orders[j++];
        }
        for (int index = left; index <= right; index++) {
            orders[index] = temp[index];
        }
    }

    // 依顧客姓名搜尋全部訂單
    public static ArrayList<Order> searchByCustomer(ArrayList<Order> allOrders, String customer) {
        ArrayList<Order> results = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getCustomer().equalsIgnoreCase(customer)) {
                results.add(order);
            }
        }
        return results;
    }
}