package training.supportbank;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Converts CSV transaction data to Transaction objects
public class DataConverter {
    public static List<Transaction> extractTransactions(BufferedReader reader) throws IOException, ParseException {
        String transactionLine;
        List<Transaction> transactions = new ArrayList<>();

        while ((transactionLine = reader.readLine()) != null) {
            String[] transactionProperties = transactionLine.split(",");
            String stringDate = transactionProperties[0].replace("\"", "");
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
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