package training.supportbank.models;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private final LocalDate date;
    @SerializedName("fromAccount")
    private final String from;
    @SerializedName("toAccount")
    private final String to;
    private final String narrative;
    private final BigDecimal amount;

    public LocalDate getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getNarrative() {
        return narrative;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction(LocalDate date, String from, String to, String narrative, BigDecimal amount) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = amount;
    }
}
