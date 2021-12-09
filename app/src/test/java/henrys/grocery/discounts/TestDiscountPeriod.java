package henrys.grocery.discounts;

import henrys.grocery.Basket;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDiscountPeriod {
    static class ExampleDiscount extends Discount {
        public ExampleDiscount() { }
        public ExampleDiscount(LocalDate started, LocalDate endsOn) {
            super(started, endsOn);
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

        Discount discount = new ExampleDiscount(started, null);

        assertTrue(discount.isActive());
    }

    @Test
    public void startingTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        Discount discount = new ExampleDiscount(tomorrow, null);

        assertFalse(discount.isActive());
    }

    @Test
    public void endedYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        Discount discount = new ExampleDiscount(null, yesterday);

        assertFalse(discount.isActive());
    }

    @Test
    public void checkingFutureState() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate nextWeek = LocalDate.now().plusDays(7);

        Discount discount = new ExampleDiscount(tomorrow, null);

        assertTrue(discount.isActive(nextWeek));
    }
}
