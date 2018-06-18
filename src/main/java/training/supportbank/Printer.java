package training.supportbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Printer {
    public static void dataOutputter(String csvFile) throws IOException {
        DataHandler dataHandler = new DataHandler();
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        reader.readLine();
        System.out.print(dataHandler.dataConverter(reader));
    }
}
