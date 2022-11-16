import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class AddressBookTest {
    AddressBook AB = new AddressBook();
    AddressEntry ae = new AddressEntry("John", "Doe", "200 Address", "15");
    AddressEntry ae2 = new AddressEntry("Alvin", "Doe", "200 AdDress", "15");

    @Test
    void addEntry() {
        assert(AB.entries.isEmpty());
        AB.addEntry(ae);
        assert(AB.entries.size() == 1);
        Object[] exp = AB.entries.toArray();
        String expected= exp[0].toString();
        assertEquals("John, Doe, 200 ADDRESS, 15", expected);
        AB.addEntry(ae2);
        exp = AB.entries.toArray();
        expected= exp[1].toString();
        //List doesn't sort on insertion
        assertEquals("Alvin, Doe, 200 ADDRESS, 15", expected);

    }

    @Test
    void createAndUpdateHousehold() {
        assert(AB.household.isEmpty());
        AB.createAndUpdateHousehold(ae);
        assert(AB.household.size() == 1);
        //same address size should remain one and value 2 should now be present in the map
        AddressEntry ae2 = new AddressEntry("Jack", "Bauer", "200 AddREss", "35");
        AB.createAndUpdateHousehold(ae2);
        assert(AB.household.size() == 1);
        assert(AB.household.containsValue(2));
        // add new entry size should be 2,
        AddressEntry ae3 = new AddressEntry("Jack", "Laufer", "300 AddREss", "35");
        AB.createAndUpdateHousehold(ae3);
        assert(AB.household.size() == 2);
        assert(AB.household.containsKey("200 ADDRESS"));

    }

    @Test
    void orderedSortingFunction() {
        AddressEntry ae3 = new AddressEntry("Jack", "Bauer", "200 AddREss", "35");
        AddressEntry ae4 = new AddressEntry("Zed", "Bauer", "300 AddREss", "35");
        AB.addEntry(ae);
        AB.addEntry(ae2);
        AB.addEntry(ae3);
        AB.addEntry(ae4);
        Object[] arrayUnsorted = AB.entries.toArray();
        String expectedUnsort= arrayUnsorted[0].toString();
        //List should be unsorted before call, after call size should be the same as it doesn't filter entries, but it should now be sorted
        assertEquals("John, Doe, 200 ADDRESS, 15", expectedUnsort);
        AB.orderedLastFirst();
        Object[] array = AB.entries.toArray();
        String expected= array[0].toString();
        assert(array.length == 4);
        assertEquals("Jack, Bauer, 200 ADDRESS, 35", expected);
    }

    @Test
    void printByHouseholdHigh() {
        // if I had more time, I would probably go back to the docs and create a mockito test for this
        AddressEntry ae3 = new AddressEntry("Jack", "Bauer", "200 AddREss", "35");
        AddressEntry ae4 = new AddressEntry("Zed", "Bauer", "300 AddREss", "35");
        AddressEntry ae5 = new AddressEntry("Hank", "Hill", "Arlen, Texas", "45");
        AddressEntry ae6 = new AddressEntry("Peggy", "Hill", "Arlen, Texas", "45");
        AB.createAndUpdateHousehold(ae3);
        AB.createAndUpdateHousehold(ae2);
        AB.createAndUpdateHousehold(ae);
        AB.createAndUpdateHousehold(ae4);
        AB.createAndUpdateHousehold(ae5);
        AB.createAndUpdateHousehold(ae6);
        AB.printByHouseholdHigh();
    }
}