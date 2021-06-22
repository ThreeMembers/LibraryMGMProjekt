package Model;

import org.json.simple.JSONObject;

public class DetailBorrowRequest {
    private BorrowRequest request;
    private Book book;
    private int quantity;

    public DetailBorrowRequest(BorrowRequest request, Book book, int quantity) {
        this.request = request;
        this.book = book;
        this.quantity = quantity;
    }

    public DetailBorrowRequest() {
    }

    public BorrowRequest getRequest() {
        return request;
    }

    public void setRequest(BorrowRequest request) {
        this.request = request;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
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
    	element.put("request", this.request.toJSON());
    	element.put("book", this.book.toJSON());
    	element.put("quantity", this.quantity);
    	return element;
    }
}
