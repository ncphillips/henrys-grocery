package henrys.grocery.discounts;

import henrys.grocery.Basket;
import henrys.grocery.Product;

import java.time.LocalDate;

public class PercentOffDiscount extends Discount {
    private final Product product;
    private final Double percentOff;

    public PercentOffDiscount(Double percentOff, Product product) {
        this(percentOff, product, null, null);
    }

    public PercentOffDiscount(Double percentOff, Product product, LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
        this.product = product;
        this.percentOff = percentOff;
    }


    @Override
    public Double calculateTotalForBasket(Basket basket) {
        double productCount = basket.getCountOfProduct(product).doubleValue();

        return productCount * product.getPrice() * percentOff;
    }
}
