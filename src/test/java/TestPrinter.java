import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import training.supportbank.Printer;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class TestPrinter {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

//    @Test
//    public void dataOutputterPrintsToConsole() throws IOException {
//        Printer printer = new Printer();
//        String expectedOutput = "[{Date=01/01/2014, From=Jon A, To=Sarah T, Narrative=Pokemon Training, Amount=7.8}, {Date=04/01/2014, From=Stephen S, To=Tim L, Narrative=Lunch, Amount=4.37}]";
//        printer.dataOutputter("TestFile.csv");
//        assertThat(expectedOutput).isEqualTo(systemOutRule.getLog());
//    }

    @Test
    public void dataOutputterPrintsToConsole() throws IOException {
        Printer printer = new Printer();
        String expectedOutput = "{Jon A=7.80, Stephen S=4.37, Sarah T=-7.80, Tim L=-4.37}";
        printer.dataOutputter("TestFile.csv");
        assertThat(systemOutRule.getLog()).isEqualTo(expectedOutput);
    }
}
