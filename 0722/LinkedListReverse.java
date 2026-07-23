public class LinkedListReverse {
    public static void main(String[] args) {
        System.out.println("=== 測試多節點 ===");
        IntNode head1 = new IntNode(10);
        head1.next = new IntNode(20);
        head1.next.next = new IntNode(30);
        printList(head1);
        head1 = reverse(head1); // 接收反轉後的新 head
        printList(head1);

        System.out.println("\n=== 測試單一節點 ===");
        IntNode head2 = new IntNode(99);
        printList(head2);
        head2 = reverse(head2);
        printList(head2);

        System.out.println("\n=== 測試空串列 ===");
        IntNode head3 = null;
        printList(head3);
        head3 = reverse(head3);
        printList(head3);
    }

    public static IntNode reverse(IntNode head) {
        IntNode previous = null;
        IntNode current = head;

        while (current != null) {
            IntNode nextNode = current.next; // 1. 先保存原本的下一個節點
            current.next = previous;         // 2. 改變目前節點的指向（反向指回去）
            previous = current;              // 3. previous 向前推進
            current = nextNode;              // 4. current 向前推進
        }
        // 當 current 變成 null 時，迴圈結束，此時 previous 會停在原本的最後一個節點
        return previous; 
    }

    public static void printList(IntNode head) {
        if (head == null) {
            System.out.println("空串列 (null)");
            return;
        }
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}