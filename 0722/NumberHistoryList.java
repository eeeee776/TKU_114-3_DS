public class NumberHistoryList {
    
    // 定義內部 Node 類別
    private class Node {
        int data;
        Node next;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public NumberHistoryList() {
        head = null;
        size = 0;
    }

    // 前端新增
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // 尾端新增
    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // 搜尋
    public boolean search(int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 刪除指定值
    public boolean removeValue(int target) {
        if (head == null) return false;

        // 如果要刪除的是第一個節點
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }

        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // 輸出串列
    public void print() {
        Node current = head;
        System.out.print("目前紀錄：");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // 取得 Size
    public int getSize() {
        return size;
    }

    // 計算總和
    public Integer getSum() {
        if (head == null) return null; // 空串列回傳 null 
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }

    // 取得最大值
    public Integer getMax() {
        if (head == null) return null;
        int max = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    // 取得最小值
    public Integer getMin() {
        if (head == null) return null;
        int min = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }

    // 主程式：至少 8 次測試
    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();
        
        System.out.println("=== 初始狀態空串列測試 ===");
        System.out.println("Sum: " + list.getSum() + ", Max: " + list.getMax() + ", Min: " + list.getMin());
        
        // 1-3. 測試新增操作
        list.addLast(50);
        list.addFirst(10);
        list.addLast(100);
        
        // 4. 測試輸出與統計
        System.out.println("\n=== 新增 3 筆資料後 ===");
        list.print();
        System.out.println("Size: " + list.getSize());
        System.out.println("Sum: " + list.getSum());
        System.out.println("Max: " + list.getMax());
        System.out.println("Min: " + list.getMin());

        // 5. 測試搜尋
        System.out.println("\n=== 測試搜尋 ===");
        System.out.println("包含 50？ " + list.search(50));
        System.out.println("包含 99？ " + list.search(99));

        // 6-7. 測試刪除 (刪除頭部、刪除中間/尾部)
        System.out.println("\n=== 測試刪除 ===");
        list.removeValue(10); // 刪除頭部
        System.out.println("刪除 10 後：");
        list.print();
        
        list.removeValue(100); // 刪除尾部
        System.out.println("刪除 100 後：");
        list.print();

        // 8. 測試刪除找不到的資料與單一節點
        list.removeValue(99); // 刪除不存在的資料
        System.out.println("刪除不存在的 99 後 (Size 應不變)：" + list.getSize());
        
        list.removeValue(50); // 刪除最後一個節點，變成空串列
        System.out.println("刪除最後一個節點後：");
        list.print();
        System.out.println("目前 Size：" + list.getSize());
    }
}