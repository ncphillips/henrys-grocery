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
        int countOfSoup = basket.getCountOfProduct(soup).intValue();
        int countOfBread = basket.getCountOfProduct(bread).intValue();

        if (countOfBread == 1 && countOfSoup == 2) {
            return bread.getPrice() / 2.0;
        }

        int maxDiscountApplications = countOfSoup / 2;
        int numDiscountApplications = Math.min(maxDiscountApplications, countOfBread);

        return numDiscountApplications * bread.getPrice() / 2.0;
    }
}
