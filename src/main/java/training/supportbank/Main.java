package training.supportbank;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        DataHandler swTransactions = new DataHandler();
        swTransactions.dataOutputter("Transactions2014.csv");
    }
}
