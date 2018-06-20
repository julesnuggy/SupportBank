package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Printer {
    public static void listAll(String csvFile) throws IOException {
        DataHandler dataHandler = new DataHandler();
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        PrinterFormatter formatter = new PrinterFormatter();
        reader.readLine();
        List<Map<String, String>> transactionsToPrint = dataHandler.dataConverter(reader);
        Set<String> namesToUse = dataHandler.extractNames(transactionsToPrint);
        Map<String, BigDecimal> calculatedMap = dataHandler.calculateBalance(transactionsToPrint, namesToUse);
        List<String> finalArray = formatter.createScript(calculatedMap);
        for(String element : finalArray) {
            System.out.println(element);
        }
    }
}
