package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Printer {
    public static void dataOutputter(String csvFile) throws IOException {
        DataHandler dataHandler = new DataHandler();
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        reader.readLine();
        List<Map<String, String>> transactionsToPrint = dataHandler.dataConverter(reader);
        Set<String> namesToUse = dataHandler.extractNames(transactionsToPrint);
        System.out.print(dataHandler.calculateBalance(transactionsToPrint, namesToUse));
    }
}
