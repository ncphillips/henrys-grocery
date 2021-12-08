package henrys.grocery;

public class Store {
    public Double calculateBasketPrice(Basket basket) {
        if (basket.getItemList().size() > 1) {
            return basket.getItemList().stream().map(Product::getPrice).reduce(0.0, Double::sum);
        }
        if (basket.getItemList().size() > 0) {
            return basket.getItemList().get(0).getPrice();
        }
        return 0.0;
    }
}
