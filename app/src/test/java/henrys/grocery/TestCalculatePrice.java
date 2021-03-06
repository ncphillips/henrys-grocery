package henrys.grocery;

import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.PercentOffDiscount;
import henrys.grocery.discounts.SoupAndBreadComboDiscount;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TestCalculatePrice {
    LocalDate today = LocalDate.now();
    
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
        Basket basket = new Basket() {{ add(product); }};

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void conflictingPrices() {
        String productName = "coffee";
        Double firstPrice = 2.5;
        Double secondPrice = 3.0;
        Product version1 = new Product(productName, firstPrice);

        Basket basket = new Basket() {{ add(version1); }};

        Product version2 = new Product(productName, secondPrice);
        Store store = new Store() {{ addProduct(version2); }};

        Double price = store.calculateBasketPrice(basket);

        assertEquals(secondPrice, price);
    }


    @Test
    public void multipleInstancesOfAProduct() {
        Double expectedPrice = 4.5;
        Product product = new Product("pear", 2.25);
        Store store = new Store();
        Basket basket = new Basket() {{ addMany(2, product); }};

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void multipleProducts() {
        Double expectedPrice = 3.0;
        Product banana = new Product("banana", 1.0);
        Product grapefruit= new Product("grapefruit", 2.0);
        Store store = new Store();
        Basket basket = new Basket() {{
            add(banana);
            add(grapefruit);
        }};

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void addingMoreOfTheFirstProduct() {
        Double expectedPrice = 5.0;
        Product raisins = new Product("raisins", 1.0);
        Product avacado = new Product("avacado", 1.0);
        Store store = new Store();
        Basket basket = new Basket() {{
            add(raisins);
            addMany(2, avacado);
            addMany(2, raisins);
        }};

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void applyOneDiscount() {
        Double expectedPrice = 6.0;
        Product peanutButter = new Product("peanut butter", 8.0);
        Discount discount = new PercentOffDiscount(0.25, peanutButter);
        Store store = new Store() {{ addDiscount(discount); }};
        Basket basket = new Basket() {{ add(peanutButter); }};

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void applyMultipleDiscounts() {
        Double expectedPrice = 7.5 + (1.25 * 2.0) + (10.0 / 2.0);
        Product ranchDressing = new Product("ranch dressing", 10.0);
        Product soup = new Product("soup", 1.25);
        Product bread = new Product("bread", 10.0);
        Basket basket = new Basket() {{
            add(ranchDressing);
            add(bread);
            addMany(2, soup);
        }};

        Discount ranchDiscount = new PercentOffDiscount(0.25, ranchDressing);
        Discount soupAndBreadComboDiscount = new SoupAndBreadComboDiscount(soup, bread);
        Store store = new Store() {{
            addDiscount(ranchDiscount);
            addDiscount(soupAndBreadComboDiscount);
        }};

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void pendingDiscounts() {
        Double fullPrice = 5.0;
        Product carrots = new Product("carrots", fullPrice);
        Basket basket = new Basket() {{ add(carrots); }};

        Discount discount = new PercentOffDiscount(0.5, carrots, today.plusDays(3), null);
        Store store = new Store() {{ addDiscount(discount); }};
        Double price = store.calculateBasketPrice(basket);

        assertEquals(fullPrice, price);
    }

    @Test
    public void expiredDiscounts() {
        Double fullPrice = 5.0;
        Product carrots = new Product("carrots", fullPrice);
        Basket basket = new Basket() {{ add(carrots); }};

        Discount discount = new PercentOffDiscount(0.5, carrots, today.minusDays(10), today.minusDays(3));
        Store store = new Store() {{ addDiscount(discount); }};
        Double price = store.calculateBasketPrice(basket);

        assertEquals(fullPrice, price);
    }
}
