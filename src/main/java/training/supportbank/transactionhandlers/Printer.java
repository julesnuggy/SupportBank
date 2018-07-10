package training.supportbank.transactionhandlers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Printer {
    public static void listAll(Map<String, BigDecimal> accountBalances) {
        List<String> accountBalancesSentence = Formatter.convertMapToSentence(accountBalances);
        for(String element : accountBalancesSentence) {
            System.out.println(element);
        }
    }

    public static void listAccount(List<String> filteredTransactions, String accountName) {
        for(String element : filteredTransactions) {
            System.out.println(element);
        }
    }
}
