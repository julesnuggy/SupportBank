package training.supportbank;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    final Date date;
    final String from;
    final String to;
    final String narrative;
    final BigDecimal amount;

    public Transaction(Date tranDate, String tranFrom, String tranTo, String tranNarrative, BigDecimal tranAmount) {
        this.date = tranDate;
        this.from = tranFrom;
        this.to = tranTo;
        this.narrative = tranNarrative;
        this.amount = tranAmount;
    }
}
