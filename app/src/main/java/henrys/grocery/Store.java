package henrys.grocery;

import henrys.grocery.discounts.Discount;

public class Store {
    Discount discount;

    public Double calculateBasketPrice(Basket basket) {
        Double subtotal = basket.getItemList().stream().map(Product::getPrice).reduce(0.0, Double::sum);

        Double discountToPrice = 0.0;

        if (this.discount != null) {
            discountToPrice = discount.calculateTotalForBasket(basket);
        }

        return subtotal - discountToPrice;
    }

    public void addDiscount(Discount discount) {
        this.discount = discount;
    }
}
