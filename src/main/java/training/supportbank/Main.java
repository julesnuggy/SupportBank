package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        String csvFile = "Transactions2014.csv";
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));

        Scanner scanner = new Scanner(reader);
        scanner.useDelimiter(",");

        while (scanner.hasNext()) {
            System.out.print(scanner.next() + " ¦ ");
        }

        scanner.close();
    }
}
