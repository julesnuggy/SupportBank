package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Printer {
//    @Inject private static DataHandler dataHandler;
//    @Inject private static BufferedReader reader;
//    @Inject private static PrinterFormatter formatter;

//    public Printer(DataHandler dataHandler, BufferedReader reader, PrinterFormatter formatter) throws IOException {
//        this.dataHandler = dataHandler;
//        this.reader = reader;
//        this.formatter = formatter;
//        this.reader.readLine();
//    }

    public static void listAll(List<String> listAllArray) {
//        List<Map<String, String>> transactionsToPrint = dataHandler.dataConverter(reader);
//        Set<String> namesToUse = dataHandler.extractNames(transactionsToPrint);
//        Map<String, BigDecimal> calculatedMap = dataHandler.calculateBalance(transactionsToPrint, namesToUse);
//        List<String> listAllArray = formatter.createScript(calculatedMap);
        for(String element : listAllArray) {
            System.out.println(element);
        }
    }

    public static void listAccount(List<Map<String, String>> listAccountArray , String accountName) throws IOException {
//        List<Map<String, String>> transactionsToPrint = dataHandler.dataConverter(reader);
//        List<Map<String, String>> listAccountArray = dataHandler.filterAccounts(transactionsToPrint, accountName);
        for(Map element : listAccountArray) {
            System.out.println(element);
        }
    }


}
