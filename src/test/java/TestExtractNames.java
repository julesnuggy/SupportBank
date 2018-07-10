import org.junit.Test;
import training.supportbank.ExtractNames;
import training.supportbank.models.Transaction;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestExtractNames {
    @Test
    public void extractNamesReturnsArrayOfUniqueNames() throws ParseException {
        Transaction testTransaction;
        List<Transaction> testTransactionsList = new ArrayList<>();

        LocalDate tranDate = LocalDate.parse("01/01/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        testTransaction = new Transaction(tranDate, "John D", "Jane D", "Doughnuts", BigDecimal.valueOf(5.0));
        testTransactionsList.add(testTransaction);
        testTransaction = new Transaction(tranDate, "Jane D", "John D", "Coffee", BigDecimal.valueOf(2.0));
        testTransactionsList.add(testTransaction);

        Set<String> tempSet = ExtractNames.getUniqueNames(testTransactionsList);
        assertThat(tempSet.size()).isEqualTo(2);
        assertThat(tempSet).contains("John D", "Jane D");
    }
}
