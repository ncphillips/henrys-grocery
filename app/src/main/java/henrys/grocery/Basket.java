package henrys.grocery;

import java.util.ArrayList;

public class Basket {
    ArrayList<Product> products = new ArrayList<>();

    public void add(Product product) {
        addMany(1, product);
    }

    public void addMany(int count, Product product) {
        for (int j = 0; j < count; j++) {
            products.add(product);
        }
    }

    public ArrayList<Product> getItemList() {
        return products;
    }

    public Long getCountOfProduct(Product product) {
        return products.stream().filter(item -> item.getID().equals(product.getID())).count();
    }
}
