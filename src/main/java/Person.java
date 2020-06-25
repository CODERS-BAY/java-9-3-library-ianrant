public class Person {

    private static int customersTotal = 0;
    private String firstName;
    private String lastName;
    private double credit;
    private int customerID;

    public Person(String firstName, String lastName, double credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.credit = credit;
        customersTotal++;
        this.customerID = customersTotal;
    }

    public static int getUsersTotal() {
        return customersTotal;
    }

    public static void setUsersTotal(int usersTotal) {
        Person.customersTotal = usersTotal;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", Customer-ID: " + customerID + ", " + credit + " credits";
    }
}
