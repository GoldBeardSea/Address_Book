public class AddressEntry {
    String firstName;
    String secondName;
    String address;
    String age;

    public AddressEntry(String firstName, String secondName, String address, String age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address.toUpperCase();
        this.age = age;
    }

    //creating standard get/setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.toUpperCase();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", getFirstName(), getSecondName(), getAddress(), getAge());
    }
}
