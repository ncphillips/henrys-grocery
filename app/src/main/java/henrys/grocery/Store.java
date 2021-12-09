package henrys.grocery;

import henrys.grocery.discounts.Discount;

import java.time.LocalDate;
import java.util.*;

public class Store {
    ArrayList<Discount> discounts = new ArrayList<>();
    Map<String, Product> products = new HashMap<>();

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public Double calculateBasketPrice(Basket basket) {
        LocalDate date = LocalDate.now();

        return getSubtotal(basket) - getDiscountToPrice(basket, date);
    }

    public Double calculateBasketPrice(Basket basket, LocalDate date) {
        Double total = (getSubtotal(basket) - getDiscountToPrice(basket, date)) * 100.0;

        return Math.round(total) / 100.0;
    }

    private Double getSubtotal(Basket basket) {
        return basket.getItems().stream().map(this::getProductPrice).reduce(0.0, Double::sum);
    }

    private Double getProductPrice(Product product) {
        Product storesRecord = getProduct(product.getName());

        return Objects.requireNonNullElse(storesRecord, product).getPrice();
    }

    private Double getDiscountToPrice(Basket basket, LocalDate date) {
        return getActiveDiscounts(date).stream().map(discount -> discount.calculateTotalForBasket(basket)).reduce(0.0, Double::sum);
    }

    private List<Discount> getActiveDiscounts(LocalDate date) {
        return discounts.stream().filter(discount -> discount.isActive(date)).toList();
    }

}
