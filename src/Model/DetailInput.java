package Model;

import org.json.simple.JSONObject;

public class DetailInput{
    private Input input;
    private StockBook stockBook;
    private int quantity;
    public DetailInput() {
    }

    public DetailInput(Input input, StockBook stockBook, int quantity) {
        this.input = input;
        this.stockBook = stockBook;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @SuppressWarnings("unchecked")
   	public JSONObject toJSON() {
       	JSONObject element = new JSONObject();
       	element.put("record", this.input.toJSON());
       	element.put("stockbook", this.stockBook.toJSON());
       	element.put("quantity", this.quantity);
       	return element;
       }
}
