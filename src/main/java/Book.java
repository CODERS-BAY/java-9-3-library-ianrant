public class Book {

    private static int booksTotal = 0;
    private String author;
    private String title;
    private String language;
    private String genre;
    private int pages;
    private int bookID;

    public Book(String author, String title, String language, String genre, int pages) {
        this.author = author;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.pages = pages;
        booksTotal++;
        this.bookID = booksTotal;
    }

    public static int getBooksTotal() {
        return booksTotal;
    }

    public static void setBooksTotal(int booksTotal) {
        Book.booksTotal = booksTotal;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "'" + title + "' by " + author + ", " + language + ", " + genre + ", " + pages;
    }
}

