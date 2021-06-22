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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((record == null) ? 0 : record.hashCode());
		result = prime * result + ((stockBook == null) ? 0 : stockBook.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailBorrow other = (DetailBorrow) obj;
		if (record == null) {
			if (other.record != null)
				return false;
		} else if (!record.equals(other.record))
			return false;
		if (stockBook == null) {
			if (other.stockBook != null)
				return false;
		} else if (!stockBook.equals(other.stockBook))
			return false;
		return true;
	}
}
