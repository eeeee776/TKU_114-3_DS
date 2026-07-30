import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class OrderManagementPractice {
    public static void main(String[] args) {
        ArrayList<Order> allOrders = new ArrayList<>();
        Deque<Order> waiting = new ArrayDeque<>();
        Deque<Order> completed = new ArrayDeque<>();

        // 測試新增訂單與防止重複編號
        addOrder(allOrders, waiting, new Order("O101", "Amy", 500));
        addOrder(allOrders, waiting, new Order("O102", "Ben", 1500));
        addOrder(allOrders, waiting, new Order("O103", "Amy", 1200));
        addOrder(allOrders, waiting, new Order("O101", "Cara", 800)); // 故意重複

        System.out.println("---------------------------------");
        
        // 依金額降冪排序
        Order[] sorted = allOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortByAmountDesc(sorted);
        System.out.println("依金額降冪排序：");
        for (Order o : sorted) {
            System.out.println(o);
        }

        System.out.println("---------------------------------");

        // 搜尋顧客訂單
        System.out.println("搜尋 Amy 的訂單：");
        for (Order o : OrderAlgorithms.searchByCustomer(allOrders, "Amy")) {
            System.out.println(o);
        }

        System.out.println("---------------------------------");

        // 處理與復原測試
        showNext(waiting);
        processNext(waiting, completed);
        processNext(waiting, completed);
        showNext(waiting);
        undoLast(waiting, completed);
        showNext(waiting);
        
        // 測試空 Queue 處理
        processNext(waiting, completed);
        processNext(waiting, completed);
        processNext(waiting, completed);
    }

    public static void addOrder(ArrayList<Order> allOrders, Deque<Order> waiting, Order order) {
        // 防止重複訂單編號
        for (Order existing : allOrders) {
            if (existing.getId().equals(order.getId())) {
                System.out.println("【拒絕新增】訂單編號 " + order.getId() + " 已存在！");
                return;
            }
        }
        allOrders.add(order);
        waiting.offer(order);
        System.out.println("已新增訂單：" + order);
    }

    public static void showNext(Deque<Order> waiting) {
        Order next = waiting.peek();
        if (next == null) {
            System.out.println("【狀態】目前沒有待處理訂單。");
        } else {
            System.out.println("【狀態】下一筆待處理：" + next);
        }
    }

    public static void processNext(Deque<Order> waiting, Deque<Order> completed) {
        Order order = waiting.poll();
        if (order == null) {
            System.out.println("【失敗】目前沒有待處理訂單可以處理。");
            return;
        }
        completed.push(order);
        System.out.println("【處理完成】" + order);
    }

    public static void undoLast(Deque<Order> waiting, Deque<Order> completed) {
        Order order = completed.poll();
        if (order == null) {
            System.out.println("【失敗】沒有可以復原的訂單。");
            return;
        }
        waiting.offerFirst(order); // 放回等待區的最前面
        System.out.println("【復原成功】" + order);
    }
}