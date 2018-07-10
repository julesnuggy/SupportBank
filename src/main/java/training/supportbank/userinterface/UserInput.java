package training.supportbank.userinterface;

import training.supportbank.customexceptions.UserInputException;
import training.supportbank.dataconverters.CsvConverter;
import training.supportbank.dataconverters.JsonParser;
import training.supportbank.models.Transaction;
import training.supportbank.transactionhandlers.ExtractNames;
import training.supportbank.transactionhandlers.Printer;
import training.supportbank.transactionhandlers.ProcessTransactions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UserInput {
    private final Scanner scanner = new Scanner(System.in);
    private static Set<String> accountNames;

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

    public List<Transaction> generateTransactions(String filename) throws IOException {
        try {
            if (filename.endsWith(".csv")) {
                return extractCsvTransactions(filename);
            } else if (filename.endsWith(".json")) {
                return extractJsonTransactions(filename);
            } else {
                throw new UserInputException(filename + "is not valid a file for this programme.\nRemember to include the file extension.");
            }
        } catch (FileNotFoundException e) {
            throw new UserInputException(filename + "is not valid a file for this programme.\nCheck the filename.");
        }
    }

    public void listAll(List<Transaction> transactions) {
        accountNames = ExtractNames.getUniqueNames(transactions);
        Map<String, BigDecimal> accountBalances = ProcessTransactions.calculateBalances(transactions, accountNames);
        Printer.listAll(accountBalances);
    }

    public void listAccount(List<Transaction> transactions) {
        accountNames = ExtractNames.getUniqueNames(transactions);
        String accountName = accountSelection();
        List<String> filteredAccountsToPrint = ProcessTransactions.filterAccounts(transactions, accountName);
        Printer.listAccount(filteredAccountsToPrint, accountName);
    }

    private List<Transaction> extractCsvTransactions(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine();
        return CsvConverter.extractTransactions(reader);
    }

    private List<Transaction> extractJsonTransactions(String filename) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parseJsonToTransactions(filename);
    }

    private String accountSelection() {
        System.out.print("Enter the full name of the person whose transactions you wish to view: ");
        String accountName = scanner.next();
        accountName += scanner.nextLine();
        if(accountNames.contains(accountName)) {
            return accountName;
        } else {
            throw new UserInputException("Invalid account name entered.\nPlease check the list of names for this transaction file.");
        }
    }
}
