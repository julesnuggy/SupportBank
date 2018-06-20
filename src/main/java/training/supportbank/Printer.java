package training.supportbank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Printer {

    public static void listAll(Map<String, BigDecimal> listAllMap) {
        List<String> listAllArray = PrinterFormatter.convertMapToSentence(listAllMap);
        for(String element : listAllArray) {
            System.out.println(element);
        }
    }

    public static void listAccount(List<String> listAccountArray, String accountName) {
        for(String element : listAccountArray) {
            System.out.println(element);
        }
    }


}
