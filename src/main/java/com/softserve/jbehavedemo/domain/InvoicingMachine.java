package com.softserve.jbehavedemo.domain;

import java.math.BigDecimal;

public class InvoicingMachine {

    public void invoiceClient(Client client) {
        var discount = client.getDiscount();
        var tier = client.getServiceTier();
        var invoice = generateInvoice(discount, tier);

        client.setInvoice(invoice);

        client.setDiscount(Discount.NONE);
    }

    private Invoice generateInvoice(Discount discount, ServiceTier tier) {
        Invoice invoice = null;
        if (discount.equals(Discount.NEWCOMER_DISCOUNT) && tier.equals(ServiceTier.STARTER)) {
            invoice =  new Invoice(BigDecimal.valueOf(20.99));
        } else if (tier.equals(ServiceTier.PRO)) {
            invoice =  new Invoice(BigDecimal.valueOf(40.99));
        } else if (tier.equals(ServiceTier.ULTRAVIP)) {
            invoice =  new Invoice(BigDecimal.valueOf(99.99));
        }
        invoice.setDeadline(30);
        return invoice;
    }
}
