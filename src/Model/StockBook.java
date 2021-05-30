package Model;

public class StockBook {
    private String id;
    private Book book;
    private Quality quality;
    private int releaseYear;
    private boolean isBorrow;

    public StockBook(Book book, Quality quality, int releaseYear, boolean isBorrow) {
        this.book = book;
        this.quality = quality;
        this.releaseYear = releaseYear;
        this.isBorrow = isBorrow;
    }

    public StockBook(String id) {
        this.id = id;
    }

    public StockBook() {
    }

    public StockBook(String id, Book book, Quality quality, int releaseYear, boolean isBorrow) {
        this.id = id;
        this.book = book;
        this.quality = quality;
        this.releaseYear = releaseYear;
        this.isBorrow = isBorrow;
    }
}
