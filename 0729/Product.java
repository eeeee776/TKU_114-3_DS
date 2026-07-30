public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Product(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t$" + price + "\t" + stock;
    }
}