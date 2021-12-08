package henrys.grocery;

import henrys.grocery.discounts.Discount;

import java.util.ArrayList;

public class Store {
    ArrayList<Discount> discounts = new ArrayList<>();

    public Double calculateBasketPrice(Basket basket) {
        Double subtotal = basket.getItemList().stream().map(Product::getPrice).reduce(0.0, Double::sum);

        Double discountToPrice = discounts.stream().map(discount -> discount.calculateTotalForBasket(basket)).reduce(0.0, Double::sum);

        return subtotal - discountToPrice;
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }
}
