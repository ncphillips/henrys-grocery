package henrys.grocery.discounts;

import henrys.grocery.Basket;

import java.time.LocalDate;

public abstract class Discount {
    public abstract Double calculateTotalForBasket(Basket basket);
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Discount() { }
    public Discount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public boolean isActive() {
        if (startDate != null) {
            boolean hasStarted;
            hasStarted = LocalDate.now().isAfter(startDate.minusDays(1));
            return hasStarted;
        }
        if (endDate != null) {
            boolean hasEnded;
            hasEnded = LocalDate.now().isBefore(endDate.plusDays(1));
            return hasEnded;
        }
        return true;
    }
}
