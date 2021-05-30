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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isBorrow() {
        return isBorrow;
    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }
}
