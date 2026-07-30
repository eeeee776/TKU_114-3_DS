public class Q02_NodeSummary {
    // 必須包含題目定義的 Node 類別
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // 必須包含測試用的 main 方法
    public static void main(String[] args) {
        Node head = new Node(12);
        head.next = new Node(7);
        head.next.next = new Node(20);
        head.next.next.next = new Node(5);

        System.out.println("節點數：" + countNodes(head));
        System.out.println("資料總和：" + sumValues(head));
        System.out.println("空串列節點數：" + countNodes(null));
    }

    // 您要修改的 method 1
    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        
        // 修正：條件改為 current != null，確保算到最後一個節點，且能處理空串列
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // 您要修改的 method 2
    public static int sumValues(Node head) {
        int total = 0;
        Node current = head;
        
        // 修正：條件改為 current != null
        while (current != null) {
            total += current.data;
            current = current.next;
        }
        return total;
    }
}