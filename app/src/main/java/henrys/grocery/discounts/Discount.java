package henrys.grocery.discounts;

import henrys.grocery.Basket;

import java.time.LocalDate;

public abstract class Discount {
    public abstract Double calculateTotalForBasket(Basket basket);
    protected LocalDate startDate;

    public Discount() { }
    public Discount(LocalDate startDate) {
       this.startDate = startDate;
    }


    public boolean isActive() {
        if (startDate != null) {
            return LocalDate.now().isAfter(startDate.minusDays(1));
        }
        return true;
    }
}
