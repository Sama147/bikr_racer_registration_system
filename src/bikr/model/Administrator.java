package bikr.model;

public class Administrator extends User {
    public Administrator() { }
    public Administrator(String firstName, String lastName, String email, String ssn, String password) {
        super(firstName, lastName, email, ssn, password);
    }
}