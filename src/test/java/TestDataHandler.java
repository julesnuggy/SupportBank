import org.junit.Test;
import org.mockito.Mockito;
import training.supportbank.DataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TestDataHandler {

    @Test
    public void dataConverterConvertsCsvToArrayOfMaps() throws IOException {
        DataHandler dataHandler = new DataHandler();

        List<Map> expectedResult = new ArrayList<>();
        Map<String, String> expectedMap = new LinkedHashMap();
        expectedMap.put("Date", "01/01/14");
        expectedMap.put("From", "John D");
        expectedMap.put("To", "Jane D");
        expectedMap.put("Narrative", "Doughnuts");
        expectedMap.put("Amount", "1.00");
        expectedResult.add(expectedMap);

        BufferedReader fakeBufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(fakeBufferedReader.readLine()).thenReturn("01/01/14,John D,Jane D,Doughnuts,1.00", null);
        assertThat(dataHandler.dataConverter(fakeBufferedReader), is(expectedResult));
    }
}
