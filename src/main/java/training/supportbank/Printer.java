package training.supportbank;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Printer {

    public static void listAll(List<String> listAllArray) {
        for(String element : listAllArray) {
            System.out.println(element);
        }
    }

    public static void listAccount(List<Map<String, String>> listAccountArray , String accountName) throws IOException {
        for(Map element : listAccountArray) {
            System.out.println(element);
        }
    }


}
