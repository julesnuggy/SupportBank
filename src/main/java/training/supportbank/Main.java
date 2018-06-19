package training.supportbank;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);
        System.out.print("This programme allows you to view processed data for the Transaction2014.csv file.\n" +
                "ListAll - outputs each persons name and the total they owe or are owed\n" +
                "ListAccount - outputs every transaction associated with the given name\n" +
                "Please enter your desired operation: ");
        String operation = scanner.next();
        if (operation.equals("ListAll")) {
            printer.listAll("Transactions2014.csv");
        } else if (operation.equals("ListAccount")) {
            System.out.print("Enter the full name of the person whose transactions you wish to view: ");
            String accountName = scanner.next();
            accountName += scanner.nextLine();
            printer.listAccount("Transactions2014.csv", accountName);
        } else {
            System.out.print("Invalid input");
        }

    }
}
