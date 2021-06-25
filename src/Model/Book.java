package Model;

import org.json.simple.JSONObject;

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
	
	public Book(int id, String name) {
		super(id);
		this.name = name;
	}
	
	public Book(String name, Author author, Category category, String image, int numberPage,
			boolean available) {
		this.name = name;
		this.author = author;
		this.category = category;
		this.image = image;
		this.numberPage = numberPage;
		this.available = available;
	}
	
	public Book(String name, Author author, Category category, String image, int numberPage) {
		this.name = name;
		this.author = author;
		this.category = category;
		this.image = image;
		this.numberPage = numberPage;
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
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		if (this.name != null)
			element.put("name", this.name);
		if (this.author != null)
			element.put("author", this.author.toJSON());
		if (this.category != null) 
			element.put("category", this.category.toJSON());
		if (this.image != null)
			element.put("image", this.image);
		if (this.numberPage != 0)
			element.put("numberpage", this.numberPage);
		element.put("available", this.available);
		return element;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numberPage;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (available != other.available)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberPage != other.numberPage)
			return false;
		return true;
	}
	
}
