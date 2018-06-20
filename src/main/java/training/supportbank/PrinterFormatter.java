package training.supportbank;

import java.math.BigDecimal;
import java.util.*;

public class PrinterFormatter {

    public static String convertToCurrency(BigDecimal value, Locale locale) {
        String outputValue;
        String outputCurrency = Currency.getInstance(locale).getSymbol(locale);
        outputValue = outputCurrency + value.abs().toString();
        return outputValue;
    }

    public static List<String> createScript(Map<String, BigDecimal> calculatedMap) {
        List<String> outputArray = new ArrayList<>();
        calculatedMap.forEach((key, value) -> {
            String operand = value.compareTo(new BigDecimal(0)) < 0 ? " owes " : " is owed ";
            outputArray.add(key + operand + convertToCurrency(value, Locale.UK));
        });

        return outputArray;
    }

}
