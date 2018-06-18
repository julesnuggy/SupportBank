package training.supportbank;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        DataHandler swTransactions = new DataHandler();
        Printer printer = new Printer();
        printer.dataOutputter("Transactions2014.csv");
    }
}
