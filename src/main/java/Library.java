import java.util.ArrayList;
import java.util.List;

public class Library {

    public static double MINIMUM_CREDIT = 5.0;
    public static int LIST_SIZE;
    private static double LENDING_FEE_PER_DAY = 0.5;
    private static int LENDINGS_TOTAL = 0;
    private static List<Book> BOOK_INVENTORY = new ArrayList<>();
    private static List<Person> CUSTOMER_BASE = new ArrayList<>();
    private static List<Library> LENDING_REGISTER = new ArrayList<>();
    private int lendingID;
    private int lendingPeriodInDays;
    private int bookID;
    private String bookTitle;
    private int customerID;
    private String customerFirstName;
    private String getCustomerLastName;

    //PRIMARY CONSTRUCTOR
    public Library(int bookID, int customerID) {
        LENDINGS_TOTAL++;
        this.lendingID = LENDINGS_TOTAL;
        this.bookID = bookID;
        this.customerID = customerID;
    }

    //SECONDARY CONSTRUCTOR, just in case
    public Library(String bookTitle, String customerFirstName, String getCustomerLastName) {
        LENDINGS_TOTAL++;
        this.lendingID = LENDINGS_TOTAL;
        this.bookTitle = bookTitle;
        this.customerFirstName = customerFirstName;
        this.getCustomerLastName = getCustomerLastName;
    }

    public static String lendBook(int bookID, int customerID) {
        String lendingStatus = "";
        if (verifyCredit(customerID)) {
            if (customerID != 7) {
                Library lending = new Library(bookID, customerID);
                LENDING_REGISTER.add(lending);
                lendingStatus = "Lending (ID " + lending.getLendingID() + ") successful";
            } else {
                lendingStatus = "Careful Karen, reading can exhausting!";
            }
        } else {
            lendingStatus = "Not enough credit on customer's account to lend this book. Please increase credit to a minimum of " + MINIMUM_CREDIT + "!";
        }
        return lendingStatus;
    }


    public static String returnBook(int bookID, int customerID, int lendingPeriodInDays) {

        String returnStatus = "";
        double lendingFeeAtReturn = lendingPeriodInDays * LENDING_FEE_PER_DAY;
        double creditBeforeReturn = 0;
        double creditAfterReturn = 0;
        LIST_SIZE = LENDING_REGISTER.size();

        if (LIST_SIZE > 0) {
            for (int i = 0; i < LIST_SIZE; i++) {
                if (LENDING_REGISTER.get(i).getBookID() == bookID && LENDING_REGISTER.get(i).getCustomerID() == customerID) {

                    creditBeforeReturn = CUSTOMER_BASE.get(i).getCredit();
                    creditAfterReturn = creditBeforeReturn - lendingFeeAtReturn;

                    if (creditAfterReturn < 0) {

                        returnStatus += "You have depleted your credit and cannot pay for this transaction." +
                                "\nThe book is going to remain with you until your account can be charged again." +
                                "\nPlease transfer a minimum of " + (0 + creditAfterReturn + MINIMUM_CREDIT) + " credits to your account." +
                                "\nHaving done so, please retry returning the book.";
                        return returnStatus;

                    } else if (creditAfterReturn == 0) {

                        LENDING_REGISTER.remove(i);
                        LENDINGS_TOTAL--;
                        returnStatus += "Thank you, the return has been processed." +
                                "\nThis transaction is going to deplete your credit." +
                                "\nPlease transfer a minimum of " + MINIMUM_CREDIT + " credits to your account after the return";
                        return returnStatus;

                    } else if (creditAfterReturn < MINIMUM_CREDIT && creditAfterReturn > 0) {

                        LENDING_REGISTER.remove(i);
                        LENDINGS_TOTAL--;
                        returnStatus += "Thank you, the return has been processed." +
                                "\nWe remind you that your credit is too low for a future lending." +
                                "\nPlease transfer a minimum of " + MINIMUM_CREDIT + " credits to your account after the return";
                        return returnStatus;

                    } else if (creditAfterReturn > 10) {
                        LENDING_REGISTER.remove(i);
                        LENDINGS_TOTAL--;
                        returnStatus += "Thank you, the return has been processed.";
                        return returnStatus;
                    }
                }
            }
        } else {
            returnStatus += "There are no books currently lent!";
            return returnStatus;
        }
        return "Error: Lending ID not found.\nPlease contact the account manager!";
    }


    public static boolean verifyCredit(int customerID) {
        LIST_SIZE = CUSTOMER_BASE.size();
        for (int i = 0; i < LIST_SIZE; i++) {
            if (CUSTOMER_BASE.get(i).getCustomerID() == customerID) {
                if (CUSTOMER_BASE.get(i).getCredit() >= MINIMUM_CREDIT) {
                    return true;
                }
            }
        }
        return false;
    }


    public static String fillCredit(int customerID, double creditIncrease) {
        // de-wait-for-it-nied
        LIST_SIZE = CUSTOMER_BASE.size();
        for (int i = 0; i < LIST_SIZE; i++) {
            if (CUSTOMER_BASE.get(i).getCustomerID() == customerID && CUSTOMER_BASE.get(i).getFirstName() != "Karen") {
                CUSTOMER_BASE.get(i).setCredit(CUSTOMER_BASE.get(i).getCredit() + creditIncrease);
                return "Credit of customer has been increased.";
            } else if (CUSTOMER_BASE.get(i).getCustomerID() == customerID && CUSTOMER_BASE.get(i).getFirstName() == "Karen") {
                return "A Cosmo is a magazine, KAREN, not a book.\nYour money is better spent at the news stand.\nThe manager is not in this year.\nHave a nice day!";
            }
        }
        return "Error: Credit could not be increased.\nPlease contact the account manager!";
    }


    public static String createNewCustomer(String firstName, String lastName, double credit) {
        Person person = new Person(firstName, lastName, credit);
        CUSTOMER_BASE.add(person);
        return "New customer " + firstName + " " + lastName + " has been registered";
    }


    public static String deleteExistingCostumer(int customerID) {
        String customerToDelete = "";
        LIST_SIZE = CUSTOMER_BASE.size();
        for (int i = 0; i < LIST_SIZE; i++) {
            if (CUSTOMER_BASE.get(i).getCustomerID() == customerID) {
                customerToDelete = CUSTOMER_BASE.get(i).getFirstName() + " " + CUSTOMER_BASE.get(i).getLastName() + " (ID " + CUSTOMER_BASE.get(i).getCustomerID() + ")";
                CUSTOMER_BASE.remove(i);
                return "Existing customer " + customerToDelete + " has been removed from the customer registry";
            }
        }
        return "Error: Customer not found.\nPlease contact the account manager!";
    }


    public static String createNewBook(String author, String title, String language, String genre, int pages) {
        Book book = new Book(author, title, language, genre, pages);
        BOOK_INVENTORY.add(book);
        return "New book " + title + " by " + author + " has been added to the inventory";
    }


    public static String deleteExistingBook(int bookID) {
        String bookToDelete = "";
        LIST_SIZE = BOOK_INVENTORY.size();
        for (int i = 0; i < LIST_SIZE; i++) {
            if (BOOK_INVENTORY.get(i).getBookID() == bookID) {
                bookToDelete = BOOK_INVENTORY.get(i).getTitle() + "', by " + BOOK_INVENTORY.get(i).getAuthor();
                BOOK_INVENTORY.remove(i);
                return "Existing book '" + bookToDelete + "' has been deleted from to the inventory";
            }
        }
        return "Error: Book not found.\nPlease contact the account manager!";
    }


    /* FORMER VERSION OF METHOD THAT DOES NOT REMOVE THE CORRECT CUSTOMER
    public static String deleteExistingBook(int bookID) {
        String bookToDelete = "";
        int toBeDeleted = 0;
        for (int i = 0; i < bookInventory.size(); i++) {
            if (bookInventory.get(i).getBookID() == bookID) {
                toBeDeleted = bookInventory.get(i).getBookID();
                bookToDelete = bookInventory.get(i).getTitle() + "', by " + bookInventory.get(i).getAuthor();
            }
        }
        bookInventory.remove(toBeDeleted);
        return "Existing book '" + bookToDelete + "' has been deleted from to the inventory";
    }
    */

    public static String printEntireBookInventor() {
        String booksInventoryToString = getBookInventory().size() + " books in total\n";
        for (Book book : getBookInventory()) {
            booksInventoryToString += "Inventory ID " + book.getBookID() + ": '" + book.getTitle() + "' by " + book.getAuthor() + ", " + book.getLanguage() + ", " + book.getGenre() + ", " + book.getPages() + " pages\n";
        }
        return booksInventoryToString;
    }


    public static String printEntireCustomerBase() {
        String customerBaseToString = getCustomerBase().size() + " customers in total\n";
        ;
        for (Person person : getCustomerBase()) {
            customerBaseToString += "Customer ID " + person.getCustomerID() + ": " + person.getFirstName() + " " + person.getLastName() + ", " + person.getCredit() + " credits\n";
        }
        return customerBaseToString;
    }


    public static String printEntireLendingList() {
        String lendingListToString = getLendingsTotal() + " lendings in total\n";
        ;
        for (Library lending : getLendingRegister()) {
            lendingListToString += "Lending ID " + lending.getLendingID() + ": Customer " + lending.getCustomerID() + " has lent Book ID " + lending.getBookID() + "\n";
        }
        return lendingListToString;
    }


    public static List<Person> getCustomerBase() {
        return CUSTOMER_BASE;
    }

    public static void setCustomerBase(List<Person> CUSTOMER_BASE) {
        CUSTOMER_BASE = CUSTOMER_BASE;
    }

    public static List<Library> getLendingRegister() {
        return LENDING_REGISTER;
    }

    public static void setLendingRegister(List<Library> lendingRegister) {
        LENDING_REGISTER = lendingRegister;
    }

    public static int getLendingsTotal() {
        return LENDINGS_TOTAL;
    }

    public static void setLendingsTotal(int lendingsTotal) {
        LENDINGS_TOTAL = lendingsTotal;
    }

    public static int getListSize() {
        return LIST_SIZE;
    }

    public static void setListSize(int listSize) {
        LIST_SIZE = listSize;
    }

    public static List<Book> getBookInventory() {
        return BOOK_INVENTORY;
    }

    public static void setBookInventory(List<Book> bookInventory) {
        BOOK_INVENTORY = bookInventory;
    }

    public int getLendingID() {
        return lendingID;
    }

    public void setLendingID(int lendingID) {
        this.lendingID = lendingID;
    }

    public int getLendingPeriodInDays() {
        return lendingPeriodInDays;
    }

    public void setLendingPeriodInDays(int lendingPeriodInDays) {
        this.lendingPeriodInDays = lendingPeriodInDays;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getGetCustomerLastName() {
        return getCustomerLastName;
    }

    public void setGetCustomerLastName(String getCustomerLastName) {
        this.getCustomerLastName = getCustomerLastName;
    }

    public double getLendingFeePerDay() {
        return LENDING_FEE_PER_DAY;
    }

    public void setLendingFeePerDay(double lendingFeePerDay) {
        this.LENDING_FEE_PER_DAY = lendingFeePerDay;
    }
}
