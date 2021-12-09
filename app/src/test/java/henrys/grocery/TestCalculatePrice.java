package henrys.grocery;

import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.PercentOffDiscount;
import henrys.grocery.discounts.SoupAndBreadComboDiscount;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

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
    public void conflictingPrices() {
        String productId = "coffee";

        Double firstPrice = 2.5;
        Product version1 = new Product(productId, firstPrice);

        Basket basket = new Basket();
        basket.add(version1);

        Double secondPrice = 3.0;
        Product version2 = new Product(productId, secondPrice);
        Store store = new Store();
        store.addProduct(version2);

        Double price = store.calculateBasketPrice(basket);

        assertEquals(secondPrice, price);
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
        Double expectedPrice = 7.5 + (1.25 * 2.0) + (10.0 / 2.0);

        Double initialRanchPrice = 10.0;
        Double percentOffRanch = 0.25;
        Product ranchDressing = new Product("ranch dressing", initialRanchPrice);
        Discount ranchDiscount = new PercentOffDiscount(percentOffRanch, ranchDressing);

        Double initialPriceOfBread = 10.0;
        Product soup = new Product("soup", 1.25);
        Product bread = new Product("bread", initialPriceOfBread);
        Discount soupAndBreadComboDiscount = new SoupAndBreadComboDiscount(soup, bread);

        Store store = new Store();
        store.addDiscount(ranchDiscount);
        store.addDiscount(soupAndBreadComboDiscount);

        Basket basket = new Basket();
        basket.add(ranchDressing);
        basket.add(bread);
        basket.addMany(2, soup);

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void pendingDiscounts() {
        Double fullPrice = 5.0;
        Product carrots = new Product("carrots", fullPrice);
        Basket basket = new Basket();
        basket.add(carrots);
        Store store = new Store();

        Discount discount = new PercentOffDiscount(0.5, carrots, LocalDate.now().plusDays(3), null);
        store.addDiscount(discount);
        Double price = store.calculateBasketPrice(basket);

        assertEquals(fullPrice, price);
    }

    @Test
    public void expiredDiscounts() {
        Double fullPrice = 5.0;
        Product carrots = new Product("carrots", fullPrice);
        Basket basket = new Basket();
        basket.add(carrots);
        Store store = new Store();

        Discount discount = new PercentOffDiscount(0.5, carrots, LocalDate.now().minusDays(10), LocalDate.now().minusDays(3));
        store.addDiscount(discount);
        Double price = store.calculateBasketPrice(basket);

        assertEquals(fullPrice, price);
    }
}
