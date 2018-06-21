import org.junit.Test;
import training.supportbank.Formatter;
import training.supportbank.Transaction;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TestFormatter {
    @Test
    public void formatterConvertToCurrencyOutputsStringCurrencyValue() {
        String currencyValue = Formatter.convertToCurrency(new BigDecimal("5.00"), Locale.UK);
        assertThat(currencyValue).isEqualTo("£5.00");
    }

    @Test
    public void formatterCreateScriptCreatesCreditString() {
        Map<String, BigDecimal> testMapInput = new LinkedHashMap<>();
        testMapInput.put("John D", new BigDecimal("5.00"));
        testMapInput.put("Jane D", new BigDecimal("-3.00"));
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("John D is owed £5.00");
        expectedOutput.add("Jane D owes £3.00");
        List<String> actualOutput = Formatter.convertMapToSentence(testMapInput);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void formatFilteredAccountsFormatsTransactionObject() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/14");
        Transaction transaction = new Transaction(date, "John D", "Jane D", "Doughnuts", BigDecimal.valueOf(5.0));
        String expectedFormatterTransaction = "[Date] Mon Jan 01 00:00:00 GMT 14 [From] John D [To] :Jane D [For] Doughnuts [Costing] 5.0";
        String actualFormattedTransaction = Formatter.formatFilteredTransaction(transaction);
        assertThat(actualFormattedTransaction ).isEqualTo(expectedFormatterTransaction);

    }

}

