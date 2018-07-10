package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.dataconverters.CsvConverter;
import training.supportbank.dataconverters.JsonParser;
import training.supportbank.models.Transaction;
import training.supportbank.transactionhandlers.ExtractNames;
import training.supportbank.transactionhandlers.Printer;
import training.supportbank.transactionhandlers.ProcessTransactions;
import training.supportbank.userinterfaces.UserInput;

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

        UserInput userInput = new UserInput();
        String filename = userInput.fileSelection();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine();

        List<Transaction> transactions = CsvConverter.extractTransactions(reader);

        Set<String> accountNames = ExtractNames.getUniqueNames(transactions);
        Map<String, BigDecimal> accountBalances = ProcessTransactions.calculateBalances(transactions, accountNames);

        JsonParser jsonParser = new JsonParser();
        List<Transaction> jsonTransactions = jsonParser.parseJsonToTransactions("Transactions2013.json");

        String operation = userInput.operationSelection(filename);

        if (operation.equals("ListAll")) {
            Printer.listAll(accountBalances);
        } else if (operation.equals("ListAccount")) {
            String accountName = userInput.accountSelection();
            List<String> filteredAccountsToPrint = ProcessTransactions.filterAccounts(transactions, accountName);
            Printer.listAccount(filteredAccountsToPrint, accountName);
        } else {
            System.out.print("Invalid input");
        }

    }

}
