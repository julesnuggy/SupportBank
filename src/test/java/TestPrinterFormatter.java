import org.junit.Test;
import training.supportbank.PrinterFormatter;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TestPrinterFormatter {
    private static Map<String, BigDecimal> fakeMapInput = new LinkedHashMap<>();
    private static BigDecimal fakeBigDecimal;

    @Test
    public void formatterConvertToCurrencyOutputsStringCurrencyValue() {
        fakeBigDecimal = new BigDecimal("5.00");
        PrinterFormatter formatter = new PrinterFormatter();
        String currencyValue = formatter.convertToCurrency(fakeBigDecimal, Locale.UK);
        assertThat(currencyValue).isEqualTo("£5.00");
    }

    @Test
    public void formatterCreateScriptCreatesCreditString() {
        fakeBigDecimal = new BigDecimal("5.00");
        fakeMapInput.put("John D", fakeBigDecimal);
        fakeBigDecimal = new BigDecimal("-3.00");
        fakeMapInput.put("Jane D", fakeBigDecimal);
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("John D is owed £5.00");
        expectedOutput.add("Jane D owes £3.00");
        PrinterFormatter formatter = new PrinterFormatter();
        List<String> actualOutput = formatter.createScript(fakeMapInput);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}

