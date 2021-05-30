package Model;

public class Quality extends Model{
	private String situation;
	private String description;
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Quality(int id, String situation, String description) {
		super(id);
		this.situation = situation;
		this.description = description;
	}
	public Quality(int id) {
		super(id);
	}
	public Quality() {
	}
	@Override
	public String toString() {
		return "Quality [situation=" + situation + ", description=" + description + ", id=" + id + "]";
	}
}
