package Model;

public class DetailBorrowRequest {
    private BorrowRequest request;
    private Book book;
    private int quantity;
    private boolean isAuthenticate;

    public DetailBorrowRequest(BorrowRequest request, Book book, int quantity, boolean isAuthenticate) {
        this.request = request;
        this.book = book;
        this.quantity = quantity;
        this.isAuthenticate = isAuthenticate;
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

    public boolean isAuthenticate() {
        return isAuthenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        isAuthenticate = authenticate;
    }
}
