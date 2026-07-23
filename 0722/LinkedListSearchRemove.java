public class LinkedListSearchRemove {
    public static void main(String[] args) {
        // 建立測試串列: 10 -> 20 -> 30 -> 40 -> null
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.print("初始串列：");
        printList(head);

        // 測試搜尋
        System.out.println("\n包含 30？ " + contains(head, 30));
        System.out.println("包含 99？ " + contains(head, 99));

        // 測試刪除中間節點
        System.out.println("\n測試刪除中間節點 (30)：");
        head = removeValue(head, 30);
        printList(head);

        // 測試刪除最後節點
        System.out.println("測試刪除最後節點 (40)：");
        head = removeValue(head, 40);
        printList(head);

        // 測試刪除 head
        System.out.println("測試刪除 head (10)：");
        head = removeValue(head, 10);
        printList(head);

        // 測試刪除找不到的資料
        System.out.println("測試刪除找不到 (99)：");
        head = removeValue(head, 99);
        printList(head);
    }

    public static boolean contains(IntNode head, int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) return true;
            current = current.next;
        }
        return false;
    }

    public static IntNode removeValue(IntNode head, int target) {
        if (head == null) return null; // 空串列

        if (head.data == target) { // 刪除的是第一個節點
            return head.next;
        }

        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next; // 跨過要刪除的節點
                break; 
            }
            previous = current;
            current = current.next;
        }
        return head; // 回傳原本的 head
    }

    public static void printList(IntNode head) {
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}