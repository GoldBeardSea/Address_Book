import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AddressBook {
    HashMap<String, Integer> household = new LinkedHashMap<>();
    ArrayList<AddressEntry> entries = new ArrayList<>();

    public void addEntry(AddressEntry entry) {
        entries.add(entry);
    }

    public void createAndUpdateHousehold (AddressEntry entry) {
        String key = entry.getAddress();
        if (household.containsKey(key)) {
            household.put(key, household.get(key) + 1);
        }
    }

    public void simpleToString() {
        for (AddressEntry a : entries) {
            System.out.println(a.toString());
        }
    }

}
