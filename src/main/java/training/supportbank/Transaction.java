package training.supportbank;

import java.math.BigDecimal;
//import java.util.Date;
import java.time.LocalDate;

public class Transaction {
    final LocalDate date;
    final String from;
    final String to;
    final String narrative;
    final BigDecimal amount;

    public Transaction(LocalDate date, String from, String to, String narrative, BigDecimal amount) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = amount;
    }
}
