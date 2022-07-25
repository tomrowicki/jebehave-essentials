package tomrowicki.jbehavedemo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {
    private final BigDecimal value;
    private final Currency currency = Currency.EUR;
    private LocalDate deadline;

    public Invoice (BigDecimal value) {
        this.value = value;
    }

    void setDeadline(int daysFromNow) {
        this.deadline = LocalDate.now().plusDays(daysFromNow);
    }
}
