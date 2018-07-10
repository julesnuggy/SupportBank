package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// Converts CSV transaction data to Transaction objects
public class DataConverter {
    private static final Logger logger = LogManager.getLogger();

    public static List<Transaction> extractTransactions(BufferedReader reader) throws IOException, ParseException {
        String transactionLine;
        List<Transaction> transactions = new ArrayList<>();

        while ((transactionLine = reader.readLine()) != null) {
            try {
                String[] transactionProperties = transactionLine.split(",");
                String stringDate = transactionProperties[0].replace("\"", "");
                LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String from = transactionProperties[1];
                String to = transactionProperties[2];
                String narrative = transactionProperties[3];
                BigDecimal amount = new BigDecimal(transactionProperties[4].replace("\"", ""));
                Transaction transaction = new Transaction(date, from, to, narrative, amount);
                transactions.add(transaction);
            } catch (NumberFormatException | DateTimeParseException e) {
                logger.error(e.getMessage() + "\nTransaction omitted from calculation: " + transactionLine + "\n");
            }
        }
        return transactions;
    }
}