package training.supportbank;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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

    public static Set<String> extractNames(List<Map<String, String>> inputArray) {
        String currName;
        Set<String> setOfNames = new HashSet<>();

        for (int i = 0; i < inputArray.size(); i++) {
            currName = inputArray.get(i).get("From");
            setOfNames.add(currName);
        }
        return setOfNames;
    }


//
//    public static Map calculateBalance(List inputArray) {
//        Map<String, BigDecimal> outputMap = new LinkedHashMap<>();
//
////        for (int i=0; i<inputArray.size(); i++) {
////            outputMap.put(inputArray[i].getKey())
////        }
//
//        return outputMap;
//    }
}