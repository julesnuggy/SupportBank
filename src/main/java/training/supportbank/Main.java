package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Main {
//    public static void main1(String args[]) throws IOException {
//        String csvFile = "Transactions2014.csv";
//        DataHandler dataHandler = new DataHandler();
//        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
//        PrinterFormatter formatter = new PrinterFormatter();
//        reader.readLine();
//
//        List<Map<String, String>> transactionsToPrint = dataHandler.dataConverter(reader);
//        Set<String> namesToUse = dataHandler.extractNames(transactionsToPrint);
//        Map<String, BigDecimal> calculatedMap = dataHandler.calculateBalance(transactionsToPrint, namesToUse);
//
//        Printer printer = new Printer();
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("This programme allows you to view processed data for the Transaction2014.csv file.\n" +
//                "ListAll - outputs each persons name and the total they owe or are owed\n" +
//                "ListAccount - outputs every transaction associated with the given name\n" +
//                "Please enter your desired operation: ");
//        String operation = scanner.next();
//
//        if (operation.equals("ListAll")) {
//            List<String> listAllArray = formatter.convertMapToSentence(calculatedMap);
//            printer.listAll(listAllArray);
//        } else if (operation.equals("ListAccount")) {
//            System.out.print("Enter the full name of the person whose transactions you wish to view: ");
//            String accountName = scanner.next();
//            accountName += scanner.nextLine();
//            List<Map<String, String>> listAccountArray = dataHandler.filterAccounts(transactionsToPrint, accountName);
//            printer.listAccount(listAccountArray, accountName);
//        } else {
//            System.out.print("Invalid input");
//        }
//
//    }

    public static void main(String args[]) {
        Transaction tran1 = new Transaction(new Date(1220227200), "Amy", "Bob", "Stuff", BigDecimal.valueOf(5));
        Transaction tran2 = new Transaction(new Date(1220227200), "Charlie", "Dean", "More Stuff", BigDecimal.valueOf(50));
        List<Transaction> tranArray = new ArrayList<>();
        tranArray.add(tran1);
        tranArray.add(tran2);
    }

}
