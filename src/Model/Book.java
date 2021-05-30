package Model;

public class Book extends Model {
	private String name;
	private Author author;
	private Category category;
	private String image;
	private int numberPage;
	private boolean available;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getNumberPage() {
		return numberPage;
	}
	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Book(int id, String name, Author author, Category category, String image, int numberPage,
			boolean available) {
		super(id);
		this.name = name;
		this.author = author;
		this.category = category;
		this.image = image;
		this.numberPage = numberPage;
		this.available = available;
	}
	public Book(int id) {
		super(id);
	}
	public Book() {
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", category=" + category + ", image=" + image
				+ ", numberPage=" + numberPage + ", available=" + available + ", id=" + id + "]";
	}
}
