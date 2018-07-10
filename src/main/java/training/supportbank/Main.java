package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.models.Transaction;
import training.supportbank.userinterface.UserInput;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String args[]) throws IOException {
        logger.info("App started");

        UserInput userInput = new UserInput();
        String filename = userInput.fileSelection();
        List<Transaction> transactions = userInput.generateTransactions(filename);
        logger.info("Assessing " + transactions.size() + " transactions for " + filename);

        String operation = userInput.operationSelection(filename);

        if (operation.equals("ListAll")) {
            userInput.listAll(transactions);
        } else if (operation.equals("ListAccount")) {
            userInput.listAccount(transactions);
        } else {
            logger.debug("You have entered an invalid operation.");
        }

    }

}
