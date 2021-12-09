package henrys.grocery;

import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.SoupAndBreadComboDiscount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFromReadme {
    Product soup = new Product("soup", 0.65);
    Product bread = new Product("bread", 0.80);
    Product milk = new Product("milk", 1.30);
    Product apples = new Product("apples", 0.10);
    Store store = new Store();

    @Before
    public void setUp() {
//        TODO: start and end date.
        Discount soupAndBread = new SoupAndBreadComboDiscount(soup, bread);

        store.addDiscount(soupAndBread);
    }

    @Test
    public void first() {
        Double expectedPrice = 3.15;

        Basket basket = new Basket();
        basket.addMany(3, soup);
        basket.addMany(2, bread);

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }
}
