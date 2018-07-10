package training.supportbank.userinterfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserInput {
    private static final Logger logger = LogManager.getLogger("UserInput");
    private final Scanner scanner = new Scanner(System.in);

    public String fileSelection() {
        System.out.println("Which transactions file would you like to analyse?" +
                "\n-- Transactions2013.json" +
                "\n-- Transactions2014.csv" +
                "\n-- DodgyTransactions2015.csv");
        return scanner.next();
    }

    public String operationSelection(String filename) {
        System.out.print("You are assessing transactions from the " + filename + " file.\n" +
                "You may run the following commands:\n" +
                "-- ListAll - outputs each persons name and the total they owe or are owed\n" +
                "-- ListAccount - outputs every transaction associated with the given name\n" +
                "Please enter your desired operation: ");
        return scanner.next();
    }

    public String accountSelection() {
        System.out.print("Enter the full name of the person whose transactions you wish to view: ");
        String accountName = scanner.next();
        accountName += scanner.nextLine();
        return accountName;
    }
}
