import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    AddressBook ad = new AddressBook();
    String filePath = "src/test/resources/test.csv";

    @Test
    public void csvCreatesAddressBook() {
        assert(ad.entries.isEmpty());
        assert(ad.household.isEmpty());
        ad = CSVReader.csvReader(ad, filePath);
        // 3 member household for Arlen address
        assert(ad.household.containsValue(3));
        // five entries in the test file
        assert(ad.entries.size() == 5);
    }

    @Test

    public void csvBadFilepath(){
    // no valid filepath still creates an empty AD that can have members added by CLI again without breaking script

        ad = CSVReader.csvReader(ad, "no");
        assert(ad.entries.isEmpty());
        assert(ad.household.isEmpty());

    }
}