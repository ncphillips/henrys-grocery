package henrys.grocery;

import henrys.grocery.discounts.Discount;
import henrys.grocery.discounts.PercentOffDiscount;
import henrys.grocery.discounts.SoupAndBreadComboDiscount;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TestFromReadme {
    Product soup = new Product("soup", 0.65);
    Product bread = new Product("bread", 0.80);
    Product milk = new Product("milk", 1.30);
    Product apples = new Product("apples", 0.10);
    Store store = new Store();

    @Before
    public void setUp() {
        LocalDate comboStartDate = LocalDate.now().minusDays(1);
        LocalDate comboEndDate = comboStartDate.plusDays(7);
        Discount soupAndBread = new SoupAndBreadComboDiscount(soup, bread, comboStartDate, comboEndDate);

        LocalDate appleDiscountStartDate = LocalDate.now().plusDays(3);
        // TODO: This is a funky calculation. I don't totally trust it.
        // https://stackoverflow.com/questions/13624442/getting-last-day-of-the-month-in-a-given-string-date
        LocalDate appleDiscountEndDate = appleDiscountStartDate.withDayOfMonth(
                appleDiscountStartDate.getMonth().length(appleDiscountStartDate.isLeapYear())
        );

        Discount appleDiscount = new PercentOffDiscount(0.10, apples, appleDiscountStartDate, appleDiscountEndDate);

        store.addDiscount(soupAndBread);
        store.addDiscount(appleDiscount);
    }

    @Test
    public void first() {
        Double expectedPrice = 3.15;

        Basket basket = new Basket();
        basket.addMany(3, soup);
        basket.addMany(2, bread);

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void second() {
        Double expectedPrice = 1.9;

        Basket basket = new Basket();
        basket.addMany(6, apples);
        basket.add(milk);

        Double price = store.calculateBasketPrice(basket);

        assertEquals(expectedPrice, price);
    }

    @Test
    public void third() {
        Double expectedPrice = 1.84;

        Basket basket = new Basket();
        basket.addMany(6, apples);
        basket.add(milk);

        Double price = store.calculateBasketPrice(basket, LocalDate.now().plusDays(5));

        assertEquals(expectedPrice, price);
    }
    @Test
    public void fourth() {
        Double expectedPrice = 1.97;

        Basket basket = new Basket();
        basket.addMany(3, apples);
        basket.addMany(2, soup);
        basket.add(bread);

        Double price = store.calculateBasketPrice(basket, LocalDate.now().plusDays(5));

        assertEquals(expectedPrice, price);
    }
}
