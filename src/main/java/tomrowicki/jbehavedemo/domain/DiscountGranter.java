package tomrowicki.jbehavedemo.domain;

public class DiscountGranter {

    public void grantDiscount(Client client, Discount discount) {
        client.setDiscount(discount);
    }
}
