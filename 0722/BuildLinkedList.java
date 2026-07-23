public class BuildLinkedList {
    public static void main(String[] args) {
        // 1. 建立 10、20、30、40 四個 Node
        IntNode head = new IntNode(10);
        IntNode node2 = new IntNode(20);
        IntNode node3 = new IntNode(30);
        IntNode node4 = new IntNode(40);

        // 2. 使用 next 正確連接
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        // node4 的 next 在建構子中預設已是 null，不需特別指定

        // 3. 由 head 走訪輸出，並計算 (4) 節點數與總和
        IntNode current = head;
        int count = 0;
        int sum = 0;

        System.out.print("串列內容：");
        while (current != null) {
            System.out.print(current.data + " -> ");
            count++;
            sum += current.data;
            current = current.next; // 走到下一個節點
        }
        System.out.println("null");

        System.out.println("節點數：" + count);
        System.out.println("總和：" + sum);
    }
}