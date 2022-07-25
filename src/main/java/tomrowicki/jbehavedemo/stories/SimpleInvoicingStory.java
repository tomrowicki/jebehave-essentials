package tomrowicki.jbehavedemo.stories;

import tomrowicki.jbehavedemo.db.PseudoDb;
import org.jbehave.core.annotations.*;
import tomrowicki.jbehavedemo.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleInvoicingStory {

    // TODO push to github
    private PseudoDb db;
    private DiscountGranter discountGranter;
    private TierInitializer tierInitializer;
    private InvoicingMachine invoicingMachine;

    @BeforeScenario
    public void init() {
        System.out.println("Establishing simulated db connection...");
        db = new PseudoDb();
        System.out.println("Starting discount granter...");
        discountGranter = new DiscountGranter();
        System.out.println("Initializing discount initializer...");
        tierInitializer = new TierInitializer();
        System.out.println("MacGyvering invoicing machine...");
        invoicingMachine = new InvoicingMachine();
    }

    private Client clientUnderTest;

    @Given("a client with the id $clientId, who just subscribed to $serviceTier service")
    public void establishClient(int clientId, ServiceTier serviceTier) {
        Client foundClient = db.findClientWithId(clientId);
        System.out.println("Got client: " + foundClient);

        tierInitializer.initializeServiceTierForClient(foundClient, serviceTier);
        System.out.println("Granted " + serviceTier + " service tier");
        clientUnderTest = foundClient;
    }

    @Given("he was provided with discount $discount")
    public void addDiscount(Discount discount) {
        discountGranter.grantDiscount(clientUnderTest, discount);
        System.out.println("Client with a newly granted discount: " + clientUnderTest);
    }

    @When("the client gets invoiced")
    public void invoiceTheClient() {
        System.out.println("Invoicing now!");
        invoicingMachine.invoiceClient(clientUnderTest);

    }

    @Then("they should pay $value $currency in $days days")
    public void validateValueAndDeadline(BigDecimal value, Currency currency, int days) {
        var currentInvoice = clientUnderTest.getLastInvoice();
        assertThat(currentInvoice).hasFieldOrPropertyWithValue("value", value);
        assertThat(currentInvoice).hasFieldOrPropertyWithValue("currency", currency);
        assertThat(currentInvoice).hasFieldOrPropertyWithValue("deadline", LocalDate.now().plusDays(days));
        System.out.println("The client is going to have to pay " + value + currency + " until " + LocalDate.now().plusDays(days));
    }

    @Then("the discount associated with their account should be $newDiscount")
    public void validateDiscount(Discount newDiscount) {
        var currentDiscount = clientUnderTest.getCurrentDiscount();
        assertThat(currentDiscount).isEqualTo(newDiscount);
    }

    @Then("they should pay $value")
    public void validateParameterizedValue(BigDecimal value) {
        var currentInvoice = clientUnderTest.getLastInvoice();
        assertThat(currentInvoice).hasFieldOrPropertyWithValue("value", value);
    }

    @AfterScenario
    public void cleanUp() {
        System.out.println("Shutting down database connection... ");
        db = null;
        discountGranter = null;
    }
}
