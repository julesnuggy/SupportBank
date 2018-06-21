package training.supportbank;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataConverter {
    public static List<Transaction> dataConverter(BufferedReader reader) throws IOException, ParseException {
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

    public static Set<String> extractNames(List<Transaction> transactions) {
        Set<String> accountNames = new HashSet<>();

        for (int i = 0; i < transactions.size(); i++) {
            for(Transaction transaction : transactions) {
                accountNames.add(transaction.from);
                accountNames.add(transaction.to);
            }
        }
        return accountNames;
    }

    public static List<String> filterAccounts(List<Transaction> transactions, String accountName) {
        List<String> filteredAccounts = new ArrayList<>();
        String filteredAccountDetails;
        for (Transaction transaction : transactions)
            if (transaction.from.equals(accountName) || transaction.to.equals(accountName)) {
                filteredAccountDetails = "[Date] " + transaction.date + " [From] " + transaction.from +
                    " [To] :" + transaction.to + " [For] " + transaction.narrative + " [Costing] " + transaction.amount;
                filteredAccounts.add(filteredAccountDetails);
            }
        return filteredAccounts;
    }

    public static Map<String, BigDecimal> calculateBalance(List<Transaction> transactions, Set<String> accountNames) {
        Map<String, BigDecimal> accountBalances = new LinkedHashMap<>();

        for (String name : accountNames) {
            BigDecimal currentValue = BigDecimal.valueOf(0.00);

            for(Transaction transaction : transactions) {
                if (transaction.from.equals(name)) {
                    currentValue = currentValue.add(transaction.amount);
                } else if (transaction.to.equals(name)) {
                    currentValue = currentValue.subtract(transaction.amount);
                }
                accountBalances.put(name, currentValue);
            }
        }
        return accountBalances;
    }
}