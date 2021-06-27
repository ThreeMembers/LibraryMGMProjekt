package Model;

import org.json.simple.JSONObject;

public class StockBook extends Model {
    private Book collection;
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
        super(id);
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
    
    public StockBook(int id, Book book) {
    	super(id);
        this.collection = book;
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
		JSONObject element = new JSONObject();
		element.put("id", this.id);
		if(this.collection != null)
			element.put("collection", this.collection.toJSON());
		if(this.quality != null)
			element.put("quality", this.quality.toJSON());
		element.put("releaseyear", this.releaseYear);
		element.put("isborrow", this.isBorrow);
		return element;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((collection == null) ? 0 : collection.hashCode());
		result = prime * result + (isBorrow ? 1231 : 1237);
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
		result = prime * result + releaseYear;
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
		StockBook other = (StockBook) obj;
		if (collection == null) {
			if (other.collection != null)
				return false;
		} else if (!collection.equals(other.collection))
			return false;
		if (isBorrow != other.isBorrow)
			return false;
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StockBook{" +
				"id=" + id +
				", collection=" + collection +
				", quality=" + quality +
				", releaseYear=" + releaseYear +
				", isBorrow=" + isBorrow +
				'}';
	}
}