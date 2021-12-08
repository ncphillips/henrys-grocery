package henrys.grocery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculatePrice {
    @Test
    public void zeroProducts() {
        Double expectedPrice = 0.0;
        Store store = new Store();
        Basket basket = new Basket();

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void oneProduct() {
        Double expectedPrice = 2.5;
        Product product = new Product("orange", expectedPrice);
        Store store = new Store();
        Basket basket = new Basket();
        basket.add(product);

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }
}
