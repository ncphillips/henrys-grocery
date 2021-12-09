package henrys.grocery.discounts;

import henrys.grocery.Basket;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestDiscountPeriod {
    static class ExampleDiscount extends Discount {
        @Override
        public Double calculateTotalForBasket(Basket basket) {
            return 0.0;
        }
    }

    @Test
    public void noDatesSpecified() {
        Discount discount = new ExampleDiscount();

        assertTrue(discount.isActive());
    }
}
