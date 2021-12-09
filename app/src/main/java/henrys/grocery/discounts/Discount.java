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
        boolean hasStarted;
        boolean hasEnded;
        if (startDate != null) {
            hasStarted = LocalDate.now().isAfter(startDate.minusDays(1));
            return hasStarted;
        }
        if (endDate != null) {
            hasEnded = LocalDate.now().isBefore(endDate.plusDays(1));
            return hasEnded;
        }
        return true;
    }
}
