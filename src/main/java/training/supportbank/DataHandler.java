package training.supportbank;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    public static List dataConverter(BufferedReader reader) throws IOException {
        String transactionLine;
        List<Map> transactionArray = new ArrayList<>();

        while ((transactionLine = reader.readLine()) != null) {
            Map<String, String> transactionMap = new LinkedHashMap<>();
            String[] transaction = transactionLine.split(",");
            transactionMap.put("Date", transaction[0].replace("\"", ""));
            transactionMap.put("From", transaction[1]);
            transactionMap.put("To", transaction[2]);
            transactionMap.put("Narrative", transaction[3]);
            transactionMap.put("Amount", transaction[4].replace("\"", ""));
            transactionArray.add(transactionMap);
        }
        return transactionArray;
    }
}