package Model;

import org.json.simple.JSONObject;

public class DetailBorrowRequest {
    private int request;
    private int book;
    private int quantity;

    public DetailBorrowRequest(int request, int book, int quantity) {
        this.request = request;
        this.book = book;
        this.quantity = quantity;
    }

    public DetailBorrowRequest() {
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
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
    	element.put("request", this.request);
    	element.put("book", this.book);
    	element.put("quantity", this.quantity);
    	return element;
    }
}
