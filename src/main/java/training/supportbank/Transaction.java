package training.supportbank;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    final LocalDate date;
    @SerializedName("fromAccount")
    final String from;
    @SerializedName("toAccount")
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
