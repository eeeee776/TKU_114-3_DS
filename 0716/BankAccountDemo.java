public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("A001", "Alice", 5000);
        BankAccount acc2 = new BankAccount("A002", "Bob", 1000);

        System.out.println("初始狀態：");
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println("-------------------------");

        System.out.println("Alice 存款 2000: " + acc1.deposit(2000));
        System.out.println("Bob 提款 500: " + acc2.withdraw(500));
        System.out.println("Alice 成功轉帳 1500 給 Bob: " + acc1.transferTo(acc2, 1500));
        System.out.println("Bob 失敗轉帳 (餘額不足) 5000 給 Alice: " + acc2.transferTo(acc1, 5000));
        
        System.out.println("-------------------------");
        System.out.println("最終狀態：");
        System.out.println(acc1);
        System.out.println(acc2);
    }
}