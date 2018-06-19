import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import training.supportbank.Printer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TestPrinter {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void listAllPrintsToConsole() {
        Printer printer = new Printer();
        String expectedOutput = "Jon A is owed £7.80\nStephen S is owed £4.37\nSarah T owes £7.80\nTim L owes £4.37\n";
        List<String> listAllArray = new ArrayList<>();
//        printer.listAll("TestFile.csv");
//        assertThat(systemOutRule.getLog()).isEqualToIgnoringNewLines(expectedOutput);
    }

    @Test
    public void listAccountPrintsToConsole() {
        Printer printer = new Printer();
        String expectedOutput = "{Date=01/01/2014, From=Jon A, To=Sarah T, Narrative=Pokemon Training, Amount=7.8}";
//        printer.listAccount("TestFile.csv", "Jon A");
//        assertThat(systemOutRule.getLog()).isEqualToIgnoringNewLines(expectedOutput);
    }
}
