package training.supportbank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Provides static Helper functions
public class Helper {
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
}
