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
        String fromName, toName;
        Set<String> setOfNames = new HashSet<>();

        for (int i = 0; i < inputArray.size(); i++) {
            fromName = inputArray.get(i).get("From");
            toName = inputArray.get(i).get("To");
            setOfNames.add(fromName);
            setOfNames.add(toName);
        }
        return setOfNames;
    }

    public static Map calculateBalance(List<Map<String, String>> inputArray, Set<String> setOfNames) {
        Map<String, BigDecimal> outputMap = new LinkedHashMap<>();

        for (String name : setOfNames) {
            for (int i = 0; i<inputArray.size(); i++) {
                if(inputArray.get(i).get("From").equals(name)) {
                    BigDecimal currVal;
                    if(outputMap.get(name) != null) {
                        currVal = outputMap.get(name);
                    } else {
                        currVal = new BigDecimal(0);
                    }
                    BigDecimal addVal = new BigDecimal(inputArray.get(i).get("Amount"));
                    BigDecimal newVal = currVal.add(addVal);
                    outputMap.put(name, newVal);
                } else if(inputArray.get(i).get("To").equals(name)) {
                    BigDecimal currVal;
                    if(outputMap.get(name) != null) {
                        currVal = outputMap.get(name);
                    } else {
                        currVal = new BigDecimal(0);
                    }
                    BigDecimal addVal = new BigDecimal(inputArray.get(i).get("Amount"));
                    BigDecimal newVal = currVal.subtract(addVal);
                    outputMap.put(name, newVal);
                }

            }
        }

        return outputMap;
    }
}