package Model;

public class DetailDelBook{
    private DelBookHistory record;
    private StockBook stockBook;

    public DetailDelBook(DelBookHistory record, StockBook stockBook) {
        this.record = record;
        this.stockBook = stockBook;
    }
    public DetailDelBook(){}

    public DelBookHistory getRecord() {
        return record;
    }

    public void setRecord(DelBookHistory record) {
        this.record = record;
    }

    public StockBook getStockBook() {
        return stockBook;
    }

    public void setStockBook(StockBook stockBook) {
        this.stockBook = stockBook;
    }
}
