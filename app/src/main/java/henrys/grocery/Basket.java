package henrys.grocery;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Basket {
    List<Product> items = new ArrayList<>();

    public void add(Product product) {
        addMany(1, product);
    }

    public void addMany(int count, Product product) {
        IntStream.range(0, count).forEach(i -> items.add(product));
    }

    public List<Product> getItems() {
        return items;
    }

    public Long getCountOfProduct(Product product) {
        return items.stream().filter(item -> item.equals(product)).count();
    }
}
