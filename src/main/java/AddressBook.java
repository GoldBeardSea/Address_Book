import java.util.*;

public class AddressBook {
    //an address book as I see it, consists of a hashmap of unique addresses that are households and an array list of entries
    HashMap<String, Integer> household = new LinkedHashMap<>();
    ArrayList<AddressEntry> entries = new ArrayList<>();

    //would eventually be smart to put in logic that could search for strict duplicates of address, name, age.
    public void addEntry(AddressEntry entry) {
        entries.add(entry);
    }

    // if it doesn't exist add it, if it exists update it
    public void createAndUpdateHousehold (AddressEntry entry) {
        String key = entry.getAddress();
        if (household.containsKey(key)) {
            household.put(key, household.get(key) + 1);
        } else {
            household.put(key, 1);
        }
    }
    //used during development to test output
    public void simpleToStringPrint() {
        for (AddressEntry a : entries) {
            System.out.println(a.toString());
        }
    }

    // using >= because I feel like 18 would match the dev description as a minimum age, however, it might have been better to just use >, depends on definition of older
    public void orderedLastFirst() {
        sortLastFirst();
        for (AddressEntry a : entries) {
                if (Integer.parseInt(a.getAge()) >= 18 ) {
                    System.out.println(a.toString());
                }
            }
    }
    // input for overloaded method
    public void orderedLastFirst(int target) {
        sortLastFirst();
        for (AddressEntry a : entries) {
                if (Integer.parseInt(a.getAge()) >= target ) {
                    System.out.println(a.toString());
                }
            }
    }

    private void sortLastFirst() {
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
