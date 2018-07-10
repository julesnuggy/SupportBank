package training.supportbank;

import training.supportbank.models.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Provides static ExtractNames functions
public class ExtractNames {
    public static Set<String> getUniqueNames(List<Transaction> transactions) {
        Set<String> accountNames = new HashSet<>();

          for(Transaction transaction : transactions) {
              accountNames.add(transaction.getFrom());
              accountNames.add(transaction.getTo());
          }

        return accountNames;
    }
}
