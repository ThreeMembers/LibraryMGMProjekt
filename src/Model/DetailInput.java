package Model;

import org.json.simple.JSONObject;

public class DetailInput{
    private Input input;
    private Book book;
    private int quantity;
    public DetailInput() {
    }

    public DetailInput(Input input, Book stockBook, int quantity) {
        this.input = input;
        this.book = stockBook;
        this.quantity = quantity;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book stockBook) {
        this.book = stockBook;
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
       	element.put("book", this.book.toJSON());
       	element.put("quantity", this.quantity);
       	return element;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + quantity;
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
		DetailInput other = (DetailInput) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
    
}
