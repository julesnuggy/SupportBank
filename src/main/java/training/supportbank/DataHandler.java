package training.supportbank;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class DataHandler {
    public static List<Map<String, String>> dataConverter(BufferedReader reader) throws IOException {
        String transactionLine;
        List<Map<String, String>> transactionArray = new ArrayList<>();

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

    public static Map<String, BigDecimal> calculateBalance(List<Map<String, String>> inputArray, Set<String> setOfNames) {
        Map<String, BigDecimal> finalOutput = new LinkedHashMap<>();
        finalOutput = getValues(inputArray, setOfNames, finalOutput,"From");
        finalOutput = getValues(inputArray, setOfNames, finalOutput,"To");

        return finalOutput;
    }

    private static Map<String, BigDecimal> getValues(List<Map<String, String>> inputArray, Set<String> setOfNames, Map<String, BigDecimal> outputMap, String mapKey) {
        BigDecimal currVal, newVal, totalVal;

        for (String name : setOfNames) {
            for (int i = 0; i < inputArray.size(); i++) {
                if (inputArray.get(i).get(mapKey).equals(name)) {
                    if (outputMap.get(name) != null) {
                        currVal = outputMap.get(name);
                    } else {
                        currVal = new BigDecimal(0);
                    }
                    newVal = new BigDecimal(inputArray.get(i).get("Amount"));

                    switch (mapKey) {
                        case "From":
                            totalVal = currVal.add(newVal);
                            break;
                        case "To":
                            totalVal = currVal.subtract(newVal);
                            break;
                        default: totalVal = new BigDecimal(0);
                        break;
                    }

                    outputMap.put(name, totalVal);
                }
            }
        }
        return outputMap;
    }
}