package DatabaseProject;

public class Client {

    //Attributes
    private int Client_ID;
    private String firstName;
    private String lastName;
    private String email;
    private String employer;

    public Client(int client_ID, String firstName, String lastName, String email, String employer) {
        Client_ID = client_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employer = employer;
    }

    @Override
    public String toString() {
        return  "Client ID=" + Client_ID +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", Employer='" + employer + '\'';
    }
}
