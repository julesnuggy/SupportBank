package training.supportbank;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Converts CSV transaction data to Transaction objects
public class DataConverter {
    public static List<Transaction> extractTransactions(BufferedReader reader) throws IOException, ParseException {
        String transactionLine;
        List<Transaction> transactions = new ArrayList<>();

        while ((transactionLine = reader.readLine()) != null) {
            String[] transactionProperties = transactionLine.split(",");
            String stringDate = transactionProperties[0].replace("\"", "");
            LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
            String from = transactionProperties[1];
            String to = transactionProperties[2];
            String narrative = transactionProperties[3];
            BigDecimal amount = new BigDecimal(transactionProperties[4].replace("\"", ""));
            Transaction transaction = new Transaction(date, from, to, narrative, amount);
            transactions.add(transaction);
        }
        return transactions;
    }
}