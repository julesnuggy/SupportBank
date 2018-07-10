import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import training.supportbank.Printer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class TestPrinter {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void listAllPrintsToConsole() {
        String expectedOutput = "Jon A is owed £7.80\nStephen S is owed £4.37\nSarah T owes £7.80\nTim L owes £4.37\n";
        Map<String, BigDecimal> listAllMap = new LinkedHashMap<>();
        listAllMap.put("Jon A", BigDecimal.valueOf(7.80));
        listAllMap.put("Stephen S", BigDecimal.valueOf(4.37));
        listAllMap.put("Sarah T", BigDecimal.valueOf(-7.80));
        listAllMap.put("Tim L", BigDecimal.valueOf(-4.37));
        Printer.listAll(listAllMap);
        assertThat(systemOutRule.getLog()).isEqualToIgnoringNewLines(expectedOutput);
    }

    @Test
    public void listAccountPrintsToConsole() {
        List<String> testInput = new ArrayList<>();
        testInput.add("[Date] 2014-01-01 [From] John D [To] Jane D [For] Doughnuts [Costing] 5.0");
        testInput.add("[Date] 2014-01-01 [From] Jane D [To] John D [For] Coffee [Costing] 2.0");

        String expectedOutput = "[Date] 2014-01-01 [From] John D [To] Jane D [For] Doughnuts [Costing] 5.0\n" +
            "[Date] 2014-01-01 [From] Jane D [To] John D [For] Coffee [Costing] 2.0";

        Printer.listAccount(testInput, "John D");
        assertThat(systemOutRule.getLog()).isEqualToIgnoringNewLines(expectedOutput);
    }
}
