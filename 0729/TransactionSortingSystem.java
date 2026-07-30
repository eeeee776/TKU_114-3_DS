public class TransactionSortingSystem {
    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("TX01", "A001", 5000, 1),
            new Transaction("TX02", "A002", 15000, 2),
            new Transaction("TX03", "A003", 5000, 3), // 金額同 TX01，時間較晚
            new Transaction("TX04", "A001", 8000, 4),
            new Transaction("TX05", "A004", 15000, 5) // 金額同 TX02，時間較晚
        };

        insertionSortTransactions(transactions);

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public static void insertionSortTransactions(Transaction[] values) {
        for (int index = 1; index < values.length; index++) {
            Transaction key = values[index];
            int position = index - 1;

            while (position >= 0) {
                Transaction current = values[position];
                boolean shouldMove = false;
                
                // 主要條件：金額降冪
                if (current.getAmount() < key.getAmount()) {
                    shouldMove = true;
                } 
                // 次要條件：金額相同時，依時間序號升冪
                else if (current.getAmount() == key.getAmount() && current.getTimeSeq() > key.getTimeSeq()) {
                    shouldMove = true;
                }

                if (shouldMove) {
                    values[position + 1] = values[position];
                    position--;
                } else {
                    break;
                }
            }
            values[position + 1] = key;
        }
    }
}