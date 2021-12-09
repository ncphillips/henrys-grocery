package henrys.grocery;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    List<Product> items = new ArrayList<>();

    public void add(Product product) {
        addMany(1, product);
    }

    public void addMany(int count, Product product) {
        for (int j = 0; j < count; j++) {
            items.add(product);
        }
    }

    public List<Product> getItems() {
        return items;
    }

    public Long getCountOfProduct(Product product) {
        return items.stream().filter(item -> item.getID().equals(product.getID())).count();
    }
}
