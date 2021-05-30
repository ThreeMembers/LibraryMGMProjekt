package Model;

public class DetailInput{
    private Input input;
    private StockBook stockBook;
    private Quality quality;
    private int quantity;
    private int releaseYear;

    public DetailInput() {
    }

    public DetailInput(Input input, StockBook stockBook, Quality quality, int quantity, int releaseYear) {
        this.input = input;
        this.stockBook = stockBook;
        this.quality = quality;
        this.quantity = quantity;
        this.releaseYear = releaseYear;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public StockBook getStockBook() {
        return stockBook;
    }

    public void setStockBook(StockBook stockBook) {
        this.stockBook = stockBook;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
