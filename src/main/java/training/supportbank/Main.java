package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException, ParseException {
        String csvFile = "Transactions2014.csv";
        DataHandler dataHandler = new DataHandler();
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        PrinterFormatter formatter = new PrinterFormatter();
        reader.readLine();

        List<Transaction> transactionsToPrint = dataHandler.dataConverter(reader);
        Set<String> namesToUse = dataHandler.extractNames(transactionsToPrint);
        Map<String, BigDecimal> namesAndSubtotals = dataHandler.calculateBalance(transactionsToPrint, namesToUse);

        Scanner scanner = new Scanner(System.in);
        System.out.print("This programme allows you to view processed data for the Transaction2014.csv file.\n" +
                "ListAll - outputs each persons name and the total they owe or are owed\n" +
                "ListAccount - outputs every transaction associated with the given name\n" +
                "Please enter your desired operation: ");
        String operation = scanner.next();

        if (operation.equals("ListAll")) {
            Printer.listAll(namesAndSubtotals);
        } else if (operation.equals("ListAccount")) {
            System.out.print("Enter the full name of the person whose transactions you wish to view: ");
            String accountName = scanner.next();
            accountName += scanner.nextLine();
            List<String> listAccountToPrint = dataHandler.filterAccounts(transactionsToPrint, accountName);
            Printer.listAccount(listAccountToPrint, accountName);
        } else {
            System.out.print("Invalid input");
        }

    }

}
