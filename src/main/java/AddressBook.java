import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
        } else {
            household.put(key, 1);
        }
    }

    public void simpleToString() {
        for (AddressEntry a : entries) {
            System.out.println(a.toString());
        }
    }

    public void printByHouseholdHigh() {
        household.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(household -> {
                    System.out.println("Address " + household.getKey() + ", Members of Household " + household.getValue());
                });
    }

}
