package henrys.grocery.discounts;

import henrys.grocery.Basket;

public class SoupAndBreadComboDiscount extends Discount {
    @Override
    public Double calculateTotalForBasket(Basket basket) {
        return 2.5;
    }
}
