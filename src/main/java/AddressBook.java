import java.util.*;

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

    public void orderedLastFirstAdult() {
        extracted();
        for (AddressEntry a : entries) {
                if (Integer.valueOf(a.getAge()) >= 18 ) {
                    System.out.println(a.toString());
                }
            }
    }

    public void orderedLastFirst(int target) {
        extracted();
        for (AddressEntry a : entries) {
                if (Integer.valueOf(a.getAge()) >= target ) {
                    System.out.println(a.toString());
                }
            }
    }

    private void extracted() {
        Collections.sort(entries, new Comparator<AddressEntry>() {
            public int compare(AddressEntry e1, AddressEntry e2) {
                int r =  e1.getSecondName().compareToIgnoreCase(e2.getSecondName());
                if (r != 0)
                    return r;
                return e1.getFirstName().compareToIgnoreCase(e2.getSecondName());
            }
        });
    }

    public void printByHouseholdHigh() {
        household.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(household -> {
                    System.out.println("Address " + household.getKey() + ", Members of Household " + household.getValue());
                });
    }

}
