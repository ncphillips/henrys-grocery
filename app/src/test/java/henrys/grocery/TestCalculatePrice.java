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

    @Test
    public void multipleInstancesOfAProduct() {
        Double expectedPrice = 4.5;
        Product product = new Product("pear", 2.25);
        Store store = new Store();
        Basket basket = new Basket();

        basket.addMany(2, product);
        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void multipleProducts() {
        Double expectedPrice = 3.0;
        Product banana = new Product("banana", 1.0);
        Product grapefruit= new Product("grapefruit", 2.0);
        Store store = new Store();
        Basket basket = new Basket();

        basket.add(banana);
        basket.add(grapefruit);
        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void addingMoreOfTheFirstProduct() {
        Double expectedPrice = 5.0;
        Product raisins = new Product("raisins", 1.0);
        Product avacado = new Product("avacado", 1.0);
        Store store = new Store();
        Basket basket = new Basket();

        basket.add(raisins);
        basket.addMany(2, avacado);
        basket.addMany(2, raisins);
        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }


}
