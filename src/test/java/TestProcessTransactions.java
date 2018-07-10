import org.junit.BeforeClass;
import org.junit.Test;
import training.supportbank.ProcessTransactions;
import training.supportbank.Transaction;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TestProcessTransactions {
    private static ProcessTransactions processTransactions = new ProcessTransactions();
    private static Transaction transaction;
    private static List<Transaction> transactions = new ArrayList<>();

    @BeforeClass
    public static void createTransactions() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/14");
        transaction = new Transaction(date, "John D", "Jane D", "Doughnuts", BigDecimal.valueOf(5.0));
        transactions.add(transaction);
        transaction = new Transaction(date, "Jane D", "John D", "Coffee", BigDecimal.valueOf(2.0));
        transactions.add(transaction);
        transaction = new Transaction(date, "Sarah B", "Jenn A", "Shoes", BigDecimal.valueOf(200.0));
        transactions.add(transaction);
    }

    @Test
    public void calculateBalanceReturnsTotalOwedPerPerson() {
        Map<String, BigDecimal> expectedAccountBalances = new LinkedHashMap<>();
        expectedAccountBalances.put("John D", BigDecimal.valueOf(3.0));
        expectedAccountBalances.put("Jane D", BigDecimal.valueOf( -3.0));
        expectedAccountBalances.put("Sarah B", BigDecimal.valueOf( 200.0));
        expectedAccountBalances.put("Jenn A", BigDecimal.valueOf( -200.0));

        Set<String> accountNames = new HashSet<>();
        accountNames.add("John D");
        accountNames.add("Jane D");
        accountNames.add("Sarah B");
        accountNames.add("Jenn A");
        assertThat(processTransactions.calculateBalances(transactions, accountNames)).isEqualTo(expectedAccountBalances);
    }

    @Test
    public void filterAccountsReturnsDesiredTransactions() throws ParseException {
        List<String> expectedFilteredAccounts = new ArrayList<>();
        expectedFilteredAccounts.add("[Date] Mon Jan 01 00:00:00 GMT 14 [From] John D [To] :Jane D [For] Doughnuts [Costing] 5.0");
        expectedFilteredAccounts.add("[Date] Mon Jan 01 00:00:00 GMT 14 [From] Jane D [To] :John D [For] Coffee [Costing] 2.0");

        List<String> actualFilteredAccounts = processTransactions.filterAccounts(transactions, "John D");
        assertThat(actualFilteredAccounts).isEqualTo(expectedFilteredAccounts);
    }
}
