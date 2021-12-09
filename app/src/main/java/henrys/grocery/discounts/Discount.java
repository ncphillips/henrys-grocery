package henrys.grocery.discounts;

import henrys.grocery.Basket;

public abstract class Discount {
    public abstract Double calculateTotalForBasket(Basket basket);

    public boolean isActive() {
        return true;
    }
}
