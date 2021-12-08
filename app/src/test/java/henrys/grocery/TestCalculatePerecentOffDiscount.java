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
}
