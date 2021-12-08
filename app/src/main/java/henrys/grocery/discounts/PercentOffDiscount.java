package henrys.grocery.discounts;

import henrys.grocery.Basket;
import henrys.grocery.Product;

public class PercentOffDiscount extends Discount {
    public PercentOffDiscount(Double percentOff, Product banana) {
    }


    @Override
    public Double calculateTotalForBasket(Basket basket) {
        return 1.0;
    }
}
