package Model;

import org.json.simple.JSONObject;

import java.util.Objects;

public class DetailBorrow {
    private int record;
    private int stockBook;

    public DetailBorrow() {
    }

    public DetailBorrow(int record, int stockBook) {
        this.record = record;
        this.stockBook = stockBook;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getStockBook() {
        return stockBook;
    }

    public void setStockBook(int stockBook) {
        this.stockBook = stockBook;
    }
    
    @SuppressWarnings("unchecked")
	public JSONObject toJSON() {
    	JSONObject element = new JSONObject();
    	element.put("record", this.record);
    	element.put("stockbook", this.stockBook);
    	return element;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DetailBorrow that = (DetailBorrow) o;
		return record == that.record && stockBook == that.stockBook;
	}

	@Override
	public int hashCode() {
		return Objects.hash(record, stockBook);
	}
}
