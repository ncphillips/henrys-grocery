package henrys.grocery.discounts;

import henrys.grocery.Basket;
import henrys.grocery.Product;

public class SoupAndBreadComboDiscount extends Discount {
    Product soup;
    Product bread;

    public SoupAndBreadComboDiscount(Product soup, Product bread) {
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
