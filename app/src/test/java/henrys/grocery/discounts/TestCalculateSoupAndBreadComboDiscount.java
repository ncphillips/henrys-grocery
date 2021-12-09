package henrys.grocery.discounts;

import henrys.grocery.Basket;
import henrys.grocery.Product;
import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.SoupAndBreadComboDiscount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculateSoupAndBreadComboDiscount {
    @Test
    public void twoSoupOneBread() {
        Double priceOfBread = 5.0;
        Double expectedDiscountToPrice = priceOfBread / 2.0;
        Product soup = new Product("soup", 2.0);
        Product bread = new Product("bread", priceOfBread);
        Discount discount = new SoupAndBreadComboDiscount(soup, bread);

        Basket basket = new Basket() {{
            addMany(2, soup);
            add(bread);
        }};
        Double discountToPrice = discount.calculateTotalForBasket(basket);

        assertEquals(expectedDiscountToPrice, discountToPrice);
    }

    @Test
    public void oneSoupOneBread() {
        Double priceOfBread = 10.0;
        Double noDiscount = 0.0;
        Product soup = new Product("soup", 5.0);
        Product bread = new Product("bread", priceOfBread);
        Discount discount = new SoupAndBreadComboDiscount(soup, bread);

        Basket basket = new Basket() {{
            add(soup);
            add(bread);
        }};
        Double discountToPrice = discount.calculateTotalForBasket(basket);

        assertEquals(noDiscount, discountToPrice);
    }

    @Test
    public void fourSoupTwoBread() {
        Double priceOfBread = 6.0;
        Double expectedDiscountToPrice = 6.0;
        Product soup = new Product("soup", 2.5);
        Product bread = new Product("bread", priceOfBread);
        Discount discount = new SoupAndBreadComboDiscount(soup, bread);

        Basket basket = new Basket() {{
            addMany(4, soup);
            addMany(2, bread);
        }};
        Double discountToPrice = discount.calculateTotalForBasket(basket);

        assertEquals(expectedDiscountToPrice, discountToPrice);
    }

    @Test
    public void twoSoupTwoBread() {
        Double priceOfBread = 8.8;
        Double expectedDiscountToPrice = 4.4;
        Product soup = new Product("soup", 2.5);
        Product bread = new Product("bread", priceOfBread);
        Discount discount = new SoupAndBreadComboDiscount(soup, bread);

        Basket basket = new Basket() {{
            addMany(2, soup);
            addMany(2, bread);
        }};
        Double discountToPrice = discount.calculateTotalForBasket(basket);

        assertEquals(expectedDiscountToPrice, discountToPrice);
    }

    @Test
    public void fourSoupOneBread() {
        Double priceOfBread = 4.0;
        Double expectedDiscountToPrice = 2.0;
        Product soup = new Product("soup", 2.5);
        Product bread = new Product("bread", priceOfBread);
        Discount discount = new SoupAndBreadComboDiscount(soup, bread);

        Basket basket = new Basket() {{
            addMany(4, soup);
            addMany(1, bread);
        }};
        Double discountToPrice = discount.calculateTotalForBasket(basket);

        assertEquals(expectedDiscountToPrice, discountToPrice);
    }
}