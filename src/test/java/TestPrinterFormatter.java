import org.junit.Test;
import training.supportbank.PrinterFormatter;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TestPrinterFormatter {
    private static Map<String, BigDecimal> testMapInput = new LinkedHashMap<>();

    @Test
    public void formatterConvertToCurrencyOutputsStringCurrencyValue() {
        PrinterFormatter formatter = new PrinterFormatter();
        String currencyValue = formatter.convertToCurrency(new BigDecimal("5.00"), Locale.UK);
        assertThat(currencyValue).isEqualTo("£5.00");
    }

    @Test
    public void formatterCreateScriptCreatesCreditString() {
        testMapInput.put("John D", new BigDecimal("5.00"));
        testMapInput.put("Jane D", new BigDecimal("-3.00"));
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("John D is owed £5.00");
        expectedOutput.add("Jane D owes £3.00");
        PrinterFormatter formatter = new PrinterFormatter();
        List<String> actualOutput = formatter.convertMapToSentence(testMapInput);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}

