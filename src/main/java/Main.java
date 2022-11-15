import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //load data into a csv
        AddressBook addressBook = new AddressBook();
        try(BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/input.csv"))) {
            String line = "";
            // read the csv
            while ((line = fileReader.readLine()) != null) {
                String[] splitStr = line.replaceAll("\"", "").replaceAll("\\.", "").split(",");
                //get rid of double quotes and split the line on commas
                StringBuilder sb = new StringBuilder(splitStr[2].replaceAll("\\.", "").replaceAll(",", "").toUpperCase().trim()+ ",");
                //create a stringbuilder with the first address field to build a standardized address field since inputs are not standardized

                //iterate across remaining address fields, final block is age, ignore it.
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
            addressBook.simpleToString();
            addressBook.printByHouseholdHigh();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // will work on this later and refactor
//        Scanner input = new Scanner(System.in);
//        int kill = 0;
//        int answer;
//        do {
//            System.out.println("-------- Address Book ---------");
//            System.out.println("Press 1 to print current Address Book");
//            //Add additional personal stretch goals after here
//            System.out.println("Press 0 to exit application");
//
//            try {
//                answer = input.nextInt();
//            } catch (NumberFormatException exception) {
//                exception.printStackTrace();
//                answer = -1;
//                System.out.print("Not a valid selection");
//            }
//            if (answer == 1)
//                AddressBook.printByHouseholdHigh();
//                AddressBook.printByAdultsLastName();
//        }while(answer != kill); {
//            System.out.println("Exiting Application...");
//        }
    }
}
