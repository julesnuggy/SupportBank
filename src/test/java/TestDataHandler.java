import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import training.supportbank.DataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TestDataHandler {
    private static DataHandler dataHandler;
    private static Map<String, String> fakeMap = new LinkedHashMap<>();
    private static List<Map<String, String>> fakeArray = new ArrayList<>();
    private static Map<String, BigDecimal> expectedBalancesMap = new LinkedHashMap<>();

    @Before
    public void populateExpectedOutputs() {
        fakeArray.clear();
        expectedBalancesMap.clear();

        dataHandler = new DataHandler();

        fakeMap = new LinkedHashMap<>();
        fakeMap.put("Date", "01/01/14");
        fakeMap.put("From", "John D");
        fakeMap.put("To", "Jane D");
        fakeMap.put("Narrative", "Doughnuts");
        fakeMap.put("Amount", "5.00");
        fakeArray.add(fakeMap);

        fakeMap = new LinkedHashMap<>();
        fakeMap.put("Date", "01/01/14");
        fakeMap.put("From", "Jane D");
        fakeMap.put("To", "John D");
        fakeMap.put("Narrative", "Coffee");
        fakeMap.put("Amount", "2.00");
        fakeArray.add(fakeMap);

        expectedBalancesMap.put("John D", new BigDecimal("3.00"));
        expectedBalancesMap.put("Jane D", new BigDecimal( "-3.00"));
    }

    @Test
    public void dataConverterConvertsCsvToArrayOfMaps() throws IOException {
        BufferedReader fakeBufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(fakeBufferedReader.readLine()).thenReturn("01/01/14,John D,Jane D,Doughnuts,5.00", "01/01/14,Jane D,John D,Coffee,2.00", null);
        assertThat(dataHandler.dataConverter(fakeBufferedReader)).isEqualTo(fakeArray);
    }

    @Test
    public void extractNamesReturnsArrayOfUniqueNames() {
        Set<String> tempSet = dataHandler.extractNames(fakeArray);
        assertThat(tempSet.size()).isEqualTo(2);
        assertThat(tempSet).contains("John D", "Jane D");
    }

    @Test
    public void calculateBalanceReturnsTotalOwedPerPerson() {
        Set<String> setOfNames = new HashSet<>();
        setOfNames.add("John D");
        setOfNames.add("Jane D");
        setOfNames.add("No Transactions Jo");
        assertThat(dataHandler.calculateBalance(fakeArray, setOfNames)).isEqualTo(expectedBalancesMap);
    }

    @Test
    public void filterAccountsReturnsDesiredTransactions() {
        List<Map<String, String>> anotherFakeArray = new ArrayList<>();
        anotherFakeArray.addAll(fakeArray);
        fakeMap = new LinkedHashMap<>();
        fakeMap.put("Date", "01/01/14");
        fakeMap.put("From", "Sarah B");
        fakeMap.put("To", "Jennifer A");
        fakeMap.put("Narrative", "Shoes");
        fakeMap.put("Amount", "200.00");
        anotherFakeArray.add(fakeMap);

        List<Map<String, String>> actualFilteredList = dataHandler.filterAccounts(anotherFakeArray, "John D");
        System.out.println(actualFilteredList);
        System.out.println(fakeArray);
        assertThat(actualFilteredList).isEqualTo(fakeArray);

    }
}
