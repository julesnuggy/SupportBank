package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Printer {
    private static DataHandler dataHandler;
    private static BufferedReader reader;
    private static PrinterFormatter formatter;

    public static void listAll(String csvFile) throws IOException {
        prepareFile(csvFile);
        List<Map<String, String>> transactionsToPrint = dataHandler.dataConverter(reader);
        Set<String> namesToUse = dataHandler.extractNames(transactionsToPrint);
        Map<String, BigDecimal> calculatedMap = dataHandler.calculateBalance(transactionsToPrint, namesToUse);
        List<String> finalArray = formatter.createScript(calculatedMap);
        for(String element : finalArray) {
            System.out.println(element);
        }
    }

    public static void listAccount(String csvFile, String accountName) throws IOException {
        prepareFile(csvFile);

    }

    private static void prepareFile(String csvFile) throws IOException {
        dataHandler = new DataHandler();
        reader = new BufferedReader(new FileReader(csvFile));
        formatter = new PrinterFormatter();
        reader.readLine();
    }


}
