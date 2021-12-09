package henrys.grocery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestManagingTheStore {
    @Test
    public void addingProducts() {
        Store store = new Store();
        Product apple = new Product("apple", 0.75);

        store.addProduct(apple);

        assertEquals(apple, store.getProduct("apple"));
    }
}
