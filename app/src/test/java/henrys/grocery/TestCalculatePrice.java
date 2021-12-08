package henrys.grocery;

import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.PercentOffDiscount;
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

    @Test
    public void applyOneDiscount() {
        Double initialPrice = 8.0;
        Double percentOff = 0.25;
        Double expectedPrice = 6.0;
        Product peanutButter = new Product("peanut butter", initialPrice);
        Discount discount = new PercentOffDiscount(percentOff, peanutButter);
        Store store = new Store();
        Basket basket = new Basket();

        store.addDiscount(discount);
        basket.add(peanutButter);
        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void applyMultipleDiscounts() {
        Double expectedPrice = 7.5 + 4.0;

        Double initialRanchPrice = 10.0;
        Double percentOffRanch = 0.25;
        Product ranchDressing = new Product("ranch dressing", initialRanchPrice);
        Discount ranchDiscount = new PercentOffDiscount(percentOffRanch, ranchDressing);

        Double initialPicklesPrice = 5.0;
        Double percentOffPickles = 0.20;
        Product pickles = new Product("pickles", initialPicklesPrice);
        Discount picklesDiscount = new PercentOffDiscount(percentOffPickles, pickles);

        Store store = new Store();
        store.addDiscount(picklesDiscount);
        store.addDiscount(ranchDiscount);

        Basket basket = new Basket();
        basket.add(ranchDressing);
        basket.add(pickles);

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }
}
