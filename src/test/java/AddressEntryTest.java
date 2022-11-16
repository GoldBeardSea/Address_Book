import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AddressEntryTest {

    private AddressEntry ae;
    private AddressEntry ae2;

    @Test
    void constructorTest() {
        ae = new AddressEntry("John", "Brown", "200 Address", "15");
        assertNotNull(ae);
        assertEquals("John", ae.getFirstName());
    }

    @Test
    void constructorObjects() {
        ae = new AddressEntry("John", "Brown", "200 Address", "15");
        ae2 = new AddressEntry("Chazz", "Nullpointer", "234 average road", "13");
        assertNotEquals(ae, ae2);
    }

    @Test
    void addressEquals() {
        ae = new AddressEntry("John", "Brown", "234 aveRage road", "15");
        ae2 = new AddressEntry("Chazz", "Nullpointer", "234 average Road", "13");
        assertEquals(ae.getAddress(), ae2.getAddress());

    }

    @Test
    void getNames() {
        ae = new AddressEntry("John", "Brown", "200 Address", "15");
        assertEquals("John", ae.getFirstName());
        assertEquals("Brown", ae.getSecondName());
    }

    @Test
    void getNameThrowsNullOnNullObj(){
        assertThrows(NullPointerException.class,
                () -> {
                    ae.getFirstName();
                });
        assertThrows(NullPointerException.class,
                () -> {
                    ae2.getSecondName();
                });
    }

    @Test
    void setNames() {
        ae = new AddressEntry("John", "Brown", "200 Address", "15");
        ae.setFirstName("Tim");
        ae.setSecondName("Busch");
        assertEquals("Tim", ae.getFirstName());
        assertEquals("Busch", ae.getSecondName());
    }

    @Test
    void setAddress() {
        ae = new AddressEntry("John", "Doe", "200 Address", "15");
        ae.setAddress("500 AveraGe RoAd");
        assertEquals("500 AVERAGE ROAD", ae.getAddress());
    }

    @Test
    void getAge() {
        ae = new AddressEntry("John", "Doe", "200 Address", "15");
        assertEquals("15", ae.getAge());
    }

    @Test
    void setAge() {
        ae = new AddressEntry("John", "Doe", "200 Address", "15");
        assertEquals("15", ae.getAge());
        ae.setAge("19");
        assertEquals("19", ae.getAge());
    }

    @Test
    void testToString() {
        ae = new AddressEntry("String", "Stringify", "String Street", "20");
        String expected = "String, Stringify, STRING STREET, 20";
        assertEquals(expected, ae.toString());
    }
}