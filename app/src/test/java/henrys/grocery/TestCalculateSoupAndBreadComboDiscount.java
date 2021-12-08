package henrys.grocery;

import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.SoupAndBreadComboDiscount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculateSoupAndBreadComboDiscount {
    @Test
    public void twoSoupOneBread() {
        Double priceOfBread = 5.0;
        Double expectedDiscountToPrice = priceOfBread / 2.0;
        Product soup = new Product("soup", 2.0);
        Product bread = new Product("bread", priceOfBread);
        Discount discount = new SoupAndBreadComboDiscount();
        Basket basket = new Basket();
        basket.addMany(2, soup);
        basket.add(bread);

        Double discountToPrice = discount.calculateTotalForBasket(basket);

        assertEquals(expectedDiscountToPrice, discountToPrice);
    }
}
