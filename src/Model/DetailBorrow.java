package Model;

import org.json.simple.JSONObject;

public class DetailBorrow {
    private Borrow record;
    private StockBook stockBook;

    public DetailBorrow() {
    }

    public DetailBorrow(Borrow record, StockBook stockBook) {
        this.record = record;
        this.stockBook = stockBook;
    }

    public Borrow getRecord() {
        return record;
    }

    public void setRecord(Borrow record) {
        this.record = record;
    }

    public StockBook getStockBook() {
        return stockBook;
    }

    public void setStockBook(StockBook stockBook) {
        this.stockBook = stockBook;
    }
    
    @SuppressWarnings("unchecked")
	public JSONObject toJSON() {
    	JSONObject element = new JSONObject();
    	element.put("record", this.record.toJSON());
    	element.put("stockbook", this.stockBook.toJSON());
    	return element;
    }
}
