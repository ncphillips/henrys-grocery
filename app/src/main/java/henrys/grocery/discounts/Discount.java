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
        return hasStarted() && !hasEnded();
    }

    private boolean hasStarted() {
        if (startDate != null) {
            return LocalDate.now().isAfter(startDate.minusDays(1));
        }
        return true;
    }

    private boolean hasEnded() {
        if (endDate != null) {
            return !LocalDate.now().isBefore(endDate.plusDays(1));
        }
        return false;
    }
}
