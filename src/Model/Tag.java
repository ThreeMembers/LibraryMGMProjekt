package Model;

public class Tag extends Model{
    private Book book;
    private String name;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag(int id, Book book, String name) {
        super(id);
        this.book = book;
        this.name = name;
    }

    public Tag(Book book, String name) {
        this.book = book;
        this.name = name;
    }

    public Tag(int id) {
        super(id);
    }

    public Tag() {
    }
    
    
}
