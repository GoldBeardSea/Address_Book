import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static AddressBook csvReader(AddressBook addressBook, String filepath) {
        try (
                BufferedReader fileReader = new BufferedReader(new FileReader(filepath))) {
            String line = "";
            // read the csv
            while ((line = fileReader.readLine()) != null) {
                String[] splitStr = line.replaceAll("\"", "").replaceAll("\\.", "").split(",");
                //get rid of double quotes and split the line on commas
                StringBuilder sb = new StringBuilder(splitStr[2].replaceAll("\\.", "").replaceAll(",", "").toUpperCase().trim() + ",");
                //create a stringbuilder with the first address field to build a standardized address since inputs are not standardized

                //iterate across remaining address, final block is age, ignore it.
                for (int i = 3; i <= splitStr.length - 2; i++) {
                    if (i == splitStr.length - 2) {
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
                AddressEntry addressEntry = new AddressEntry(splitStr[0], splitStr[1], sb.toString(), splitStr[splitStr.length - 1]);
                addressBook.addEntry(addressEntry);
                addressBook.createAndUpdateHousehold(addressEntry);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return addressBook;
    }
}
