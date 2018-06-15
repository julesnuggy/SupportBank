import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import training.supportbank.DataHandler;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestDataHandler {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void dataOutputterConvertsCsvToArrayOfMaps() throws IOException {
        DataHandler dh = new DataHandler();
        String expectedOutput = "[{Date=01/01/2014, From=Jon A, To=Sarah T, Narrative=Pokemon Training, Amount=7.8}, {Date=04/01/2014, From=Stephen S, To=Tim L, Narrative=Lunch, Amount=4.37}]";
        dh.dataOutputter("TestFile.csv");
        assertEquals(expectedOutput, systemOutRule.getLog());
    }
}
