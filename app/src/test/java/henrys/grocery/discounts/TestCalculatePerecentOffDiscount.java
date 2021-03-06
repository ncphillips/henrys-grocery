package henrys.grocery.discounts;

import henrys.grocery.Basket;
import henrys.grocery.Product;
import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.PercentOffDiscount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculatePerecentOffDiscount {
    @Test
    public void percentOffSingleProduct() {
        Double expectedTotal = 1.0;
        Product banana = new Product("banana", 4.0);
        Discount discount = new PercentOffDiscount(0.25, banana);
        Basket basket = new Basket() {{
            add(banana);
        }};

        Double discountTotal = discount.calculateTotalForBasket(basket);

        assertEquals(expectedTotal, discountTotal);
    }

    @Test
    public void percentOffTwoProductItems() {
        Double expectedTotal = 1.5;
        Product apple = new Product("apple", 1.5);
        Discount discount = new PercentOffDiscount(0.50, apple);
        Basket basket = new Basket() {{
            addMany(2, apple);
        }};

        Double discountTotal = discount.calculateTotalForBasket(basket);

        assertEquals(expectedTotal, discountTotal);
    }

    @Test
    public void percentOffSomeProducts() {
        Double expectedTotal = 2.0;
        Product pear = new Product("pear", 0.75);
        Product grapes = new Product("grapes", 5.0);
        Discount grapesDiscount = new PercentOffDiscount(0.2, grapes);
        Basket basket = new Basket() {{
            addMany(3, pear);
            addMany(2, grapes);
        }};

        Double discountTotal = grapesDiscount.calculateTotalForBasket(basket);

        assertEquals(expectedTotal, discountTotal);
    }

    @Test
    public void percentOffZeroProducts() {
        Double expectedTotal = 0.0;
        Product almonds = new Product("almonds", 2.30);
        Discount discount = new PercentOffDiscount(0.2, almonds);
        Basket basket = new Basket();

        Double discountTotal = discount.calculateTotalForBasket(basket);

        assertEquals(expectedTotal, discountTotal);
    }

}
