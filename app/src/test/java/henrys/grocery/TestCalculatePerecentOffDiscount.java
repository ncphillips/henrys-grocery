package henrys.grocery;

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
        Basket basket = new Basket();
        basket.add(banana);

        Double discountTotal = discount.calculateTotalForBasket(basket);

        assertEquals(expectedTotal, discountTotal);
    }

    @Test
    public void percentOffTwoProductItems() {
        Double expectedTotal = 1.5;
        Product apple = new Product("apple", 1.5);
        Discount discount = new PercentOffDiscount(0.50, apple);
        Basket basket = new Basket();

        basket.addMany(2, apple);

        Double discountTotal = discount.calculateTotalForBasket(basket);

        assertEquals(expectedTotal, discountTotal);
    }
}
