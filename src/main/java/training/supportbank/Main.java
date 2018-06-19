package training.supportbank;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        Printer printer = new Printer();
//        Scanner scanner = new Scanner(System.in);

        printer.listAll("Transactions2014.csv");
    }
}
