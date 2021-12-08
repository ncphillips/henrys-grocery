package henrys.grocery;

public class Product {
    private final Double price;

    public Product(String name, Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}
