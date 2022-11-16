import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        AddressBook addressBook = getAddressBook();

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
            System.out.println("Press 0 to exit application");

            try {
                answer = input.nextInt();
                input.nextLine();
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                answer = -1;
                System.out.print("Not a valid selection");
            }
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
        }while(answer != kill); {
            System.out.println("Exiting Application...");
        }
    }

    private static AddressBook getAddressBook() {
        AddressBook addressBook = new AddressBook();
        try(BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/input.csv"))) {
            String line = "";
            // read the csv
            while ((line = fileReader.readLine()) != null) {
                String[] splitStr = line.replaceAll("\"", "").replaceAll("\\.", "").split(",");
                //get rid of double quotes and split the line on commas
                StringBuilder sb = new StringBuilder(splitStr[2].replaceAll("\\.", "").replaceAll(",", "").toUpperCase().trim()+ ",");
                //create a stringbuilder with the first address field to build a standardized address since inputs are not standardized

                //iterate across remaining address, final block is age, ignore it.
                for (int i = 3; i <= splitStr.length-2; i++) {
                    if (i==splitStr.length-2) {
                        sb.append(" " + splitStr[i].replaceAll("\\.", "").toUpperCase().trim());
                    } else {
                        sb.append(" " + splitStr[i].replaceAll("\\.", "").toUpperCase().trim() + ",");
                    }
                }
                //create AddressEntry objects standardize apartment with no comma this is essentially a hack, and would need to be further developed to handle additional usecases and alternative spellings
                if (sb.toString().contains(", APT")) {
                    for (int i = 0; i < sb.length(); i++) {
                        if (sb.charAt(i) == ',') {
                            sb.deleteCharAt(i);
                            break;
                        }
                    }
                }
                AddressEntry addressEntry = new AddressEntry(splitStr[0], splitStr[1], sb.toString(), splitStr[splitStr.length-1]);
                addressBook.addEntry(addressEntry);
                addressBook.createAndUpdateHousehold(addressEntry);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return addressBook;
    }
}
