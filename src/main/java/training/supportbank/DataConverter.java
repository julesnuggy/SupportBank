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

    public static List<String> filterAccounts(List<Transaction> inputArray, String accountName) {
        List<String> filteredArray = new ArrayList<>();
        String filteredAccountDetails;
        for (Transaction tranElement : inputArray)
            if (tranElement.from.equals(accountName) || tranElement.to.equals(accountName)) {
                filteredAccountDetails = "[Date] " + tranElement.date + " [From] " + tranElement.from +
                    " [To] :" + tranElement.to + " [For] " + tranElement.narrative + " [Costing] " + tranElement.amount;
                filteredArray.add(filteredAccountDetails);
            }
        return filteredArray;
    }

    public static Map<String, BigDecimal> calculateBalance(List<Transaction> inputArray, Set<String> setOfNames) {
        Map<String, BigDecimal> mapOfUniqueBalances = getValues(inputArray, setOfNames);
        return mapOfUniqueBalances;
    }

    private static Map<String, BigDecimal> getValues(List<Transaction> transactions, Set<String> setOfNames) {
        Map<String, BigDecimal> outputMap = new LinkedHashMap<>();

        for (String name : setOfNames) {
            BigDecimal currVal = BigDecimal.valueOf(0.00);

            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).from.equals(name)) {
                    currVal = currVal.add(transactions.get(i).amount);
                } else if (transactions.get(i).to.equals(name)) {
                    currVal = currVal.subtract(transactions.get(i).amount);
                }
                outputMap.put(name, currVal);
            }
        }
        return outputMap;
    }
}