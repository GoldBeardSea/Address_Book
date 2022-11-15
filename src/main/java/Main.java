import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //load data into a csv
        try(BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/input.csv"))) {
            String line = "";
            // read the csv
            while ((line = fileReader.readLine()) != null) {
                String[] splitStr = line.replaceAll("\"", "").split(",");
                //get rid of double quotes and split the line on commas
                StringBuilder sb = new StringBuilder(splitStr[2].replaceAll("\\.", "").toUpperCase().trim()+ ",");
                //create a stringbuilder with the first address field to build a standardized address field since inputs are not standardized

                //iterate across remaining address fields, final block is age, ignore it.
                for (int i = 3; i <= splitStr.length-2; i++) {
                    if (i==splitStr.length-2) {
                        sb.append(" " + splitStr[i].replaceAll("\\.", "").toUpperCase().trim());
                    } else {
                        sb.append(" " + splitStr[i].replaceAll("\\.", "").toUpperCase().trim() + ",");
                    }
                }
                //create AddressEntry objects
                System.out.println(sb.toString());
                System.out.println(Arrays.toString(splitStr));
                AddressEntry addressEntry = new AddressEntry(splitStr[0], splitStr[1], sb.toString(), splitStr[splitStr.length-1]);
                System.out.println(addressEntry.toString());
            }
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
//                AddressBook.printByHouseHold();
//                AddressBook.printByAdultsLastName();
//        }while(answer != kill); {
//            System.out.println("Exiting Application...");
//        }
    }
}
