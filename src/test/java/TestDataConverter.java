import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import training.supportbank.DataConverter;
import training.supportbank.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TestDataConverter {
    private static DataConverter dataConverter;
    private static Map<String, BigDecimal> expectedBalancesMap = new LinkedHashMap<>();

    private static Transaction testTransaction;
    private static List<Transaction> testTransactionsList = new ArrayList<>();

    @BeforeClass
    public static void populateExpectedOutputs() throws ParseException {
        dataConverter = new DataConverter();

        Date tranDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/14");
        testTransaction = new Transaction(tranDate, "John D", "Jane D", "Doughnuts", BigDecimal.valueOf(5.0));
        testTransactionsList.add(testTransaction);
        testTransaction = new Transaction(tranDate, "Jane D", "John D", "Coffee", BigDecimal.valueOf(2.0));
        testTransactionsList.add(testTransaction);

        expectedBalancesMap.put("John D", BigDecimal.valueOf(3.0));
        expectedBalancesMap.put("Jane D", BigDecimal.valueOf( -3.0));
    }

    @Test
    public void dataConverterConvertsCsvToArrayOfMaps() throws IOException, ParseException {
        BufferedReader testBufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(testBufferedReader.readLine()).thenReturn("01/01/14,John D,Jane D,Doughnuts,5.00", "01/01/14,Jane D,John D,Coffee,2.00", null);
        List<Transaction> actualDataConverterOutput = dataConverter.dataConverter(testBufferedReader);
        assertThat(actualDataConverterOutput)
                .hasSameClassAs(testTransactionsList)
                .hasSameSizeAs(testTransactionsList);
    }

    @Test
    public void extractNamesReturnsArrayOfUniqueNames() {
        Set<String> tempSet = dataConverter.extractNames(testTransactionsList);
        assertThat(tempSet.size()).isEqualTo(2);
        assertThat(tempSet).contains("John D", "Jane D");
    }

    @Test
    public void calculateBalanceReturnsTotalOwedPerPerson() {
        Set<String> setOfNames = new HashSet<>();
        setOfNames.add("John D");
        setOfNames.add("Jane D");
        assertThat(dataConverter.calculateBalance(testTransactionsList, setOfNames)).isEqualTo(expectedBalancesMap);
    }

    @Test
    public void filterAccountsReturnsDesiredTransactions() throws ParseException {
        List<Transaction> anotherTestTransactionList = new ArrayList<>();
        anotherTestTransactionList.addAll(testTransactionsList);

        Date tranDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/14");
        testTransaction = new Transaction(tranDate, "Sarah B", "Jenn A", "Shoes", BigDecimal.valueOf(200.0));
        anotherTestTransactionList.add(testTransaction);

        List<String> expectedTestOutput = new ArrayList<>();
        expectedTestOutput.add("[Date] Mon Jan 01 00:00:00 GMT 14 [From] John D [To] :Jane D [For] Doughnuts [Costing] 5.0");
        expectedTestOutput.add("[Date] Mon Jan 01 00:00:00 GMT 14 [From] Jane D [To] :John D [For] Coffee [Costing] 2.0");

        List<String> actualFilteredList = dataConverter.filterAccounts(anotherTestTransactionList, "John D");
        assertThat(actualFilteredList).isEqualTo(expectedTestOutput);
    }
}