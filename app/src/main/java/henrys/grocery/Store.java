package henrys.grocery;

public class Store {
    public Double calculateBasketPrice(Basket basket) {
        if (basket.getItemList().size() > 0) {
            return basket.getItemList().get(0).getPrice();
        }
        return 0.0;
    }
}
