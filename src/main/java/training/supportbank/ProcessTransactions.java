package training.supportbank;

import java.math.BigDecimal;
import java.util.*;

public class ProcessTransactions {
    public static List<String> filterAccounts(List<Transaction> transactions, String accountName) {
        List<String> filteredAccountDetails = new ArrayList<>();

        for (Transaction transaction : transactions)
            if (transaction.from.equals(accountName) || transaction.to.equals(accountName)) {
                filteredAccountDetails.add(Formatter.formatFilteredAccounts(transaction));
            }

        return filteredAccountDetails;
    }

    public static Map<String, BigDecimal> calculateBalances(List<Transaction> transactions, Set<String> accountNames) {
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
