import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.mockito.Mockito;
import training.supportbank.DataHandler;
import training.supportbank.Printer;
import training.supportbank.PrinterFormatter;

import java.io.BufferedReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class TestPrinter {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void listAllPrintsToConsole() throws IOException {
        DataHandler dataHandlerDummy = Mockito.mock(DataHandler.class);
        BufferedReader readerDummy = Mockito.mock(BufferedReader.class);
        PrinterFormatter formatterDummy = Mockito.mock(PrinterFormatter.class);
        Printer printer = new Printer(dataHandlerDummy, readerDummy, formatterDummy);
        String expectedOutput = "Jon A is owed £7.80\nStephen S is owed £4.37\nSarah T owes £7.80\nTim L owes £4.37\n";
        printer.listAll("TestFile.csv");
        assertThat(systemOutRule.getLog()).isEqualToIgnoringNewLines(expectedOutput);
    }

    @Test
    public void listAccountPrintsToConsole() throws IOException {
        DataHandler dataHandlerDummy = Mockito.mock(DataHandler.class);
        BufferedReader readerDummy = Mockito.mock(BufferedReader.class);
        PrinterFormatter formatterDummy = Mockito.mock(PrinterFormatter.class);
        Printer printer = new Printer(dataHandlerDummy, readerDummy, formatterDummy);
        String expectedOutput = "{Date=01/01/2014, From=Jon A, To=Sarah T, Narrative=Pokemon Training, Amount=7.8}";
        printer.listAccount("TestFile.csv", "Jon A");
        assertThat(systemOutRule.getLog()).isEqualToIgnoringNewLines(expectedOutput);
    }
}
