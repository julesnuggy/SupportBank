package training.supportbank.userinterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.dataconverters.CsvConverter;
import training.supportbank.dataconverters.JsonParser;
import training.supportbank.models.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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

    public List<Transaction> generateTransactions(String filename) throws IOException {
        if(filename.endsWith(".csv")) {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.readLine();
            return CsvConverter.extractTransactions(reader);
        } else if (filename.endsWith(".json")) {
            JsonParser jsonParser = new JsonParser();
            return jsonParser.parseJsonToTransactions(filename);
        } else {
            return null;
        }
    }
}
