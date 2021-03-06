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
        return isActive(LocalDate.now());
    }

    public boolean isActive(LocalDate date) {
        return hasStarted(date) && !hasEnded(date);
    }

    private boolean hasStarted(LocalDate date) {
        if (startDate == null) {
            return true;
        }

        return date.isAfter(startDate.minusDays(1));
    }

    private boolean hasEnded(LocalDate date) {
        if (endDate == null) {
            return false;
        }

        return !date.isBefore(endDate.plusDays(1));
    }
}
