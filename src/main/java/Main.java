import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        AddressBook addressBook = new AddressBook();
        String filePath = "src/main/resources/input.csv";
        CSVReader.csvReader(addressBook, filePath);

        // bare minim console CLI to control some of the scripts
        Scanner input = new Scanner(System.in);
        int kill = 0;
        int answer, age;
        do {
            System.out.println("--------- | Address Book | ---------");
            System.out.println("Press 1 to print current Address Book, ordered by largest household, and then individual entries over 18 years old");
            //Add additional personal stretch goals after here
            System.out.println("Press 2 to print current Address Book, ordered by largest household, and then individual entries over a specific age in years");
            System.out.println("Press 3 to add an entry to the address book");
            System.out.println("Press 4 to add filepath to create addressbook out of a csv");
            System.out.println("Press 0 to exit application");

            try {
                answer = input.nextInt();
                input.nextLine();
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                answer = -1;
                System.out.print("Not a valid selection");
            }
            //in time introduce handling logic for user input errors
            if (answer == 1) {
                addressBook.printByHouseholdHigh();
                addressBook.orderedLastFirst();
            }
            if (answer == 2) {
                System.out.println("Enter Age (0-120) to filter entries");
                age = input.nextInt();
                System.out.println("You have entered " + age + " years old");
                addressBook.printByHouseholdHigh();
                addressBook.orderedLastFirst(age);
            }
            if (answer == 3) {
                System.out.println("Enter First Name");
                String firstName = input.nextLine();
                System.out.println("Enter Last Name");
                String lastName = input.nextLine();
                System.out.println("Enter Address Format: Street Unit, City, State (Abbreviation)");
                String address = input.nextLine().toUpperCase();
                System.out.println("Enter Age");
                String ageStr = input.nextLine();
                AddressEntry addressEntry = new AddressEntry(firstName, lastName, address, ageStr);
                addressBook.addEntry(addressEntry);
                addressBook.createAndUpdateHousehold(addressEntry);
                System.out.println("You have inserted " + addressEntry.toString() + " into address book");
            }
            if (answer == 4) {
                System.out.println("Enter Filepath for different csv to build address book");
                String filePathInput = input.nextLine();
                AddressBook inputBook = new AddressBook();
                CSVReader.csvReader(inputBook, filePathInput);
                System.out.println("Enter Target age to filter entries");
                int ageStr = input.nextInt();
                inputBook.printByHouseholdHigh();
                inputBook.orderedLastFirst(ageStr);
            }
        } while (answer != kill);
        {
            System.out.println("Exiting Application...");
        }
    }
}
