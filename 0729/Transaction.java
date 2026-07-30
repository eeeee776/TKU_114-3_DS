public class Transaction {
    private String txId;
    private String account;
    private int amount;
    private int timeSeq;

    public Transaction(String txId, String account, int amount, int timeSeq) {
        this.txId = txId;
        this.account = account;
        this.amount = amount;
        this.timeSeq = timeSeq;
    }

    public int getAmount() {
        return amount;
    }

    public int getTimeSeq() {
        return timeSeq;
    }

    @Override
    public String toString() {
        return "交易編號: " + txId + " | 帳號: " + account + " | 金額: $" + amount + " | 時間序號: " + timeSeq;
    }
}