package henrys.grocery.discounts;

import henrys.grocery.Basket;
import henrys.grocery.Product;

import java.time.LocalDate;

public class SoupAndBreadComboDiscount extends Discount {
    Product soup;
    Product bread;

    public SoupAndBreadComboDiscount(Product soup, Product bread) {
        this.soup = soup;
        this.bread = bread;
    }

    public SoupAndBreadComboDiscount(Product soup, Product bread, LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
        this.soup = soup;
        this.bread = bread;
    }

    @Override
    public Double calculateTotalForBasket(Basket basket) {
        Double countOfSoup = basket.getCountOfProduct(soup).doubleValue();
        Double countOfBread = basket.getCountOfProduct(bread).doubleValue();

        Double maxApplies = Math.floor(countOfSoup / 2.0);
        Double numApplies = Math.min(maxApplies, countOfBread);

        return numApplies * bread.getPrice() / 2.0;
    }
}
