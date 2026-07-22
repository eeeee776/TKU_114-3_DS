public class CartItem {
    private String code;
    private String name;
    private int price; // 單價
    private int quantity; // 數量

    public CartItem(String code, String name, int price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = Math.max(price, 0); // 防護：單價不能小於0
        this.quantity = Math.max(quantity, 1); // 防護：初始數量至少為1
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * 累加數量 (當重複加入相同商品時呼叫)
     */
    public void addQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    /**
     * 修改數量，並限制數量必須大於 0
     * @return 修改成功回傳 true，若傳入非法數量則回傳 false
     */
    public boolean setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
            return true;
        }
        return false; // 拒絕小於或等於 0 的更新
    }

    /**
     * 取得該商品的小計金額 (單價 * 數量)
     */
    public int getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "代碼: " + code + " | 名稱: " + name + " | 單價: $" + price + " | 數量: " + quantity + " | 小計: $" + getSubtotal();
    }
}