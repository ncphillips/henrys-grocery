package henrys.grocery.discounts;

import henrys.grocery.Basket;

import java.time.LocalDate;

public abstract class Discount {
    public abstract Double calculateTotalForBasket(Basket basket);

    public Discount() { }
    public Discount(LocalDate startDate) { }


    public boolean isActive() {
        return true;
    }
}
