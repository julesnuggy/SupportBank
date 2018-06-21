import org.junit.Test;
import training.supportbank.Helper;
import training.supportbank.Transaction;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHelper {
    @Test
    public void extractNamesReturnsArrayOfUniqueNames() throws ParseException {
        Transaction testTransaction;
        List<Transaction> testTransactionsList = new ArrayList<>();

        Date tranDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/14");
        testTransaction = new Transaction(tranDate, "John D", "Jane D", "Doughnuts", BigDecimal.valueOf(5.0));
        testTransactionsList.add(testTransaction);
        testTransaction = new Transaction(tranDate, "Jane D", "John D", "Coffee", BigDecimal.valueOf(2.0));
        testTransactionsList.add(testTransaction);

        Set<String> tempSet = Helper.extractNames(testTransactionsList);
        assertThat(tempSet.size()).isEqualTo(2);
        assertThat(tempSet).contains("John D", "Jane D");
    }
}
