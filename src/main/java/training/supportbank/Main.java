package training.supportbank;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        Printer printer = new Printer();
        printer.listAll("Transactions2014.csv");
    }
}
