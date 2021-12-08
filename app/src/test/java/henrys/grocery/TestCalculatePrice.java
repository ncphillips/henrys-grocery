package henrys.grocery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculatePrice {
    @Test
    public void emptyBasket() {
        Double expectedPrice = 0.0;
        Store store = new Store();
        Basket basket = new Basket();

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }
}
