package training.supportbank.transactionhandlers;

import training.supportbank.models.Transaction;

import java.math.BigDecimal;
import java.util.*;

public class ProcessTransactions {
    public static List<String> filterAccounts(List<Transaction> transactions, String accountName) {
        List<String> filteredAccountDetails = new ArrayList<>();

        for (Transaction transaction : transactions)
            if (transaction.getFrom().equals(accountName) || transaction.getTo().equals(accountName)) {
                filteredAccountDetails.add(Formatter.formatFilteredTransaction(transaction));
            }

        return filteredAccountDetails;
    }

    public static Map<String, BigDecimal> calculateBalances(List<Transaction> transactions, Set<String> accountNames) {
        Map<String, BigDecimal> accountBalances = new LinkedHashMap<>();

        for (String name : accountNames) {
            BigDecimal currentValue = BigDecimal.valueOf(0.00);

            for(Transaction transaction : transactions) {
                if (transaction.getFrom().equals(name)) {
                    currentValue = currentValue.add(transaction.getAmount());
                    accountBalances.put(name, currentValue);
                } else if (transaction.getTo().equals(name)) {
                    currentValue = currentValue.subtract(transaction.getAmount());
                    accountBalances.put(name, currentValue);
                }
            }
        }
        return accountBalances;
    }
}