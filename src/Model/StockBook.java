package Model;

import org.json.simple.JSONObject;

public class StockBook extends Model{

    private Book collection;//book name
    private Quality quality;
    private int releaseYear;
    private boolean isBorrow;

    public StockBook(Book book, Quality quality, int releaseYear, boolean isBorrow) {
        this.collection = book;
        this.quality = quality;
        this.releaseYear = releaseYear;
        this.isBorrow = isBorrow;
    }

    public StockBook(int id) {
        this.id = id;
    }

    public StockBook() {
    }

    public StockBook(int id, Book book, Quality quality, int releaseYear, boolean isBorrow) {
        super(id);
        this.collection = book;
        this.quality = quality;
        this.releaseYear = releaseYear;
        this.isBorrow = isBorrow;
    }




	public Book getBook() {
		return collection;
	}

	public void setBook(Book book) {
		this.collection = book;
	}

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public boolean isBorrow() {
		return isBorrow;
	}

	public void setBorrow(boolean isBorrow) {
		this.isBorrow = isBorrow;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		element.put("collection", this.collection.toJSON());
		element.put("quality", this.quality.toJSON());
		element.put("releaseyear", this.releaseYear);
		element.put("isborrow", this.isBorrow);
		return element;
	}
}