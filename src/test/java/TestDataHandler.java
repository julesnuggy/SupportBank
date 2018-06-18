import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import training.supportbank.DataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class TestDataHandler {
    private static List<Map<String, String>> expectedArray = new ArrayList<>();
    private static Map<String, BigDecimal> expectedBalancesMap = new LinkedHashMap<>();
    private static List<String> expectedListOfNames = new ArrayList<>();

    @Before
    public void populateExpectedOutputs() {
        expectedArray.clear();
        expectedBalancesMap.clear();
        expectedListOfNames.clear();

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

        expectedListOfNames.add("John D");
        expectedListOfNames.add("Jane D");
    }

    @Test
    public void dataConverterConvertsCsvToArrayOfMaps() throws IOException {
        DataHandler dataHandler = new DataHandler();
        BufferedReader fakeBufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(fakeBufferedReader.readLine()).thenReturn("01/01/14,John D,Jane D,Doughnuts,5.00", "01/01/14,Jane D,John D,Coffee,2.00", null);
        assertThat(dataHandler.dataConverter(fakeBufferedReader), is(expectedArray));
    }

    @Test
    public void extractNamesReturnsArrayOfUniqueNames() {
        DataHandler dataHandler = new DataHandler();

        assertThat(dataHandler.extractNames(expectedArray), containsOnly(expectedListOfNames));
    }

//    @Test
//    public void calculateBalanceReturnsTotalOwedPerPerson() {
//        DataHandler dataHandler = new DataHandler();
//
//        assertThat(dataHandler.calculateBalance(expectedArray), is(expectedBalancesMap));
//    }
}
