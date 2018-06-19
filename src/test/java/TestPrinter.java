import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import training.supportbank.Printer;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class TestPrinter {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void listAllPrintsToConsole() throws IOException {
        Printer printer = new Printer();
        String expectedOutput = "Jon A is owed £7.80\nStephen S is owed £4.37\nSarah T owes £7.80\nTim L owes £4.37\n";
        printer.listAll("TestFile.csv");
        assertThat(systemOutRule.getLog()).isEqualToIgnoringNewLines(expectedOutput);
    }
}
