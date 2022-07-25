package tomrowicki.jbehavedemo.domain;

public class TierInitializer {
    public void initializeServiceTierForClient(Client client, ServiceTier tier) {
        client.setTier(tier);
    }
}
