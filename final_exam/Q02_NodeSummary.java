public static int countNodes(Node head) {
    int count = 0;
    Node current = head;
    
    while (current != null) {
        count++;
        current = current.next;
    }
    return count;
}

public static int sumValues(Node head) {
    int total = 0;
    Node current = head;
    
    while (current != null) {
        total += current.data;
        current = current.next;
    }
    return total;
}