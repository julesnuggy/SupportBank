package training.supportbank;

import java.util.List;

public class Printer {

    public static void listAll(List<String> listAllArray) {
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
