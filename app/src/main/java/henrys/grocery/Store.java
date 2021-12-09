package henrys.grocery;

import henrys.grocery.discounts.Discount;

import java.util.*;

public class Store {
    ArrayList<Discount> discounts = new ArrayList<>();
    Map<String, Product> products = new HashMap<>();

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public void addProduct(Product product) {
        products.put(product.getID(), product);
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public Double calculateBasketPrice(Basket basket) {
        return getSubtotal(basket) - getDiscountToPrice(basket);
    }

    private Double getSubtotal(Basket basket) {
        return basket.getItemList().stream().map(this::getProductPrice).reduce(0.0, Double::sum);
    }

    private Double getProductPrice(Product product) {
        Product storesRecord = getProduct(product.getID());

        return Objects.requireNonNullElse(storesRecord, product).getPrice();
    }

    private Double getDiscountToPrice(Basket basket) {
        return getActiveDiscounts().stream().map(discount -> discount.calculateTotalForBasket(basket)).reduce(0.0, Double::sum);
    }

    private List<Discount> getActiveDiscounts() {
        return discounts.stream().filter(Discount::isActive).toList();
    }

}
