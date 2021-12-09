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
        Basket basket = new Basket() {{
            addMany(3, soup);
            addMany(2, bread);
        }};

        Double price = store.calculateBasketPrice(basket);

        Double expectedPrice = 3.15;
        assertEquals(expectedPrice, price);
    }

    @Test
    public void second() {
        Basket basket = new Basket() {{
            addMany(6, apples);
            add(milk);
        }};

        Double price = store.calculateBasketPrice(basket);

        Double expectedPrice = 1.9;
        assertEquals(expectedPrice, price);
    }

    @Test
    public void third() {
        Basket basket = new Basket() {{
            addMany(6, apples);
            add(milk);
        }};

        Double price = store.calculateBasketPrice(basket, LocalDate.now().plusDays(5));

        Double expectedPrice = 1.84;
        assertEquals(expectedPrice, price);
    }

    @Test
    public void fourth() {
        Basket basket = new Basket() {{
            addMany(3, apples);
            addMany(2, soup);
            add(bread);
        }};

        Double price = store.calculateBasketPrice(basket, LocalDate.now().plusDays(5));

        Double expectedPrice = 1.97;
        assertEquals(expectedPrice, price);
    }
}
