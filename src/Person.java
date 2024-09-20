// Create an Abstract class called person and extend it from both Doctor and Patient classes
// name and contactNumber are common variables for both Doctor and Patient classes
abstract class Person {
    private final String name;
    private String contactNumber;

    // constructor
    public Person(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return this.name;
    }
}