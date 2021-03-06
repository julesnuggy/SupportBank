package training.supportbank;

import java.math.BigDecimal;
import java.util.*;

public class Formatter {

    public static String convertToCurrency(BigDecimal value, Locale locale) {
        String currencyValueString;
        String outputCurrency = Currency.getInstance(locale).getSymbol(locale);
        currencyValueString = outputCurrency + value.setScale(2).abs().toString();
        return currencyValueString;
    }

    public static List<String> convertMapToSentence(Map<String, BigDecimal> uniqueNameSubtotalMap) {
        List<String> uniqueNameSubtotalSentenceArray = new ArrayList<>();
        uniqueNameSubtotalMap.forEach((key, value) -> {
            String operand = value.compareTo(new BigDecimal(0)) < 0 ? " owes " : " is owed ";
            uniqueNameSubtotalSentenceArray.add(key + operand + convertToCurrency(value, Locale.UK));
        });

        return uniqueNameSubtotalSentenceArray;
    }

    public static String formatFilteredTransaction(Transaction transaction) {
        String formattedTransaction = "[Date] " + transaction.date + " [From] " + transaction.from + " [To] :" +
                transaction.to + " [For] " + transaction.narrative + " [Costing] " + transaction.amount;

        return formattedTransaction;
    }

}
