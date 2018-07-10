package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String args[]) throws IOException, ParseException {
        logger.info("App started");

        String csvFile = "DodgyTransactions2015.csv";
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        reader.readLine();

        List<Transaction> transactions = DataConverter.extractTransactions(reader);
        Set<String> accountNames = Helper.extractNames(transactions);
        Map<String, BigDecimal> accountBalances = ProcessTransactions.calculateBalances(transactions, accountNames);

        Scanner scanner = new Scanner(System.in);
        System.out.print("This programme allows you to view processed data for the " + csvFile + " file.\n" +
                "ListAll - outputs each persons name and the total they owe or are owed\n" +
                "ListAccount - outputs every transaction associated with the given name\n" +
                "Please enter your desired operation: ");
        String operation = scanner.next();

        if (operation.equals("ListAll")) {
            Printer.listAll(accountBalances);
        } else if (operation.equals("ListAccount")) {
            System.out.print("Enter the full name of the person whose transactions you wish to view: ");
            String accountName = scanner.next();
            accountName += scanner.nextLine();
            List<String> filteredAccountsToPrint = ProcessTransactions.filterAccounts(transactions, accountName);
            Printer.listAccount(filteredAccountsToPrint, accountName);
        } else {
            System.out.print("Invalid input");
        }

    }

}
