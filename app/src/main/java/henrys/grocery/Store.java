package henrys.grocery;

public class Store {
    public Double calculateBasketPrice(Basket basket) {
        return basket.getItemList().stream().map(Product::getPrice).reduce(0.0, Double::sum);
    }
}
