package henrys.grocery.discounts;

import henrys.grocery.Basket;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class TestDiscountPeriod {
    static class ExampleDiscount extends Discount {
        public ExampleDiscount() { }
        public ExampleDiscount(LocalDate started) {
            super(started);
        }
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

    @Test
    public void startedYesterday() {
        LocalDate started = LocalDate.now().minusDays(1);

        Discount discount = new ExampleDiscount(started);

        assertTrue(discount.isActive());
    }
}
