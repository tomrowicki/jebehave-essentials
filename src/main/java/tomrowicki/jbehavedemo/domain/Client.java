package tomrowicki.jbehavedemo.domain;

public class Client {
    private int id;
    private Discount discount;
    private ServiceTier tier;

    private Invoice lastInvoice;

    public Client(int id, Discount discount, ServiceTier tier) {
        this.id = id;
        this.discount = discount;
        this.tier = tier;
    }

    void setDiscount(Discount discount) {
        this.discount = discount;
    }

    void setTier(ServiceTier tier) {
        this.tier = tier;
    }

    void setInvoice(Invoice invoice) {
        this.lastInvoice = invoice;
    }

    ServiceTier getServiceTier() {
        return tier;
    }

    Discount getDiscount() {
        return discount;
    }

    public Invoice getLastInvoice() {
        return lastInvoice;
    }

    public Discount getCurrentDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", discount=" + discount +
                ", tier=" + tier +
                '}';
    }
}
