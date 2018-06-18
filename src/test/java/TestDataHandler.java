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
    private static List<Map<String, String>> expectedArray = new ArrayList<>();
    private static Map<String, BigDecimal> expectedBalancesMap = new LinkedHashMap<>();

    @Before
    public void populateExpectedOutputs() {
        expectedArray.clear();
        expectedBalancesMap.clear();

        Map<String, String> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Date", "01/01/14");
        expectedMap.put("From", "John D");
        expectedMap.put("To", "Jane D");
        expectedMap.put("Narrative", "Doughnuts");
        expectedMap.put("Amount", "5.00");
        expectedArray.add(expectedMap);

        expectedMap = new LinkedHashMap<>();
        expectedMap.put("Date", "01/01/14");
        expectedMap.put("From", "Jane D");
        expectedMap.put("To", "John D");
        expectedMap.put("Narrative", "Coffee");
        expectedMap.put("Amount", "2.00");
        expectedArray.add(expectedMap);

        expectedBalancesMap = new LinkedHashMap<>();
        expectedBalancesMap.put("John D", new BigDecimal(3.00));
        expectedBalancesMap.put("Jane D", new BigDecimal( -3.00));
    }

    @Test
    public void dataConverterConvertsCsvToArrayOfMaps() throws IOException {
        DataHandler dataHandler = new DataHandler();
        BufferedReader fakeBufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(fakeBufferedReader.readLine()).thenReturn("01/01/14,John D,Jane D,Doughnuts,5.00", "01/01/14,Jane D,John D,Coffee,2.00", null);
        assertThat(dataHandler.dataConverter(fakeBufferedReader)).isEqualTo(expectedArray);
    }

    @Test
    public void extractNamesReturnsArrayOfUniqueNames() {
        DataHandler dataHandler = new DataHandler();
        Set<String> tempSet = dataHandler.extractNames(expectedArray);
        assertThat(tempSet.size()).isEqualTo(2);
        assertThat(tempSet).contains("John D", "Jane D");
    }

//    @Test
//    public void calculateBalanceReturnsTotalOwedPerPerson() {
//        DataHandler dataHandler = new DataHandler();
//
//        assertThat(dataHandler.calculateBalance(expectedArray), is(expectedBalancesMap));
//    }
}
