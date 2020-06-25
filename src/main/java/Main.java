public class Main {

    public static void main(String[] args) {

        Filler.fillLibrary();

        System.out.println("The following persons are customers:");
        System.out.println(Library.printEntireCustomerBase());
        System.out.println();
        System.out.println("The following books are in our inventory:");
        System.out.println(Library.printEntireBookInventor());

        System.out.println(Library.deleteExistingCostumer(3));
        System.out.println(Library.deleteExistingBook(5));
        System.out.println();
        System.out.println("The following books are in our inventory:");
        System.out.println(Library.printEntireBookInventor());
        System.out.println("The following persons are customers:");
        System.out.println(Library.printEntireCustomerBase());
        System.out.println();

        System.out.println("lending 1");
        System.out.println(Library.lendBook(5, 4));
        System.out.println("lending 2");
        System.out.println(Library.lendBook(2, 2));
        System.out.println("lending 3");
        System.out.println(Library.lendBook(3, 1));
        System.out.println("lending list 1");
        System.out.println(Library.printEntireLendingList());
        System.out.println();
        System.out.println("return 1");
        System.out.println(Library.returnBook(5, 4, 5));
        System.out.println("return 2");
        System.out.println(Library.returnBook(2, 2, 10));
        System.out.println("return 3");
        System.out.println(Library.returnBook(3, 1, 14));
        System.out.println("lending list 2");
        System.out.println(Library.printEntireLendingList());

        System.out.println();
        System.out.println();
        System.out.println(Library.lendBook(9, 7));
        System.out.println(Library.fillCredit(7, 5));
        System.out.println();
        System.out.println();
        System.out.println(Library.lendBook(9, 1));
        System.out.println(Library.fillCredit(1, 5));


    }
}
