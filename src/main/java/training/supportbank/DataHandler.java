package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    public static void dataOutputter(String csvFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        reader.readLine();
        System.out.print(dataIterator(reader));
    }

    private static List dataIterator(BufferedReader reader) throws IOException {
        String transactionLine;
        Map transactionMap = new LinkedHashMap<>();
        List<Map> transactionArray = new ArrayList<Map>();

        while ((transactionLine = reader.readLine()) != null) {
            String[] transaction = transactionLine.split(",");
            transactionMap.put("Date", transaction[0].replace("\"", ""));
            transactionMap.put("From", transaction[1]);
            transactionMap.put("To", transaction[2]);
            transactionMap.put("Narrative", transaction[3]);
            transactionMap.put("Amount", new BigDecimal(transaction[4].replace("\"", "")));
            transactionArray.add(transactionMap);
        }
        return transactionArray;
    }
}