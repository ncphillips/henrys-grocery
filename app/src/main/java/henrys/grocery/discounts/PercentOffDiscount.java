package henrys.grocery.discounts;

import henrys.grocery.Basket;
import henrys.grocery.Product;

public class PercentOffDiscount extends Discount {
    private final Product product;
    private final Double percentOff;

    public PercentOffDiscount(Double percentOff, Product product) {
        this.product = product;
        this.percentOff = percentOff;
    }


    @Override
    public Double calculateTotalForBasket(Basket basket) {
        double productCount = basket.getCountOfProduct(product).doubleValue();

        return productCount * product.getPrice() * percentOff;
    }
}
